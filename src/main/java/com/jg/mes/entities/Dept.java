package com.jg.mes.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
//@JsonIgnoreProperties(value = {"users"})
public class Dept {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid",strategy = "uuid.hex")
    private String deptcode;

    @Column(length = 30)
    private  String deptname;

//    @OneToMany(fetch= FetchType.LAZY,cascade = CascadeType.MERGE,mappedBy = "DeptCode")
   @OneToMany(fetch= FetchType.LAZY,cascade = CascadeType.MERGE,mappedBy = "DeptCode")
  // @JoinColumn(name="deptid")
   @NotFound(action= NotFoundAction.IGNORE)//代表可以为空，允许为null
   @JsonBackReference
   private List<User> users;

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }
}
