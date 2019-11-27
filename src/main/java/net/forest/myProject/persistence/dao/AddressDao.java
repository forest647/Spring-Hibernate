package net.forest.myProject.persistence.dao;

import java.util.List;
import net.forest.myProject.persistence.entity.Address;
import net.forest.myProject.persistence.entity.Person;

public interface AddressDao {

   void saveOrUpdate(final String city, final String street, final Person person);

   void saveOrUpdate(final Address address);

   List<Address> getAddressList(final Person person);
}
