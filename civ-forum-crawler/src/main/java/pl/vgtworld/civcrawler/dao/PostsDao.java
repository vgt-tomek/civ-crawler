package pl.vgtworld.civcrawler.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.vgtworld.civcrawler.entities.Post;

@Stateless
public class PostsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void add(Post post) {
		em.persist(post);
	}
	
	public Post findById(int id) {
		return em.find(Post.class, id);
	}
}
