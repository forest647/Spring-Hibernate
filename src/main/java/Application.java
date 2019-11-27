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
    final ApplicationContext appContext =
        new ClassPathXmlApplicationContext("applicationContext.xml");

    final UserDao userDao = appContext.getBean(UserDao.class);
    final PersonDao personDao = appContext.getBean(PersonDao.class);
    final AddressDao addressDao = appContext.getBean(AddressDao.class);
    System.out.println("************************ START ************************");

    final User user1 = new User("aaaaaa", "ssssss");

    final Person person1 = new Person("Ionel", "Padure", user1);
    final Person person2 = new Person("Viorel", "Dura", new User("ddddd", "ffffff"));

    final Address a1 = new Address("Sibiu", "Strada Lunga nr 12", person1);

    personDao.create(person1);
    personDao.create(person2);

    addressDao.create(a1);
    addressDao.create("Cluj", "Str Louis Pasteur 23/32", person1);

    personDao.readAll();
    userDao.readAll();

    userDao.getUser("aaaaaa", "wrong_password");

    final User user = userDao.getUser("aaaaaa", "ssssss");

    personDao.getPerson(null);

    final Person person3 = personDao.getPerson(user);

    System.out.printf("Searched person1 is: %s %s and the addresses are: \n", person3.getFirstName(), person3.getLastName());

    for (Address address : addressDao.getAddressList(person3)) {
      System.out.printf("City:%s Street:%s \n", address.getCity(), address.getStreet());
    }

    System.out.println("************************ STOP  ************************");
  }
}
