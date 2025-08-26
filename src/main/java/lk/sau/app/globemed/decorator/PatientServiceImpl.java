/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.decorator;

import lk.sau.app.globemed.dao.PatientDAO;
import lk.sau.app.globemed.entity.Patient;

/**
 *
 * @author Saumya
 */
public class PatientServiceImpl implements PatientService {

    private PatientDAO patientDAO = new PatientDAO();

    @Override
    public void savePatient(Patient patient) {
        patientDAO.savePatient(patient);  // normal save
    }

}
