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
public abstract class AuthHandler {
    
    protected AuthHandler nextHandler;

    public void setNextHandler(AuthHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public abstract void handle(User user);
    
}
