package pl.vgtworld.civcrawler.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.entities.UserToken;

@Stateless
public class UserTokensDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(UserToken token) {
		em.persist(token);
	}
	
	@SuppressWarnings("unchecked")
	public UserToken findLastTokenForUser(User user) {
		Query query = em.createNamedQuery(UserToken.QUERY_USER_LAST_TOKEN);
		query.setParameter("user", user);
		query.setMaxResults(1);
		List<UserToken> result = query.getResultList();
		if (result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
