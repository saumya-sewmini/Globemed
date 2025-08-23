/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.dao;

import java.util.List;
import lk.sau.app.globemed.dto.AppointmentDTO;
import lk.sau.app.globemed.entity.Appointment;
import lk.sau.app.globemed.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Saumya
 */
public class AppointmentDAO {

    public void save(Appointment appointment) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(appointment);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Appointment> findByPatientId(int patientId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Appointment a WHERE a.patient.id = :patientId";
            Query<Appointment> query = session.createQuery(hql, Appointment.class);
            query.setParameter("patientId", patientId);
            return query.list();
        }
    }

    public List<AppointmentDTO> findCompletedAppointmentsByDoctor(int doctorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT new lk.sau.app.globemed.dto.AppointmentDTO("
                    + "a.id, p.id, a.doctor.id, "
                    + // 👈 add patientId and doctorId
                    "CONCAT(p.fname, ' ', p.lname), "
                    + "a.appointmentDate, a.notes) "
                    + "FROM Appointment a "
                    + "JOIN a.patient p "
                    + "JOIN a.status s "
                    + "WHERE a.doctor.id = :doctorId AND s.status = 'Completed'";

            return session.createQuery(hql, AppointmentDTO.class)
                    .setParameter("doctorId", doctorId)
                    .getResultList();
        }
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Appointment a WHERE a.patient.patientId = :pid", Appointment.class)
                    .setParameter("pid", patientId)
                    .list();
        }
    }

}
