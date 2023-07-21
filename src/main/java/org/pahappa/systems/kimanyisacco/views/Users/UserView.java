package org.pahappa.systems.kimanyisacco.views.Users;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;
import org.pahappa.systems.kimanyisacco.service.Userservice;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "userView")
@ViewScoped
public class UserView {

    private final User user;
    List<User> userList;
    private Userservice userservice;

    public UserView() {
        this.user = new User();
        this.userservice=new UserServiceImpl();
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
        userList=userservice.getAllUsers();
        return userList;
    }
    public void deleteMember(User user){
        System.out.println(user.getMemberId());
        userservice.deleteMember(user.getMemberId());
        userList.remove(user);
    }
}
