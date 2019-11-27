package net.forest.myProject.persistence.dao;

import java.util.List;
import net.forest.myProject.persistence.entity.Address;
import net.forest.myProject.persistence.entity.Person;

public interface AddressDao {

   void create(final String city, final String street, final Person person);

   void create(final Address address);

   List<Address> getAddressList(final Person person);
}
