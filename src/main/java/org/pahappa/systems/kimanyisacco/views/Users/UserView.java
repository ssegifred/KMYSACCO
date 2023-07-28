package org.pahappa.systems.kimanyisacco.views.Users;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.service.Userservice;
import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;

@ManagedBean(name = "userView")
@ViewScoped
public class UserView {

    private final User user;
    List<User> userList;
    private Userservice userservice;

    public UserView() {
        this.user = new User();
        this.userservice = new UserServiceImpl();
    }

    public User getUser() {
        return user;
    }

    public Userservice getUserservice() {
        return userservice;
    }

    public void setUserservice(Userservice userservice) {
        this.userservice = userservice;
    }

    public List<User> getAllUsers() {
        userList = userservice.getAllUsers();
        return userList;
    }

    public void deleteMember(User user) {
        System.out.println(user.getMemberId());
        boolean check = userservice.deleteMember(user.getMemberId());
        if (check) {
            userList.remove(user);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Member Removed successfully"));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed", "Member has some balance!"));

        }
    }
}
