/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
/**
 *
 * @author Saumya
 */
@Entity
@Table(name = "patients")
public class Patient {
    
    @Id
    @Column(name = "patient_id")
    private Integer patientId;   // same as user_id

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "patient_id")  
    private User user;

    @Column(name = "fname", length = 45, nullable = false)
    private String fname;

    @Column(name = "lname", length = 45, nullable = false)
    private String lname;

    @Column(name = "dob")
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    
    
}
