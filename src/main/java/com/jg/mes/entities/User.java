package com.jg.mes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = {"deptcode","deptCode","hibernateLazyInitializer"})
public class User  {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid",strategy = "uuid.hex")
    @Id
    private  String userid;

    @Column(length = 30)
    private  String username;


    private Date  inhere;

    @OneToOne(fetch= FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="deptid", unique = false)
   /* @JoinColumn(name="deptid",foreignKey = @ForeignKey(name = "DeptID",value = ConstraintMode.NO_CONSTRAINT),
            unique = false)*/

    private Dept DeptCode;


    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(name="usermodules",
    joinColumns ={@JoinColumn(name="userid")},
    inverseJoinColumns = {@JoinColumn(name="modelid")})
     private Set<Module> models;

    public Dept getDeptCode() {
        return DeptCode;
    }

    public void setDeptCode(Dept deptCode) {
        DeptCode = deptCode;
    }

    public Set<Module> getModels() {
        return models;
    }

    public void setModels(Set<Module> models) {
        this.models = models;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getInhere() {
        return inhere;
    }

    public void setInhere(Date inhere) {
        this.inhere = inhere;
    }

    public Dept getDeptcode() {
        return DeptCode;
    }

    public void setDeptcode(Dept deptcode) {
        this.DeptCode = deptcode;
    }
    public User(){}

    public User(String username, Date inhere, Dept deptcode) {
        this.username = username;
        this.inhere = inhere;
        this.DeptCode = deptcode;
    }
}
