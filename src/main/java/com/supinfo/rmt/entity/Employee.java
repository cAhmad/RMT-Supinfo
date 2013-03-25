package com.supinfo.rmt.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Ahmad
 * @version $Id$
 */
@Entity
@DiscriminatorValue(value= "employee")
public class Employee extends User implements Serializable {

    @ManyToOne
    private Manager manager;

    public Employee() {
    }

    public Employee(String username, String password, String firstName, String lastName, String email, Date dateOfBirth, Manager manager) {
        super(username, password, firstName, lastName, email, dateOfBirth);
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
