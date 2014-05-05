package pl.vgtworld.civcrawler.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.vgtworld.civcrawler.entities.UserToken;

@Stateless
public class UserTokensDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(UserToken token) {
		em.persist(token);
	}
}
