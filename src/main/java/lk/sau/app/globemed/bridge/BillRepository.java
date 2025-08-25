/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.sau.app.globemed.bridge;

import lk.sau.app.globemed.entity.Bill;

/**
 *
 * @author Saumya
 */
public interface BillRepository {

    boolean save(Bill bill);

    boolean existsByMedicalRecordId(int medicalRecordId);

}
