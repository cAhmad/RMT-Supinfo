package com.supinfo.rmt.managedbean;

import com.supinfo.rmt.entity.Client;
import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.WorkTime;
import com.supinfo.rmt.managedbean.UserController;
import com.supinfo.rmt.service.BundleService;
import com.supinfo.rmt.service.ClientService;
import com.supinfo.rmt.service.WorkTimeService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahmad
 * @version $Id$
 */
@ManagedBean
@ViewScoped
public class WorkTimeController implements Serializable {

    @EJB
    private WorkTimeService workTimeService;
    @EJB
    private ClientService clientService;

    @ManagedProperty(value = "#{userController}")
    private UserController userController;

    private WorkTime workTime;
    private List<SelectItem> selectItems = new ArrayList<SelectItem>();

    @PostConstruct
    public void init() {
        List<Client> clients = clientService.getAll();
        selectItems.add(new SelectItem(null, "--- " + BundleService.getString("workTimeSelectAClient") + " ---"));
        for (Client client : clients) {
            selectItems.add(new SelectItem(client, client.getName()));
        }
    }

    // ========================================
    // Utils
    // ========================================
    public String addWorkTime() throws IOException {
        workTime.setEmployee((Employee) userController.getUser());
        workTimeService.addWorkTime(workTime);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                BundleService.getString("workTimeCreateSuccess"), BundleService.getString("workTimeCreateSuccess"));
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "employee_home.jsf";
    }

    // ========================================
    // Accessor
    // ========================================
    public WorkTime getWorkTime() {
        if (workTime == null) {
            workTime = new WorkTime();
        }
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}
