/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.auth;

import lk.sau.app.globemed.entity.User;

/**
 *
 * @author Saumya
 */
public class AuthProcessor {
    
    private AuthHandler handlerChain;
    
    public AuthProcessor(){
        AuthHandler admin = new AdminHandler();
        AuthHandler doctor = new DoctorHandler();
        AuthHandler patient = new PatientHandler();

        admin.setNextHandler(doctor);
        doctor.setNextHandler(patient);

        this.handlerChain = admin;
    }
    
    public void process(User user){
        handlerChain.handle(user);
    }
    
}
