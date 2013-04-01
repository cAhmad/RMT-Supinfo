package com.supinfo.rmt.service;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * @author Ahmad
 * @version $Id$
 */
public class BundleService implements Serializable {
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("com.supinfo.rmt.lang.i18n");

    private BundleService() {
    }

    public static String getString(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
