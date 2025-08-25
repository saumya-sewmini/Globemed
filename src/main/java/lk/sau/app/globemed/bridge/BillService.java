/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.bridge;

import lk.sau.app.globemed.entity.Bill;

/**
 *
 * @author Saumya
 */
public abstract class BillService {

    protected BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public abstract boolean saveBill(Bill bill);

}
