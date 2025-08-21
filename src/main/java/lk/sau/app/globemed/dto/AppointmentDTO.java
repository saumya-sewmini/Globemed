/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.dto;

import java.time.LocalDateTime;

/**
 *
 * @author Saumya
 */
public class AppointmentDTO {

    private int id;
    private int patientId;
    private int doctorId;
    private String patientName;
    private LocalDateTime appointmentDate;
    private String notes;

    public AppointmentDTO(int id, int patientId, int doctorId, String patientName, LocalDateTime appointmentDate, String notes) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getPatientName() {
        return patientName;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public String getNotes() {
        return notes;
    }

    

}
