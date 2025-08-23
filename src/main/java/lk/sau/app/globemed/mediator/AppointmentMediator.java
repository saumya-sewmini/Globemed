/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.sau.app.globemed.mediator;

import java.time.LocalDate;
import java.util.List;
import lk.sau.app.globemed.entity.Appointment;
import lk.sau.app.globemed.entity.Doctor;

/**
 *
 * @author Saumya
 */
public interface AppointmentMediator {

    void bookAppointment(int patientId, int doctorId, LocalDate date, String time);

    List<Doctor> getAllDoctors();

    List<Appointment> getAppointmentsForPatient(int patientId);

    List<Appointment> getCompletedAppointmentsByDoctor(int doctorId);

}
