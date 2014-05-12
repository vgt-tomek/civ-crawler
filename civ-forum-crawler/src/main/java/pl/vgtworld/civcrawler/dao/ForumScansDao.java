package pl.vgtworld.civcrawler.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.civcrawler.entities.ForumScan;

@Stateless
public class ForumScansDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(ForumScan scan) {
		em.persist(scan);
	}
	
	@SuppressWarnings("unchecked")
	public ForumScan findLast() {
		Query query = em.createNamedQuery(ForumScan.QUERY_FIND_LAST);
		query.setMaxResults(1);
		List<ForumScan> result = query.getResultList();
		if (result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
