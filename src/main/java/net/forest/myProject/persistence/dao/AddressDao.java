package net.forest.myProject.persistence.dao;

import java.util.List;
import net.forest.myProject.persistence.entity.Address;
import net.forest.myProject.persistence.entity.Person;

public interface AddressDao {

   void create(String city, String street, Person person);

   void create(Address address);

   List<Address> getAddressList(Person person);
}
