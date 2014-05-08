package pl.vgtworld.civcrawler.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.vgtworld.civcrawler.entities.Thread;

@Stateless
public class ThreadsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(Thread thread) {
		em.persist(thread);
	}
	
	public Thread findById(int id) {
		return em.find(Thread.class, id);
	}
	
}
