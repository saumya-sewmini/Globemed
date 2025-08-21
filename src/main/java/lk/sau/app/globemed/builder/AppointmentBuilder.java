/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.builder;

import java.time.LocalDateTime;
import java.util.Date;
import lk.sau.app.globemed.entity.Appointment;
import lk.sau.app.globemed.entity.Doctor;
import lk.sau.app.globemed.entity.Patient;
import lk.sau.app.globemed.entity.Status;
import lk.sau.app.globemed.entity.User;

/**
 *
 * @author Saumya
 */
public class AppointmentBuilder {
    
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentDate;
    private Status status;
    private String notes;

    public AppointmentBuilder setPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public AppointmentBuilder setDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public AppointmentBuilder setDate(LocalDateTime date) {
        this.appointmentDate = date;
        return this;
    }

    public AppointmentBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public AppointmentBuilder setNotes(String notes) {
        this.notes = notes;
        return this;
    }
    
    public Appointment build() {
        Appointment apptAppointment = new Appointment();
        apptAppointment.setPatient(patient);
        apptAppointment.setDoctor(doctor);
        apptAppointment.setAppointmentDate(appointmentDate);
        apptAppointment.setStatus(status);
        apptAppointment.setNotes(notes);
        return apptAppointment;
    }
    
}
