/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.decorator;

import lk.sau.app.globemed.entity.Patient;

/**
 *
 * @author Saumya
 */
public class PatientServiceDecorator implements PatientService {

    protected PatientService decoratedService;

    public PatientServiceDecorator(PatientService decoratedService) {
        this.decoratedService = decoratedService;
    }

    @Override
    public void savePatient(Patient patient) {
        decoratedService.savePatient(patient);
    }

}
