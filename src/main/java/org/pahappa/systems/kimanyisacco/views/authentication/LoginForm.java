package org.pahappa.systems.kimanyisacco.views.authentication;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.pahappa.systems.kimanyisacco.controllers.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.service.Userservice;
import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;

@ManagedBean(name = "loginForm")
@SessionScoped
public class LoginForm {

    private User user;
    private final Userservice userservice;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginForm() {

        this.user = new User();
        this.userservice = new UserServiceImpl();
    }

    public void doLogin() throws IOException {
        String member = user.getMemberId();
        String pass = user.getPassword();

        User user1 = userservice.getMember(member);
        if ((user1 != null) && user1.getMemberId().equals(member) && BCrypt.checkpw(pass, user1.getPassword())) {
            if (user1.getStatus() == 0) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                        .getSession(true);
                session.setAttribute("Member", user1);
                String baseUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(baseUrl + Hyperlinks.dashboard);
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed", "Your account is frozen!Contact admin"));
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed", "Invalid credentials!"));
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
