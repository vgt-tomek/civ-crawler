package pl.vgtworld.civcrawler.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.civcrawler.entities.ThreadReadMarker;

@Stateless
public class ThreadReadMarkersDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(ThreadReadMarker marker) {
		em.persist(marker);
	}
	
	@SuppressWarnings("unchecked")
	public ThreadReadMarker findByThreadForUser(int threadId, int userId) {
		Query query = em.createNamedQuery(ThreadReadMarker.QUERY_FIND_BY_THREAD_FOR_USER);
		query.setParameter("userId", userId);
		query.setParameter("threadId", threadId);
		query.setMaxResults(1);
		List<ThreadReadMarker> result = query.getResultList();
		if (result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
