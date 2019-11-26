package net.forest.myProject.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;

  @Column(name = "city")
  private String city;

  @Column(name = "address")
  private String street;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id")
  private Person person;

  public Address() {
  }

  public Address(String city, String street, Person person) {
    this.city = city;
    this.street = street;
    this.person = person;
  }

  public int getId() {
    return id;
  }

  public String getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }

  public Person getPerson() {
    return person;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
