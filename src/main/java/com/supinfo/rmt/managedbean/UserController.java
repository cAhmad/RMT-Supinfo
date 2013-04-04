package com.supinfo.rmt.managedbean;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.User;
import com.supinfo.rmt.service.BundleService;
import com.supinfo.rmt.service.UserService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

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

    private static Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public UserController() {
    }

    // ========================================
    // Utils
    // ========================================
    public String authenticateUser() {
        user = userService.authenticate(user.getUsername(), user.getPassword());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
        if(user == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    BundleService.getString("loginError"), BundleService.getString("loginError"));
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "";
        } else if(user instanceof Employee) {
            return "./employee/employee_home.jsf?faces-redirect=true";
        } else if(user instanceof Manager) {
            return "./manager/manager_home.jsf?faces-redirect=true";
        }
        return "";
    }

    public String logout() throws IOException {
        setUser(null);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getSessionMap().clear();
        externalContext.redirect(getLoginPage());
        return "";
    }

    public String getLoginPage() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/faces/login.jsf";
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

    public static Locale getCurrentLocale() {
        if (currentLocale == null) {
            return Locale.FRANCE;
        }
        return currentLocale;
    }
}
