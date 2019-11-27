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
  public void saveOrUpdate(final String city, final String street, final Person person) {
    final Address address = new Address(city, street, person);
    saveOrUpdate(address);
  }

  @Override
  public void saveOrUpdate(final Address address) {
    new PersistenceOperations().saveOrUpdate(sessionFactory, address, "*** Address '" + address.getCity() + "' saved!");
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Address> getAddressList(final Person person) {
    List<Address> addressList = new ArrayList<>();

    final Query q = sessionFactory.getCurrentSession().createQuery("FROM Address WHERE person=:person");
    q.setParameter("person", person);

    try {
      addressList = (List<Address>) q.list();
      if (addressList == null) {
        System.out.println("Address Not Found !");
      }
    } catch (Exception ex) {
      System.out.printf("Exception in getAddressLIst: %s \n", ex.getMessage());
    }

    return addressList;
  }
}
