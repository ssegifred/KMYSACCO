// package org.pahappa.systems.kimanyisacco.views.Users;

// import org.pahappa.systems.kimanyisacco.controllers.Hyperlinks;
// import org.pahappa.systems.kimanyisacco.models.adminLogin;
// import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;
// import org.pahappa.systems.kimanyisacco.service.Userservice;

// import javax.faces.bean.ManagedBean;
// import javax.faces.bean.ViewScoped;
// import javax.faces.context.FacesContext;
// import java.io.IOException;
// import java.util.List;

// @ManagedBean(name="admin")
// @ViewScoped
// public class admin {
// private adminLogin admin;
// private Userservice userservice;

// public admin() {
// this.admin = new adminLogin();
// this.userservice = new UserServiceImpl();
// }

// public adminLogin getAdmin() {
// return admin;
// }

// public void setAdmin(adminLogin admin) {
// this.admin = admin;
// }

// public Userservice getUserservice() {
// return userservice;
// }

// public void setUserservice(Userservice userservice) {
// this.userservice = userservice;
// }

// public void dologin() {
// List<adminLogin> check = userservice.admin();
// String id = admin.getAdminId();
// String password = admin.getPass();
// String baseUrl =
// FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
// for (adminLogin user : check) {

// if (user.getAdminId().equals(id) && user.getPass().equals(password)) {
// try {
// FacesContext.getCurrentInstance().getExternalContext().redirect(baseUrl +
// Hyperlinks.admiDashboard);
// } catch (IOException e) {
// throw new RuntimeException(e);
// }
// } else {
// try {
// FacesContext.getCurrentInstance().getExternalContext().redirect(baseUrl +
// Hyperlinks.adminlogin);
// } catch (IOException e) {
// throw new RuntimeException(e);
// }

// }
// }
// }
// }
