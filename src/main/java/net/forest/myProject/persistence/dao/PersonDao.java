package net.forest.myProject.persistence.dao;

import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.User;

public interface PersonDao {

  void saveOrUpdate(final Person person);

  void readAll();

  Person getPerson(final User user);
}
