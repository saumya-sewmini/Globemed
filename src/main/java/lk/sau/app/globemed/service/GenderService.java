/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.service;

import java.util.List;
import lk.sau.app.globemed.entity.Gender;
import lk.sau.app.globemed.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Saumya
 */
public class GenderService {

    public List<Gender> loadGenders() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Gender> genders = session.createQuery("FROM Gender", Gender.class).list();
        session.close();
        return genders;
    }

}
