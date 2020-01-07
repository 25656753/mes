package com.jg.mes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties(value = { "users"})
public class Module {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long mid;
  private String mname;

  @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
  @JoinTable(name = "usermodules",
          joinColumns = {
          @JoinColumn(name = "modelid")},
          inverseJoinColumns = {
           @JoinColumn(name="userid")
          })
  private Set<User>  Users;

  public Set<User> getUsers() {
    return Users;
  }

  public void setUsers(Set<User> users) {
    Users = users;
  }

  public long getMid() {
    return mid;
  }

  public void setMid(long mid) {
    this.mid = mid;
  }


  public String getMname() {
    return mname;
  }

  public void setMname(String mname) {
    this.mname = mname;
  }

}
