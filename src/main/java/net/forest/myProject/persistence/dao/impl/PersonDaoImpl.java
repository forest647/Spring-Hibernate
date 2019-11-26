package net.forest.myProject.persistence.dao.impl;

import java.util.List;
import javax.transaction.Transactional;
import net.forest.myProject.common.PersistenceOperations;
import net.forest.myProject.persistence.dao.PersonDao;
import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {

  @Autowired
  SessionFactory sessionFactory;

  @Override
  public void create(Person detail) {
    new PersistenceOperations().Save(sessionFactory, detail,"*** Employee created!");
  }

  @Override
  public void readAll() {
    List<Person> detailList = sessionFactory.getCurrentSession().createCriteria(Person.class).list();

    System.out.println("************ ALL PERSONs ****************");

    for (Person detail : detailList){
      System.out.printf("*** Id:%s \t Firstname:%s \t Lastname:%s \n", detail.getId(), detail.getFirstName(),
          detail.getLastName());
    }
  }

  @Override
  public Person getPerson(User user){
    Query q = sessionFactory.getCurrentSession().createQuery("FROM Person WHERE user=:user");
    q.setParameter("user", user);

    Person person = null;

    try {
      person = (Person) q.uniqueResult();
      if (user == null){
        System.out.println("Person Not Found !");
      }
    }
    catch (Exception ex){
      System.out.printf("Exception in getPerson: %s", ex.getMessage());
    }

    return person;
  }
}
