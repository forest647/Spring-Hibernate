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
  private SessionFactory sessionFactory;

  @Override
  public void create(final Person person) {
    new PersistenceOperations().Save(sessionFactory, person,"*** Employee created!");
  }

  @SuppressWarnings("unchecked")
  @Override
  public void readAll() {
    final List<Person> detailList = sessionFactory.getCurrentSession().createCriteria(Person.class).list();

    System.out.println("************ ALL PERSONs ****************");

    for (final Person detail : detailList){
      System.out.printf("*** Id:%s \t Firstname:%s \t Lastname:%s \n", detail.getId(), detail.getFirstName(),
          detail.getLastName());
    }
  }

  @Override
  public Person getPerson(final User user){
    final Query q = sessionFactory.getCurrentSession().createQuery("FROM Person WHERE user=:user");
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
