import net.forest.myProject.persistence.dao.AddressDao;
import net.forest.myProject.persistence.dao.PersonDao;
import net.forest.myProject.persistence.dao.UserDao;
import net.forest.myProject.persistence.entity.Address;
import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

  public static void main(String[] args) {
    ApplicationContext appContext =
        new ClassPathXmlApplicationContext("applicationContext.xml");

    UserDao userDao = appContext.getBean(UserDao.class);
    PersonDao personDao = appContext.getBean(PersonDao.class);
    AddressDao addressDao = appContext.getBean(AddressDao.class);

    System.out.println("************************ START ************************");

    User u1 = new User("aaaaaa","ssssss");

    Person p1 = new Person("Ionel", "Padure", u1);
    Person p2 = new Person("Viorel", "Dura", new User("ddddd", "ffffff"));

    Address a1 = new Address("Sibiu", "Strada Lunga nr 12", p1);

    personDao.create(p1);
    personDao.create(p2);

    addressDao.create(a1);
    addressDao.create("Cluj", "Str Louis Pasteur 23/32", p1);

    personDao.readAll();
    userDao.readAll();

    userDao.getUser("aaaaaa", "wrong_password");

    User user = userDao.getUser("aaaaaa", "ssssss");

    personDao.getPerson(null);

    Person p3 = personDao.getPerson(user);

    System.out.printf("Searched person is: %s %s and the addresses are: \n", p3.getFirstName(), p3.getLastName());

    for (Address address: addressDao.getAddressList(p3)) {
      System.out.printf("City:%s Street:%s \n", address.getCity(), address.getStreet());
    }

    System.out.println("************************ STOP  ************************");
  }
}
