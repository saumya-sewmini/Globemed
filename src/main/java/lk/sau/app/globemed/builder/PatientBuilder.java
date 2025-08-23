/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.builder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import lk.sau.app.globemed.entity.Gender;
import lk.sau.app.globemed.entity.Patient;
import lk.sau.app.globemed.entity.Role;
import lk.sau.app.globemed.entity.User;
import org.hibernate.Session;

/**
 *
 * @author Saumya
 */
public class PatientBuilder {

    private String fname;
    private String lname;
    private LocalDate dob;
    private Gender gender;
    private String username;
    private String password;
    private String email;
    private String phone;

    public PatientBuilder setFirstName(String fname) {
        this.fname = fname;
        return this;
    }

    public PatientBuilder setLastName(String lname) {
        this.lname = lname;
        return this;
    }

    public PatientBuilder setDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public PatientBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public PatientBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public PatientBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public PatientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public PatientBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Patient build() {

        // Create User
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);

        // Assign role = Patient
        Role role = new Role();
        role.setRoleId(3);
        user.setRole(role);

        // Create Patient
        Patient patient = new Patient();
        patient.setFname(fname);
        patient.setLname(lname);
        patient.setDob(dob);
        patient.setGender(gender);
        patient.setUser(user);

        System.out.println("Patient saved with ID: " + patient.getPatientId());

        return patient;

    }

}
