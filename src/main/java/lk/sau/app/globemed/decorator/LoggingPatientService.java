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
public class LoggingPatientService extends PatientServiceDecorator{
    
    public LoggingPatientService(PatientService decoratedService) {
        super(decoratedService);
    }
    
    @Override
    public void savePatient(Patient patient) {
        System.out.println("[LOG] Saving patient: " + patient.getFname() + " " + patient.getLname());
        super.savePatient(patient);
        System.out.println("[LOG] Patient saved successfully with ID: " + patient.getPatientId());
    }
    
}
