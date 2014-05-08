package pl.vgtworld.civcrawler.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.civcrawler.dao.PostsDao;
import pl.vgtworld.civcrawler.entities.Post;

@Stateless
public class PostsService {
	
	@Inject
	private PostsDao dao;
	
	public void add(Post post) {
		dao.add(post);
	}
	
	public Post findById(int id) {
		return dao.findById(id);
	}
}
