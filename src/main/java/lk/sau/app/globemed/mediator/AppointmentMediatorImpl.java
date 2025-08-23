/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.mediator;

import java.time.LocalDate;
import java.util.List;
import lk.sau.app.globemed.entity.Appointment;
import lk.sau.app.globemed.entity.Doctor;
import lk.sau.app.globemed.entity.Patient;
import lk.sau.app.globemed.entity.Status;
import lk.sau.app.globemed.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Saumya
 */
public class AppointmentMediatorImpl implements AppointmentMediator {

    @Override
    public void bookAppointment(int patientId, int doctorId, LocalDate date, String time) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // Load entities
            Patient patient = session.get(Patient.class, patientId);
            Doctor doctor = session.get(Doctor.class, doctorId);
            Status status = session.get(Status.class, 1); // default = 1

            if (patient != null && doctor != null && status != null) {
                Appointment appointment = new Appointment();
                appointment.setPatient(patient);
                appointment.setDoctor(doctor);
                appointment.setStatus(status);
                appointment.setAppointmentDate(date.atStartOfDay());
                appointment.setAppointmentTime(time);

                session.persist(appointment);
                tx.commit();

                System.out.println("Appointment booked successfully for patient: " + patient.getFname());
            } else {
                System.out.println("Invalid patient/doctor/status data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Doctor", Doctor.class).list();
        }
    }

}
