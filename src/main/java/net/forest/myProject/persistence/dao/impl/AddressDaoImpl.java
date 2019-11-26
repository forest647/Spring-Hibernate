package net.forest.myProject.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import net.forest.myProject.common.PersistenceOperations;
import net.forest.myProject.persistence.dao.AddressDao;
import net.forest.myProject.persistence.entity.Address;
import net.forest.myProject.persistence.entity.Person;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void create(String city, String street, Person person) {
    Address address = new Address(city, street, person);
    new PersistenceOperations().Save(sessionFactory, address, "*** Address created!");
  }

  @Override
  public void create(Address address) {
    new PersistenceOperations().Save(sessionFactory, address, "*** Address created!");
  }

  @Override
  public List<Address> getAddressList(Person person) {
    List<Address> addressList = new ArrayList<Address>();

    Query q = sessionFactory.getCurrentSession().createQuery("FROM Address WHERE person=:person");
    q.setParameter("person", person);

    try {
      addressList = (List<Address>) q.list();
      if (addressList == null){
        System.out.println("Address Not Found !");
      }
    }
    catch (Exception ex){
      System.out.printf("Exception in getAddressLIst: %s", ex.getMessage());
    }

    return addressList;
  }
}
