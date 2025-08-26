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
public class AuthHandlerDecorator extends AuthHandler{
    
    protected AuthHandler wrappedHandler;

    public AuthHandlerDecorator(AuthHandler wrappedHandler) {
        this.wrappedHandler = wrappedHandler;
    }

    @Override
    public void handle(User user) {
        if (wrappedHandler != null) {
            wrappedHandler.handle(user);
        }
    }
    
}
