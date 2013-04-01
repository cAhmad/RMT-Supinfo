package com.supinfo.rmt.validator;

import com.supinfo.rmt.service.BundleService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ahmad
 * @version $Id$
 */
@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

    private final Pattern pattern;

    public EmailValidator() {
        String emailPattern = "^[_A-Za-z0-9-]+(\\."
                + "[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(emailPattern);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Matcher matcher = pattern.matcher(value.toString());
        if(!matcher.matches()){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    BundleService.getString("emailErrorPattern"), BundleService.getString("emailErrorPattern"));
            throw new ValidatorException(msg);
        }
    }
}
