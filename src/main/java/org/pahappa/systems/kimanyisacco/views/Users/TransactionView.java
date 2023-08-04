package org.pahappa.systems.kimanyisacco.views.Users;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.pahappa.systems.kimanyisacco.models.Transact;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.service.Userservice;
import org.pahappa.systems.kimanyisacco.service.Implement.UserServiceImpl;

@ManagedBean(name = "TransactionView")
@ViewScoped
public class TransactionView {
    private final Transact trans;
    private Userservice userservice;
    List<Transact> transactions;
    private User user;

    public TransactionView() {
        this.trans = new Transact();
        this.userservice = new UserServiceImpl();
        this.user = new User();
    }

    // getters and setters for transaction object
    public Transact getTrans() {
        return trans;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Userservice getUserservice() {
        return userservice;
    }

    public void setUserservice(Userservice userservice) {
        this.userservice = userservice;
    }

    public List<Transact> getAllTransactions() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        User member = (User) facesContext.getExternalContext().getSessionMap().get("Member");
        String memberId = member.getMemberId();
        transactions = userservice.getAllTransactions(memberId);
        return transactions;
    }

    public List<Transact> getTransactions() {
        transactions = userservice.getTransactions();
        return transactions;
    }

    public Double getDeposit() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        User member = (User) facesContext.getExternalContext().getSessionMap().get("Member");
        String memberId = member.getMemberId();
        transactions = userservice.getAllTransactions(memberId);
        if (transactions != null) {
            Double dep = 0.00;
            for (Transact trans : transactions) {
                if (trans.getType().equals("Deposit")) {
                    dep += trans.getAmount();

                }
            }
            return dep;
        } else {
            return 0.00;

        }
    }

    public Double getWithdrawals() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        User member = (User) facesContext.getExternalContext().getSessionMap().get("Member");
        String memberId = member.getMemberId();
        transactions = userservice.getAllTransactions(memberId);
        if (transactions != null) {
            Double dep = 0.00;
            for (Transact trans : transactions) {
                if ((trans.getType().equals("Withdraw")) && (trans.getStatus() == 0)) {
                    dep += trans.getAmount();

                }
            }
            return dep;
        } else {
            return 0.00;

        }
    }

    public Double getBalance() {
        Double balance = getDeposit() - getWithdrawals();
        return balance;
    }

    // get all withdral requests
    public List<Transact> getRequests() {
        transactions = userservice.getRequests();
        return transactions;
    }

    public void ApproveRequest(Transact trans) {
        String memberId = trans.getMemberid();
        Double amount = trans.getAmount();
        boolean approved = userservice.ApprovedWithdraws(memberId, amount);
        if (approved) {
            transactions.remove(trans);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Request Approved successfully"));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed", "Member balance is not enough!"));

        }
    }

    public void RejectRequest(Transact trans) {
        String memberId = trans.getMemberid();
        // Double amount = trans.getAmount();
        boolean rejected = userservice.RejectWithdraws(memberId);
        if (rejected)  {
            transactions.remove(trans);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Request Rejected successfully"));
        }
    }
}
