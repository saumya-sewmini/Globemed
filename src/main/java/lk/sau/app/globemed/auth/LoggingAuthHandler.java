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
public class LoggingAuthHandler extends AuthHandlerDecorator{
    
    public LoggingAuthHandler(AuthHandler wrappedHandler) {
        super(wrappedHandler);
    }
    
    @Override
    public void handle(User user) {
        System.out.println("[LOG] Handling user role: " + user.getRole().getRoleName());
        super.handle(user);
        System.out.println("[LOG] Finished handling user: " + user.getUsername());
    }
    
}
