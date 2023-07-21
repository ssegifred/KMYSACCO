package org.pahappa.systems.kimanyisacco.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String Residence;
    private String email;
    private String dob;
    private String password;
    private String memberId;



    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
@Column(name = "firstname",length = 100)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String fName) {
        this.firstname = fName;
    }

    @Column(name = "lastname",length = 100)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    @Column(name = "phone",length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name = "residence",length = 100)
    public String getResidence() {
        return Residence;
    }

    public void setResidence(String residence) {
        Residence = residence;
    }
    @Column(name = "email",length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "dob",length = 50)
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    @Column(name = "password",nullable = false,length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Id
    @Column(name = "member_id",nullable = false,length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
