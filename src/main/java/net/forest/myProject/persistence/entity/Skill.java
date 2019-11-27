package net.forest.myProject.persistence.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "skill_name")
  private String skillName;

  @Column(name = "description")
  private String description;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "skills")
  private Set<Person> persons = new HashSet<>();

  public Skill() {
  }

  public Skill(final String skillName, final String description) {
    this.skillName = skillName;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Skill)) {
      return false;
    }

    Skill skill = (Skill) o;
    return Objects.equals(skillName, skill.skillName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(skillName);
  }

  //Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSkillName() {
    return skillName;
  }

  public void setSkill(String skill) {
    this.skillName = skill;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  Set<Person> getPersons() {
    return persons;
  }

  public void setPersons(Set<Person> persons) {
    this.persons = persons;
  }
}
