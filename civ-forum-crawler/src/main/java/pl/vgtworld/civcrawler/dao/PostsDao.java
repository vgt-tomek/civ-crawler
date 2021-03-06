package pl.vgtworld.civcrawler.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.vgtworld.civcrawler.entities.Post;
import pl.vgtworld.civcrawler.entities.User;

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
	
	@SuppressWarnings("unchecked")
	public Post[] findNewSince(Date date) {
		Query query = em.createNamedQuery(Post.QUERY_FIND_NEW_SINCE);
		query.setParameter("date", date);
		List<Post> results = query.getResultList();
		return results.toArray(new Post[results.size()]);
	}
	
	@SuppressWarnings("unchecked")
	public Post[] findUnread(User user, Date sinceDate) {
		Query query = em.createNativeQuery(Post.NATIVE_QUERY_FIND_UNREAD, Post.class);
		query.setParameter("date", sinceDate);
		query.setParameter("userId", user.getId());
		List<Post> result = query.getResultList();
		return result.toArray(new Post[result.size()]);
	}
}
