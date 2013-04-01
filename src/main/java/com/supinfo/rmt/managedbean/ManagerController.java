package com.supinfo.rmt.managedbean;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.service.BundleService;
import com.supinfo.rmt.service.EmployeeService;
import org.apache.commons.codec.digest.DigestUtils;

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
public class ManagerController implements Serializable {

    @EJB
    private EmployeeService employeeService;

    @ManagedProperty(value = "#{userController}")
    private UserController userController;

    private Employee employee;
    private Manager manager;

    private DataModel<Employee> model;
    private final String employeeId;

    public ManagerController() {
        employeeId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
    }

    // ========================================
    // Utils
    // ========================================
    public String addEmployee() throws IOException {
        // Hash password
        getEmployee().setPassword(DigestUtils.sha1Hex(getEmployee().getPassword()));
        getEmployee().setManager(getManager());

        final String message;
        if(employeeId == null || employeeId.isEmpty()) {
            employeeService.addEmployee(getEmployee());
            message = BundleService.getString("employeeCreateSuccess");
        } else {
            employeeService.editEmployee(getEmployee());
            message = BundleService.getString("employeeEditSuccess");
        }
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                message, message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        return "manager_home.jsf";
    }

    public String editEmployee() throws IOException {
        Employee employeeToEdit = model.getRowData();
        FacesContext.getCurrentInstance().getExternalContext().redirect("add_employee.jsf?id=" + employeeToEdit.getId());
        return "";
    }

    // ========================================
    // Accessor
    // ========================================
    public Employee getEmployee() {
        if (employee == null) {
            if (employeeId != null && !employeeId.isEmpty()) {
                employee = employeeService.getById(Long.valueOf(employeeId));
            } else {
                employee = new Employee();
            }
        }
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Manager getManager() {
        if(manager == null) {
            manager = (Manager) userController.getUser();
        }
        return manager;
    }

    public DataModel<Employee> getModel() throws IOException {
        if(model == null) {
            List<Employee> employees = employeeService.getByManager(getManager());
            model = new ListDataModel<Employee>(employees);
        }
        return model;
    }

    public void setModel(DataModel<Employee> model) {
        this.model = model;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}
