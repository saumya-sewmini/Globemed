/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.dao;

import java.util.List;
import lk.sau.app.globemed.entity.Doctor;
import lk.sau.app.globemed.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Saumya
 */
public class DoctorDAO {

    public List<Doctor> findAll() {
        List<Doctor> doctors = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            doctors = session.createQuery("FROM Doctor", Doctor.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctors;
    }

    public Doctor findByDisplayName(String displayName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Doctor> doctors = session.createQuery("FROM Doctor", Doctor.class).list();

            for (Doctor d : doctors) {
                String fullName = d.getFname() + " " + d.getLname() + " (" + d.getSpecialization() + ")";
                if (fullName.equals(displayName)) {
                    return d;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
