package net.forest.myProject.persistence.dao;

import java.util.Set;
import net.forest.myProject.persistence.entity.Person;
import net.forest.myProject.persistence.entity.Skill;

public interface SkillDao {
  void saveOrUpdate(final String skillName, final String description);

  void saveOrUpdate(final Skill skill);

  Skill getSkill(final String skillName);

  Set<Skill> getPersonSkills(final Person person);
}
