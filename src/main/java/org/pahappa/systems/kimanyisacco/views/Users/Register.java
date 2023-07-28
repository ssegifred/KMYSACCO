package org.pahappa.systems.kimanyisacco.views.Users;

import java.io.IOException;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.pahappa.systems.kimanyisacco.controllers.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.service.Userservice;
import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;

@ManagedBean(name = "register")
@ViewScoped
public class Register {
    private final User user;

    private Userservice userservice;
    private User currentUser;

    public Register() {
        this.user = new User();
        this.userservice = new UserServiceImpl();
    }

    public void generateMemberId() {
        // Get the first character of the member's name
        char firstCharacter = user.getLastname().charAt(0);

        // Generate a random integer between 100 and 999
        int randomInt = new Random().nextInt(900) + 100;

        // Create the Member ID using the first character followed by the random integer
        String memberid = firstCharacter + String.valueOf(randomInt);
        user.setMemberId(memberid);
        System.out.println("Generated Member ID: " + memberid);
    }

    // Getters and Setters for user and userservice

    public void registerUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        String session = (String) context.getExternalContext().getSessionMap().get("AdminID");
        String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        userservice.saveUser(user);
        if (session != null) {

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(base + Hyperlinks.admiDashboard);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(base + Hyperlinks.login);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // Add the setter for userservice

    public User getUser() {
        return user;
    }

    public Userservice getUserservice() {
        return userservice;
    }

    public void setUserservice(Userservice userservice) {
        this.userservice = userservice;
    }

    public User getCurrentUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        return (User) externalContext.getSessionMap().get("Member");
    }

    public void updateUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        User member = (User) context.getExternalContext().getSessionMap().get("Member");
        String id = member.getMemberId();
        user.setMemberId(id);
        System.out.println(id);
        boolean update = userservice.updateUser(user);
        if (update) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(base + Hyperlinks.dashboard);
            } catch (IOException e) {

                System.out.println("Path not found");
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed", "You session expired!"));
        }
    }
}
