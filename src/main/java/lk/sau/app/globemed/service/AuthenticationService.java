/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.service;

import lk.sau.app.globemed.auth.AuthProcessor;
import lk.sau.app.globemed.auth.LoggedInUser;
import lk.sau.app.globemed.dao.UserDAO;
import lk.sau.app.globemed.entity.User;

/**
 *
 * @author Saumya
 */
public class AuthenticationService {
    
    private UserDAO userDAO;
    private AuthProcessor authProcessor;

    public AuthenticationService() {
        this.userDAO = new UserDAO();
        this.authProcessor = new AuthProcessor();
    }
    
    public void login(String username, String password) {
        User user = userDAO.findByUsernameAndPassword(username, password);

        if (user != null) {
            LoggedInUser.setUser(user);
            
            System.out.println("Login successful: " + user.getUsername());
            
            authProcessor.process(user);
        } else {
            System.out.println("Invalid username or password!");
        }
    }
    
}
