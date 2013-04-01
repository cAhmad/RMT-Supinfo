package com.supinfo.rmt.managedbean;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.WorkTime;
import com.supinfo.rmt.service.BundleService;
import com.supinfo.rmt.service.WorkTimeService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * @author Ahmad
 * @version $Id$
 */
@ManagedBean
@ViewScoped
public class EmployeeController implements Serializable {

    @EJB
    private WorkTimeService workTimeService;

    @ManagedProperty(value = "#{userController}")
    private UserController userController;

    private Employee employee;
    private DataModel<WorkTime> model;

    public EmployeeController() {
    }

    // ========================================
    // Utils
    // ========================================

    public String deleteWorkTime() throws IOException {
        WorkTime workTimeToDelete = model.getRowData();
        workTimeService.deleteWorkTime(workTimeToDelete);
        setModel(null);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                BundleService.getString("workTimeDeleteSuccess"), BundleService.getString("workTimeDeleteSuccess"));
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "";
    }

    // ========================================
    // Accessor
    // ========================================
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    public Employee getEmployee() throws IOException {
        if (employee == null) {
            employee = (Employee) userController.getUser();
        }
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public DataModel<WorkTime> getModel() throws IOException {
        if (model == null) {
            List<WorkTime> workTimes = workTimeService.getByEmployee((getEmployee()));
            model = new ListDataModel<WorkTime>(workTimes);
        }
        return model;
    }

    public void setModel(DataModel<WorkTime> model) {
        this.model = model;
    }
}
