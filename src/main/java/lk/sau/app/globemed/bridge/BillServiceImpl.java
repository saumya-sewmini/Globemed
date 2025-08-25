/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.bridge;

import javax.swing.JOptionPane;
import lk.sau.app.globemed.entity.Bill;

/**
 *
 * @author Saumya
 */
public class BillServiceImpl extends BillService {

    public BillServiceImpl(BillRepository billRepository) {
        super(billRepository);
    }

    @Override
    public boolean saveBill(Bill bill) {
        if (billRepository.existsByMedicalRecordId(bill.getMedicalRecord().getId())) {
            JOptionPane.showMessageDialog(null, "This bill has been paid.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return billRepository.save(bill);
    }

}
