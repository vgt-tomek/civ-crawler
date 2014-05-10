package pl.vgtworld.civcrawler.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.vgtworld.civcrawler.entities.ForumReadMarker;

@Stateless
public class ForumReadMarkersDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(ForumReadMarker marker) {
		em.persist(marker);
	}
	
}
