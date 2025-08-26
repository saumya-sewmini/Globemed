/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.sau.app.globemed.builder;

import lk.sau.app.globemed.entity.Branch;
import lk.sau.app.globemed.entity.Role;
import lk.sau.app.globemed.entity.Staff;
import lk.sau.app.globemed.entity.User;
import org.hibernate.Session;

/**
 *
 * @author Saumya
 */
public class NurseBuilder {

    private String fname;
    private String lname;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Branch branch;

    public NurseBuilder setFirstName(String fname) {
        this.fname = fname;
        return this;
    }

    public NurseBuilder setLastName(String lname) {
        this.lname = lname;
        return this;
    }

    public NurseBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public NurseBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public NurseBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public NurseBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public NurseBuilder setBranch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public Staff build(Session session) {
        // Step 1: Create User
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);

        // Role: Nurse
        Role role = new Role();
        role.setRoleId(4);
        user.setRole(role);

        // Save User first
        session.persist(user);

        // Step 2: Create Staff
        Staff staff = new Staff();
        staff.setUser(user);
        staff.setFname(fname);
        staff.setLname(lname);
        staff.setBranch(branch);

        // Save Staff
        session.persist(staff);

        return staff;
    }

}
