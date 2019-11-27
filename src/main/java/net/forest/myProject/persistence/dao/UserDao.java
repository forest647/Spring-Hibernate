package net.forest.myProject.persistence.dao;

import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.User;

public interface UserDao {

   void create(final String username, final String password, final Person person);

   void readAll();

   void update();

   void delete();

   User getUser(final String username,final String password);
}
