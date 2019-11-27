package net.forest.myProject.persistence.dao.impl;

import java.util.List;
import javax.transaction.Transactional;
import net.forest.myProject.common.PersistenceOperations;
import net.forest.myProject.persistence.dao.UserDao;
import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void create(final String username, final String password, final Person person) {
    final User user = new User(username, password);
    new PersistenceOperations().Save(sessionFactory, user, "*** User created!");
  }

  @SuppressWarnings("unchecked")
  @Override
  public void readAll() {
    final List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();

    System.out.println("************ ALL USERs ****************");

    for (final User user : userList) {
      System.out.printf("*** Id:%s \t Username:%s \t Password:%s  Person:%s \n",
          user.getId(),
          user.getUsername(),
          user.getPassword(),
          user.getPerson().getFirstName() + " " + user.getPerson().getLastName());
    }
  }

  @Override
  public User getUser(final String username, final String password) {
    final Query q = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=:username AND password=:password");
    q.setParameter("username", username);
    q.setParameter("password", password);

    User user = null;

    try {
      user = (User) q.uniqueResult();
      if (user == null) {
        System.out.println("User Not Found !");
      }
    } catch (Exception ex) {
      System.out.printf("Exception in getUser: %s", ex.getMessage());
    }

    return user;
  }

  @Override
  public void update() {

  }

  @Override
  public void delete() {

  }

}
