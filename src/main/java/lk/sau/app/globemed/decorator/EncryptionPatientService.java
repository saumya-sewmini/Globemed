/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.decorator;

import java.util.Base64;
import lk.sau.app.globemed.entity.Patient;
import lk.sau.app.globemed.entity.User;
import lk.sau.app.globemed.util.EncryptionUtil;

/**
 *
 * @author Saumya
 */
public class EncryptionPatientService extends PatientServiceDecorator {

    public EncryptionPatientService(PatientService decoratedService) {
        super(decoratedService);
    }

    @Override
    public void savePatient(Patient patient) {
        // Encrypt sensitive fields
        if (patient.getUser().getPassword() != null) {
            patient.getUser().setPassword(EncryptionUtil.encrypt(patient.getUser().getPassword()));
        }

        if (patient.getUser().getEmail() != null) {
            patient.getUser().setEmail(EncryptionUtil.encrypt(patient.getUser().getEmail()));
        }

        if (patient.getUser().getPhone() != null) {
            patient.getUser().setPhone(EncryptionUtil.encrypt(patient.getUser().getPhone()));
        }

        super.savePatient(patient);
    }

}
