/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lk.sau.app.globemed.entity.Role;
import lk.sau.app.globemed.entity.User;
import lk.sau.app.globemed.util.HibernateUtil;

/**
 *
 * @author Saumya
 */
public class LoginTest {
    
    public static void main(String[] args) {
        
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = HibernateUtil.getSessionFactory().createEntityManager();
            tx = em.getTransaction();
 
            tx.begin();
            
//            User user = new User();
//            user.setUsername("admin");
//            user.setPassword("12345"); // in real projects, hash this!
//            user.setEmail("admin@gmail.com");
//            user.setPhone("0711234567");
//            
//            Role role = em.find(Role.class, 1);
//            user.setRole(role);
//            
//            em.persist(user);
//            tx.commit();
//
//            System.out.println("Test user created successfully!");

            String inputUsername = "admin";
            String inputPassword = "12345";

            User loggedIn = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :uname AND u.password = :pass",
                    User.class)
                    .setParameter("uname", inputUsername)
                    .setParameter("pass", inputPassword)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (loggedIn != null) {
                System.out.println("Login successful! User: " + loggedIn.getUsername()
                        + " | RoleId: " + loggedIn.getRole().getRoleName());
            } else {
                System.out.println("Login failed! Invalid username or password.");
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            HibernateUtil.shutdown();
        }
        
    }
    
}
