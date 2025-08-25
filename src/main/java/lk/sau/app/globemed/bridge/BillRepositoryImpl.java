/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.bridge;

import lk.sau.app.globemed.entity.Bill;
import lk.sau.app.globemed.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Saumya
 */
public class BillRepositoryImpl implements BillRepository {

    @Override
    public boolean save(Bill bill) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(bill);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean existsByMedicalRecordId(int medicalRecordId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(b) FROM Bill b WHERE b.medicalRecord.id = :medicalRecordId", Long.class
            );
            query.setParameter("medicalRecordId", medicalRecordId);
            return query.uniqueResult() > 0;
        }
    }

}
