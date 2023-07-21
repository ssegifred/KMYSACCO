package org.pahappa.systems.kimanyisacco.views.Users;

import org.pahappa.systems.kimanyisacco.controllers.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;
import org.pahappa.systems.kimanyisacco.service.Userservice;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Random;

@ManagedBean(name = "register")
@ViewScoped
public class Register {
    private final User user;

    private Userservice userservice;

    public Register() {
        this.user = new User();
        this.userservice=new UserServiceImpl();
    }
    public void generateMemberId() {
        // Get the first character of the member's name
        char firstCharacter = user.getLastname().charAt(0);

        // Generate a random integer between 100 and 999
        int randomInt = new Random().nextInt(900) + 100;

        // Create the Member ID using the first character followed by the random integer
      String memberid  = firstCharacter + String.valueOf(randomInt);
        user.setMemberId(memberid);

        System.out.println("Generated Member ID: " + memberid);
    }

    // Getters and Setters for user and userservice

    public void registerUser() {
        System.out.println("Called");
        userservice.saveUser(user);
        String base= FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.dashboard);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
}
