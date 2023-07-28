package org.pahappa.systems.kimanyisacco.views.Users;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.pahappa.systems.kimanyisacco.controllers.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.adminLogin;
import org.pahappa.systems.kimanyisacco.service.Userservice;
import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;

@ManagedBean(name = "adminBean")
@ViewScoped
public class admin {
    private adminLogin admin;
    private Userservice userservice;

    public admin() {
        this.admin = new adminLogin();
        this.userservice = new UserServiceImpl();
    }

    public adminLogin getAdmin() {
        return admin;
    }

    public void setAdmin(adminLogin admin) {
        this.admin = admin;
    }

    public Userservice getUserservice() {
        return userservice;
    }

    public void setUserservice(Userservice userservice) {
        this.userservice = userservice;
    }

    public void dologin() throws IOException {
        String id = admin.getAdminId();
        String password = admin.getPass();
        adminLogin check = (adminLogin) userservice.getAdmin(id);
        System.out.println(check.getPass());
        if (check.getAdminId().equals(id) && check.getPass().equals(password)) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("AdminID", check.getAdminId());
            String baseUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(baseUrl +
                    Hyperlinks.admiDashboard);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "Invalid credentials!"));
        }
    }

    public void logout() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            if (session != null) {
                session.invalidate();
                String baseUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(baseUrl + Hyperlinks.Home);
            }

        } catch (IOException e) {
            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logout Failed",
                    "An error occurred during logout.");
            FacesContext.getCurrentInstance().addMessage(null, errorMessage);
            e.printStackTrace();
        }
    }

}
