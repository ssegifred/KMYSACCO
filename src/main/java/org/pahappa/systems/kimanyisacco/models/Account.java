package org.pahappa.systems.kimanyisacco.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    private int id;
    private Double amount;
    private Double withdrawals;
    private User user;

    // constructor, getters and setters

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public Account() {
        this.amount = 0.00;
        this.withdrawals = 0.00;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "member_id", unique = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "withdrawals")
    public Double getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(Double withdrawals) {
        this.withdrawals = withdrawals;
    }

}