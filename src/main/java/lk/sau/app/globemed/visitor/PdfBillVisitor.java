/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.visitor;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import lk.sau.app.globemed.entity.Bill;
import lk.sau.app.globemed.entity.MedicalRecord;
import lk.sau.app.globemed.entity.Patient;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 *
 * @author Saumya
 */
public class PdfBillVisitor implements BillVisitor {

    @Override
    public void visit(Bill bill) {

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            float margin = 50;
            float yStart = page.getMediaBox().getHeight() - 70;
            float pageWidth = page.getMediaBox().getWidth();

            PDPageContentStream cs = new PDPageContentStream(document, page);

            // ===== HEADER =====
            cs.setFont(PDType1Font.HELVETICA_BOLD, 20);
            cs.beginText();
            cs.newLineAtOffset((pageWidth / 2) - 60, yStart);
            cs.showText("Patient Bill");
            cs.endText();
            yStart -= 50;

            // ===== BILL INFO =====
            Patient patient = bill.getPatient();
            MedicalRecord record = bill.getMedicalRecord();

            cs.setFont(PDType1Font.HELVETICA, 12);
            String[] billDetails = {
                "Appointment ID: " + record.getId(),
                "Patient Name: " + patient.getFname() + " " + patient.getLname(),
                "Gender: " + patient.getGender().getGender(),
                "Phone: " + patient.getUser().getPhone(),
                "Doctor: " + record.getDoctor().getFname() + " " + record.getDoctor().getLname(),
                "Amount: " + bill.getAmount(),
                "Payment Type: " + bill.getPaymentType().getPaymentType(),
                "Billing Date: " + bill.getBillingDate().toString()
            };

            for (String line : billDetails) {
                cs.beginText();
                cs.newLineAtOffset(margin, yStart);
                cs.showText(line);
                cs.endText();
                yStart -= 20;
            }

            cs.close();

            // ===== PREVIEW =====
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            byte[] pdfBytes = baos.toByteArray();

            PDDocument docForRender = PDDocument.load(pdfBytes);
            PDFRenderer renderer = new PDFRenderer(docForRender);
            BufferedImage image = renderer.renderImageWithDPI(0, 150);
            docForRender.close();

            JLabel pdfLabel = new JLabel(new ImageIcon(image));
            JScrollPane scrollPane = new JScrollPane(pdfLabel);

            JFrame frame = new JFrame("Bill Preview");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(700, 600);
            frame.setLocationRelativeTo(null);

            JPanel buttonPanel = new JPanel();
            JButton printBtn = new JButton("Print Bill");
            JButton saveBtn = new JButton("Save Bill");

            buttonPanel.add(printBtn);
            buttonPanel.add(saveBtn);

            frame.setLayout(new BorderLayout());
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setVisible(true);

            // ===== PRINT =====
            printBtn.addActionListener(e -> {
                try {
                    PDDocument docPrint = PDDocument.load(pdfBytes);
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setJobName("Patient Bill");
                    job.setPrintable(new PDFPrintable(docPrint, Scaling.SHRINK_TO_FIT));
                    if (job.printDialog()) {
                        job.print();
                    }
                    docPrint.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Printing Failed: " + ex.getMessage());
                }
            });

            // ===== SAVE =====
            saveBtn.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(new File("Bill_" + bill.getId() + ".pdf"));
                int option = chooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    try (FileOutputStream fos = new FileOutputStream(chooser.getSelectedFile())) {
                        fos.write(pdfBytes);
                        JOptionPane.showMessageDialog(frame, "Bill Saved Successfully ✅");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Save Failed: " + ex.getMessage());
                    }
                }
            });

            System.out.println("Bill PDF Generated & Preview Opened ✅");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
