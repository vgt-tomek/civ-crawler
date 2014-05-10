package pl.vgtworld.civcrawler.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.civcrawler.dao.AuthorsDao;
import pl.vgtworld.civcrawler.entities.Author;

@Stateless
public class AuthorsService {
	
	@Inject
	private AuthorsDao dao;
	
	public void add(Author author) {
		dao.add(author);
	}
	
	public Author findById(int id) {
		return dao.findById(id);
	}
	
}
