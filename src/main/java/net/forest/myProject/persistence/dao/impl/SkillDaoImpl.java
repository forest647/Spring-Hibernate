package net.forest.myProject.persistence.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import net.forest.myProject.common.PersistenceOperations;
import net.forest.myProject.persistence.dao.SkillDao;
import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.Skill;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class SkillDaoImpl implements SkillDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void saveOrUpdate(final String skillName, final String description) {
    final Skill skill = new Skill(skillName, description);
    saveOrUpdate(skill);
  }

  @Override
  public void saveOrUpdate(final Skill skill) {
    new PersistenceOperations().saveOrUpdate(sessionFactory, skill, "*** Skill '" + skill.getSkillName() + "' saved!");
  }

  @Override
  public Skill getSkill(final String skillName) {
    final Query q = sessionFactory.getCurrentSession().createQuery("FROM Skill WHERE skillName=:skillName");
    q.setParameter("skillName", skillName);

    Skill skill = null;

    try {
      skill = (Skill) q.uniqueResult();
      if (skill == null) {
        System.out.println("Skill with skillName '" + skillName + "' Not Found !");
      }
    } catch (Exception ex) {
      System.out.printf("Exception in getSkill: %s \n", ex.getMessage());
    }
    return skill;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Set<Skill> getPersonSkills(final Person person) {
    final Set<Skill> skillSet = new HashSet<>();

    final Query q = sessionFactory.getCurrentSession().createQuery("SELECT s FROM Person p JOIN p.skills s WHERE p.id=:personId");
    q.setParameter("personId", person.getId());

    try {
      skillSet.addAll((List<Skill>) q.list());
    } catch (Exception ex) {
      System.out.printf("Exception in getPersonSkills: %s \n", ex.getMessage());
    }

    return skillSet;
  }
}
