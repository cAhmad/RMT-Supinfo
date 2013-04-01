package com.supinfo.rmt.validator;

import com.supinfo.rmt.service.BundleService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Date;

/**
 * @author Ahmad
 * @version $Id$
 */
@FacesValidator(value = "dateOfBirthValidator")
public class DateOfBirthValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        if (value instanceof Date && ((Date) value).after(new Date())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    BundleService.getString("dateOfBirthError"), BundleService.getString("dateOfBirthError"));
            throw new ValidatorException(msg);
        }
    }
}