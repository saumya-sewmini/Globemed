/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lk.sau.app.globemed.visitor.MedicalRecordVisitor;
import lk.sau.app.globemed.visitor.SaveMedicalRecordVisitor;
import lk.sau.app.globemed.visitor.VisitableMedicalRecord;

/**
 *
 * @author Saumya
 */
@Entity
@Table(name = "medical_records")
public class MedicalRecord implements VisitableMedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "record_date", nullable = false, updatable = false)
    private LocalDateTime recordDate;

    @ManyToOne
    @JoinColumn(name = "treatment_type_id", nullable = false)
    private TreatmentType treatmentTypeId;

    @Column(name = "medicine", columnDefinition = "TEXT")
    private String medicine;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public TreatmentType getTreatmentTypeId() {
        return treatmentTypeId;
    }

    public void setTreatmentTypeId(TreatmentType treatmentTypeId) {
        this.treatmentTypeId = treatmentTypeId;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public void accept(MedicalRecordVisitor visitor) {
        visitor.visit(this);
    }

}
