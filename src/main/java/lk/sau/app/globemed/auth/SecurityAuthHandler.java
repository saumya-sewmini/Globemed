/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.auth;

import javax.swing.JOptionPane;
import lk.sau.app.globemed.entity.User;

/**
 *
 * @author Saumya
 */
public class SecurityAuthHandler extends AuthHandlerDecorator {

    public SecurityAuthHandler(AuthHandler wrappedHandler) {
        super(wrappedHandler);
    }

//    @Override
//    public void handle(User user) {
//        if (user.getStatus().equalsIgnoreCase("blocked")) {
//            JOptionPane.showMessageDialog(null,
//                    "Your account is blocked. Contact admin.");
//            return; // Stop the chain
//        }
//        super.handle(user);
//    }

}
