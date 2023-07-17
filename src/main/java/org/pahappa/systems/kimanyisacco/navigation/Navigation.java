package org.pahappa.systems.kimanyisacco.navigation;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Contains the links to the different pages with in the application.
 * It is to help us navigate between the pages in the application easily.
 */
@ManagedBean(name = "navigation")
@ApplicationScoped //There should be only one instance of the class created for the entire application
public class Navigation {

    private final String dashboard = "/pages/dashboard/Dashboard.xhtml";
    private final String adminlogin="/pages/Admin/admin.xhtml";
    private final String admiDashboard="/pages/Admin/admiDashboard2.xhtml";
    private final String login="/pages/RegLogin/Login.xhtml";
    private final String Register="/pages/RegLogin/Register.xhtml";

    private final String landing = "/pages/landing/Landing.xhtml";

    public String getDashboard() {
        return dashboard;
    }

    public String getLanding() {
        return landing;
    }

    public String getAdminlogin() {
        return adminlogin;
    }

    public String getAdmiDashboard() {
        return admiDashboard;
    }

    public String getLogin() {
        return login;
    }

    public String getRegister() {
        return Register;
    }
}
