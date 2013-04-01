package com.supinfo.rmt.managedbean;

import com.supinfo.rmt.entity.Client;
import com.supinfo.rmt.service.ClientService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
        return "manager_home.jsf?faces-redirect=true";
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
