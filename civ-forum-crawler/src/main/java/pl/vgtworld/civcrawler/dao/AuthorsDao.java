package pl.vgtworld.civcrawler.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.vgtworld.civcrawler.entities.Author;

@Stateless
public class AuthorsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(Author author) {
		em.persist(author);
	}
	
	public Author findById(int id) {
		return em.find(Author.class, id);
	}
	
}
