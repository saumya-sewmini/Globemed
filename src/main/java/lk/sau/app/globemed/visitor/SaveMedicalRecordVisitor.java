/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.visitor;

import java.time.LocalDateTime;
import lk.sau.app.globemed.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.sau.app.globemed.entity.MedicalRecord;

/**
 *
 * @author Saumya
 */
public class SaveMedicalRecordVisitor implements MedicalRecordVisitor {

    @Override
    public void visit(MedicalRecord medicalRecord) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            medicalRecord.setRecordDate(LocalDateTime.now()); // auto timestamp
            session.persist(medicalRecord);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
