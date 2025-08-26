/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.dao;

import java.util.List;
import lk.sau.app.globemed.entity.Staff;
import lk.sau.app.globemed.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Saumya
 */
public class StaffDAO {

    public List<Staff> findByRoleIds(List<Integer> roleIds) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "SELECT s FROM Staff s "
                    + "JOIN FETCH s.user u "
                    + "JOIN FETCH u.role r "
                    + "JOIN FETCH s.branch b "
                    + "WHERE r.roleId IN (:roles)", Staff.class)
                    .setParameter("roles", roleIds)
                    .list();
        }
    }

}
