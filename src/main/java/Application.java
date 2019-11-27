import java.util.Set;
import net.forest.myProject.persistence.dao.AddressDao;
import net.forest.myProject.persistence.dao.PersonDao;
import net.forest.myProject.persistence.dao.SkillDao;
import net.forest.myProject.persistence.dao.UserDao;
import net.forest.myProject.persistence.entity.Address;
import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.Skill;
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
    final SkillDao skillDao = appContext.getBean(SkillDao.class);

    final User user1 = new User("aaaaaa", "ssssss");
    final Person person1 = new Person("Ionel", "Padure", user1);
    final Person person2 = new Person("Viorel", "Dura", new User("ddddd", "ffffff"));
    final Address address1 = new Address("Sibiu", "Strada Lunga nr 12", person1);
    final Skill skillJDBC = new Skill("JDBC",  "Java DataBase Conoectivity");
    final Skill skillHibernate = new Skill("Hibernate", "Winter is comming ...");


    System.out.println("************************ START ************************");

    personDao.saveOrUpdate(person1);
    personDao.saveOrUpdate(person2);

    addressDao.saveOrUpdate(address1);
    addressDao.saveOrUpdate("Cluj", "Str Louis Pasteur 23/32", person1);

    skillDao.saveOrUpdate("Java", "Java programming language");
    skillDao.saveOrUpdate("Maven", "Build tool");
    skillDao.saveOrUpdate(skillJDBC);
    skillDao.saveOrUpdate(skillHibernate);

    person1.addSkill(skillHibernate);
    person1.addSkill(skillDao.getSkill("Java"));

    person2.addSkill(skillJDBC);
    person2.addSkill(skillDao.getSkill("Java"));
    person2.addSkill(skillDao.getSkill("Maven"));

    personDao.saveOrUpdate(person1);
    personDao.saveOrUpdate(person2);

    personDao.readAll();
    userDao.readAll();

    userDao.getUser("aaaaaa", "wrong_password");

    final User user = userDao.getUser("aaaaaa", "ssssss");

    personDao.getPerson(null);
    personDao.getPerson(new User("qwe","qwe"));

    final Person person3 = personDao.getPerson(user);

    System.out.printf("Searched person1 is: %s %s and the addresses are: \n", person3.getFirstName(), person3.getLastName());

    for (Address address : addressDao.getAddressList(person3)) {
      System.out.printf("City:%s Street:%s \n", address.getCity(), address.getStreet());
    }

    Set<Skill> skillSet = skillDao.getPersonSkills(person2);

    System.out.println("'" + person2.getFirstName()+ "' skills are: ");
    for (Skill skill: skillSet) {
      System.out.printf("Skill: '%s' Description: '%s' \n", skill.getSkillName(), skill.getDescription());
    }

    System.out.println("************************ STOP  ************************");
  }
}
