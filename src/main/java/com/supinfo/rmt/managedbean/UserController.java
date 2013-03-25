package com.supinfo.rmt.managedbean;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.User;
import com.supinfo.rmt.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * @author Ahmad
 * @version $Id$
 */
@ManagedBean
@SessionScoped
public class UserController implements Serializable {

    @EJB
    private UserService userService;

    private User user;

    public UserController() {
    }

    // ========================================
    // Utils
    // ========================================
    public String authenticateUser() {
        user = userService.authenticate(user.getUsername(), user.getPassword());
        if(user == null) {
            return "";
        } else if(user instanceof Employee) {
            return "./employee/employee_home.jsf?faces-redirect=true";
        } else if(user instanceof Manager) {
            return "./manager/manager_home.jsf?faces-redirect=true";
        }
        return "";
    }

    // ========================================
    // Accessor
    // ========================================

    public User getUser() {
        if(user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
