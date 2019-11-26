package net.forest.myProject.persistence.dao;

import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.User;

public interface PersonDao {

  void create(Person detail);

  void readAll();

  Person getPerson(User user);
}
