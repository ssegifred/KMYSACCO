package org.pahappa.systems.kimanyisacco.views.authentication;

import org.pahappa.systems.kimanyisacco.controllers.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;
import org.pahappa.systems.kimanyisacco.service.Userservice;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@ManagedBean(name = "loginForm")
@ViewScoped
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
        String member=user.getMemberId();
        String pass=user.getPassword();
        System.out.println(member);
        System.out.println(pass);
        List<User> userList = userservice.getAllUsers();
        for (User user1 : userList) {
            if (user1.getMemberId().equals(member) && (user1.getPassword().equals(pass))) {

                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("MemberID", user.getMemberId());
                String baseUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(baseUrl + Hyperlinks.dashboard);
            } else {
                String baseUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(baseUrl + Hyperlinks.login);


                System.out.println("Invalid credentials");
            }
        }
    }
    public void logout() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

            if (session != null) {
                session.invalidate();
            }

            String baseUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(baseUrl + Hyperlinks.Home);
        } catch (IOException e) {
            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logout Failed", "An error occurred during logout.");
            FacesContext.getCurrentInstance().addMessage(null, errorMessage);
            e.printStackTrace();
        }
    }

}
