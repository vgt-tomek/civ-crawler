package pl.vgtworld.civcrawler.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.vgtworld.civcrawler.entities.User;

@Stateless
public class UsersDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(User user) {
		em.persist(user);
	}
	
}
