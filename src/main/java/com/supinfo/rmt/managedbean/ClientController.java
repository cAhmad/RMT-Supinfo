package com.supinfo.rmt.managedbean;

import com.supinfo.rmt.entity.Client;
import com.supinfo.rmt.service.BundleService;
import com.supinfo.rmt.service.ClientService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * @author Ahmad
 * @version $Id$
 */
@ManagedBean
@ViewScoped
public class ClientController implements Serializable {

    @EJB
    private ClientService clientService;

    private Client client;

    public ClientController() {
    }

    // ========================================
    // Utils
    // ========================================
    public String addClient() {
        clientService.addClient(client);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                BundleService.getString("clientCreateSuccess"), BundleService.getString("clientCreateSuccess"));
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "manager_home.jsf";
    }

    // ========================================
    // Accessor
    // ========================================
    public Client getClient() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
