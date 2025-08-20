/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.auth;

import gui.DoctorDashboard;
import javax.swing.JOptionPane;
import lk.sau.app.globemed.entity.User;

/**
 *
 * @author Saumya
 */
public class DoctorHandler extends AuthHandler{

    @Override
    public void handle(User user) {
        
        if (user.getRole().getRoleName().equalsIgnoreCase("doctor")) {
            new DoctorDashboard().setVisible(true);
        } else if (nextHandler != null) {
            nextHandler.handle(user);
        } else {
            JOptionPane.showMessageDialog(null, "Unknown role: " + user.getRole().getRoleName());
        }
    }
    
}
