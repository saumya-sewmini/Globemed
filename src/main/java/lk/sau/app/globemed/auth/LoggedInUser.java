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
public class LoggedInUser {

    private static User currentUser;

    // Set logged in user
    public static void setUser(User user) {
        currentUser = user;
    }

    // Get logged in user (anywhere in app)
    public static User getUser() {
        return currentUser;
    }

    // Clear when logging out
    public static void clear() {
        currentUser = null;
    }

}
