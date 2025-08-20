/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.dao;

import jakarta.persistence.EntityManager;
import lk.sau.app.globemed.entity.User;
import lk.sau.app.globemed.util.HibernateUtil;

/**
 *
 * @author Saumya
 */
public class UserDAO {

    public User findByUsernameAndPassword(String username, String password) {

        EntityManager em = null;
        try {
            em = HibernateUtil.getSessionFactory().createEntityManager();
            return em.createQuery(
                    "SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :uname AND u.password = :pass",
                    User.class)
                    .setParameter("uname", username)
                    .setParameter("pass", password)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
