package net.forest.myProject.persistence.entity;

import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private User user;

  public Person() {
  }

  public Person(String firstName, String lastName, User user) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.user = user;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public User getUser() {
    return user;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
