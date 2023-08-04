package org.pahappa.systems.kimanyisacco.views.Users;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.service.Userservice;
import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;

@ManagedBean(name = "accountbean")
@ViewScoped
public class accountbean {
    private Account account;
    private Userservice userservice;
    private final User user;

    public accountbean() {
        this.account = new Account();
        this.userservice = new UserServiceImpl();
        this.user = new User();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Userservice getUserservice() {
        return userservice;
    }

    public void setUserservice(Userservice userservice) {
        this.userservice = userservice;
    }

    public void Deposit() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        User member = (User) facesContext.getExternalContext().getSessionMap().get("Member");
        String memberId = member.getMemberId();
        System.out.println("MemberID: " + memberId);
        System.out.println(account.getAmount());
        if (account.getAmount() > 500.0) {
            boolean check = userservice.getAccountByUserId(memberId, account.getAmount());

            if (check) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Deposit done successfully"));
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "You are not logged in"));
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Enter amount greater than 500"));
        }
    }

    public void Withdrawal() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        User member = (User) facesContext.getExternalContext().getSessionMap().get("Member");
        String memberId = member.getMemberId();
        System.out.println("MemberID: " + memberId);
        System.out.println(account.getAmount());
        boolean check = userservice.getWithdrawals(memberId, account.getAmount());

        if (check) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Request sent successfully"));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Insufficient amount"));
        }
    }

}
