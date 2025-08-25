/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import lk.sau.app.globemed.bridge.BillRepositoryImpl;
import lk.sau.app.globemed.bridge.BillService;
import lk.sau.app.globemed.bridge.BillServiceImpl;
import lk.sau.app.globemed.entity.Bill;
import lk.sau.app.globemed.entity.MedicalRecord;
import lk.sau.app.globemed.entity.PaymentType;
import lk.sau.app.globemed.util.HibernateUtil;
import lk.sau.app.globemed.visitor.PdfBillVisitor;
import org.hibernate.Session;

/**
 *
 * @author Saumya
 */
public class BillingDialog extends JDialog {

    private JComboBox<PaymentType> paymentTypeCombo;
    private JButton payButton, printButton;
    private JLabel amountLabel;
    private int patientId;
    private MedicalRecord medicalRecord;

    private BillService billService;

    private Bill savedBill;

    public BillingDialog(Frame parent, int patientId, MedicalRecord medicalRecord) {
        super(parent, "Pay Bill", true);
        this.patientId = patientId;
        this.medicalRecord = medicalRecord;
        this.billService = new BillServiceImpl(new BillRepositoryImpl());

        initUI();
        setLocationRelativeTo(parent); // center

    }

    private void initUI() {
        setLayout(new GridLayout(5, 2, 10, 10));

        amountLabel = new JLabel("Amount: " + calculateAmount());
        paymentTypeCombo = new JComboBox<>(loadPaymentTypes().toArray(new PaymentType[0]));
        payButton = new JButton("Pay Bill");
        printButton = new JButton("Print Bill");
//        printButton.setEnabled(false);

        add(new JLabel("Patient ID:"));
        add(new JLabel(String.valueOf(patientId)));
        add(new JLabel("Medical Record ID:"));
        add(new JLabel(String.valueOf(medicalRecord.getId())));
        add(new JLabel("Amount:"));
        add(amountLabel);
        add(new JLabel("Payment Type:"));
        add(paymentTypeCombo);
        add(payButton);
        add(printButton);

        payButton.addActionListener(e -> saveBill());
        printButton.addActionListener(e -> printBill());
        setSize(400, 250);
    }

    private double calculateAmount() {
        return 3100 + medicalRecord.getTreatmentTypeId().getPrice();
    }

    private List<PaymentType> loadPaymentTypes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM PaymentType", PaymentType.class).list();
        }
    }

    private void saveBill() {

        Bill bill = new Bill();
        bill.setPatient(medicalRecord.getPatient());
        bill.setMedicalRecord(medicalRecord);
        bill.setAmount(calculateAmount());
        bill.setBillingDate(LocalDateTime.now());

        PaymentType selectedType = (PaymentType) paymentTypeCombo.getSelectedItem();
        String paymentTypeName = selectedType.getPaymentType();
        bill.setPaymentType(selectedType);

        if (billService.saveBill(bill)) {
            this.savedBill = bill;
            JOptionPane.showMessageDialog(this, "Bill Paid Successfully!");
            printButton.setEnabled(true);
        }
    }

    private void printBill() {
        if (savedBill != null) {
            try {
                PdfBillVisitor pdfVisitor = new PdfBillVisitor();
                savedBill.accept(pdfVisitor);  // Visitor in action
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error printing bill: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No bill available to print.");
        }
    }

}
