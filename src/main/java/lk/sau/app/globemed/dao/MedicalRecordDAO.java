/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.dao;

import java.util.List;
import lk.sau.app.globemed.entity.MedicalRecord;
import lk.sau.app.globemed.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Saumya
 */
public class MedicalRecordDAO {

    public List<MedicalRecord> findByDoctorAndPatient(int doctorId, int patientId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MedicalRecord> query = session.createQuery(
                    "FROM MedicalRecord WHERE doctor.id = :doctorId AND patient.id = :patientId",
                    MedicalRecord.class
            );
            query.setParameter("doctorId", doctorId);
            query.setParameter("patientId", patientId);
            return query.getResultList();
        }
    }

    public List<MedicalRecord> findByPatient(int patientId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MedicalRecord> query = session.createQuery(
                    "FROM MedicalRecord WHERE patient.id = :patientId ORDER BY recordDate DESC",
                    MedicalRecord.class
            );
            query.setParameter("patientId", patientId);
            return query.getResultList();
        }
    }

    public MedicalRecord findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(MedicalRecord.class, id);
        }
    }

}
