package pl.vgtworld.civcrawler.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.civcrawler.dao.ThreadsDao;
import pl.vgtworld.civcrawler.entities.Thread;

@Stateless
public class ThreadsService {
	
	@Inject
	ThreadsDao dao;
	
	public void add(Thread thread) {
		dao.add(thread);
	}
	
	public Thread findById(int id) {
		return dao.findById(id);
	}
}
