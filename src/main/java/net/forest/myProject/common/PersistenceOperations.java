package net.forest.myProject.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PersistenceOperations {

  public void Save(SessionFactory sessionFactory, Object object, String message){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(object);
    session.getTransaction().commit();
    System.out.println(message);
    session.close();
  }
}
