package pl.vgtworld.civcrawler.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.civcrawler.entities.User;

@Stateless
public class UsersDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(User user) {
		em.persist(user);
	}
	
	@SuppressWarnings("unchecked")
	public User findByLogin(String login) {
		Query query = em.createNamedQuery(User.QUERY_FIND_BY_LOGIN);
		query.setParameter("login", login);
		List<User> resultList = query.getResultList();
		if (resultList.size() == 0) {
			return null;
		}
		return resultList.get(0);
	}
}
