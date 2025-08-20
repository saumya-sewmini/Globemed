/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.entity;

import jakarta.persistence.*;

/**
 *
 * @author Saumya
 */
@Entity
@Table(name = "prescription")
public class Prescription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medical_records_id", nullable = false)
    private MedicalRecord medicalRecords;

    @Column(name = "medicine_name", nullable = false, length = 100)
    private String medicineName;

    @Column(name = "dosage", length = 45)
    private String dosage;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MedicalRecord getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(MedicalRecord medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    
    
}
