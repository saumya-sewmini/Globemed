/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.mediator;

import java.time.LocalDate;
import java.util.List;
import lk.sau.app.globemed.builder.Email;
import lk.sau.app.globemed.builder.EmailBuilder;
import lk.sau.app.globemed.dao.AppointmentDAO;
import lk.sau.app.globemed.entity.Appointment;
import lk.sau.app.globemed.entity.Doctor;
import lk.sau.app.globemed.entity.Patient;
import lk.sau.app.globemed.entity.Status;
import lk.sau.app.globemed.service.EmailService;
import lk.sau.app.globemed.util.EncryptionUtil;
import lk.sau.app.globemed.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Saumya
 */
public class AppointmentMediatorImpl implements AppointmentMediator {

    private AppointmentDAO appointmentDAO = new AppointmentDAO();

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
                
                String decryptedEmail = EncryptionUtil.decrypt(patient.getUser().getEmail());

                Email email = new EmailBuilder()
                        .to(decryptedEmail)
                        .subject("Appointment Confirmation")
                        .body("Dear " + patient.getFname() + ",\n\n"
                                + "Your appointment with Dr. " + doctor.getFname() +" "+ doctor.getLname()
                                + " on " + date + " at " + time + " has been sheduled.\n\n"
                                + "Thank you.")
                        .build();

                // Send email
                EmailService emailService = new EmailService();
                emailService.sendEmail(email);
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

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) {
        return appointmentDAO.getAppointmentsByPatientId(patientId);
    }

    @Override
    public List<Appointment> getCompletedAppointmentsByDoctor(int doctorId) {
        return appointmentDAO.getAppointmentsByDoctorAndStatus(doctorId, 2);
    }

}
