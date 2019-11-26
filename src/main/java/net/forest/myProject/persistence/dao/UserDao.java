package net.forest.myProject.persistence.dao;

import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.User;

public interface UserDao {

   void create(String username, String password, Person employeeId);

   void readAll();

   void update();

   void delete();

   User getUser(String username, String password);
}
