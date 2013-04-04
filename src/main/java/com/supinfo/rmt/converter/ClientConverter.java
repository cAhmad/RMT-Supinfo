package com.supinfo.rmt.converter;

import com.supinfo.rmt.entity.Client;
import com.supinfo.rmt.service.ClientService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author Ahmad
 * @version $Id$
 */
@FacesConverter(value = "clientConverter")
@ManagedBean
public class ClientConverter implements Converter {

    @EJB
    private ClientService clientService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String newValue) {
        try {
            return clientService.getById(Long.valueOf(newValue));
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if(value == null) {
            return "";
        } else if (value instanceof Client) {
            return ((Client) value).getId().toString();
        } else {
            return value.toString();
        }
    }
}
