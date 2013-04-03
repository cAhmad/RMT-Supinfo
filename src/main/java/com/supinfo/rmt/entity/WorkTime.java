package com.supinfo.rmt.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Ahmad
 * @version $Id$
 */
@Entity
public class WorkTime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{constraints.workTime.beginDate.notnull}")
    @Temporal(TemporalType.DATE)
    private Date beginDate;

    @NotNull(message = "{constraints.workTime.endDate.notnull}")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @NotNull(message = "{constraints.workTime.client.notnull}")
    @OneToOne
    private Client client = new Client();

    @NotNull(message = "{constraints.workTime.employee.notnull}")
    @ManyToOne
    private Employee employee;

    public WorkTime() {
    }

    public WorkTime(Date beginDate, Date endDate, Client client) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
