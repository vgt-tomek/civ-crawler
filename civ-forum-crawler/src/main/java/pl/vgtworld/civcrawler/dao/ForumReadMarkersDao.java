package pl.vgtworld.civcrawler.dao;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.civcrawler.entities.ForumReadMarker;

@Stateless
public class ForumReadMarkersDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(ForumReadMarker marker) {
		em.persist(marker);
	}
	
	public Date findLastDateForUser(int userId) {
		Query query = em.createNamedQuery(ForumReadMarker.QUERY_LAST_FOR_USER);
		query.setParameter("userId", userId);
		query.setMaxResults(1);
		return (Date)query.getSingleResult();
	}
}
