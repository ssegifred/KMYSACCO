package org.pahappa.systems.kimanyisacco.navigation;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Contains the links to the different pages with in the application.
 * It is to help us navigate between the pages in the application easily.
 */
@ManagedBean(name = "navigation")
@ApplicationScoped // There should be only one instance of the class created for the entire
                   // application
public class Navigation {

    private final String dashboard = "/pages/dashboard/Dashboard.xhtml";
    private final String adminlogin = "/pages/Admin/admin.xhtml";
    private final String admiDashboard = "/pages/Admin/admiDashboard2.xhtml";
    private final String login = "/pages/RegLogin/Login.xhtml";
    private final String Register = "/pages/RegLogin/Register.xhtml";
    private final String home = "/pages/home/Home.xhtml";

    private final String landing = "/pages/landing/Landing.xhtml";
    private final String edituser = "/pages/RegLogin/AddMember.xhtml";
    private final String memberedit = "/pages/RegLogin/EditMember.xhtml";

    public String getEdituser() {
        return edituser;
    }

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

    public String getHome() {
        return home;
    }

    public String getMemberedit() {
        return memberedit;
    }

}
