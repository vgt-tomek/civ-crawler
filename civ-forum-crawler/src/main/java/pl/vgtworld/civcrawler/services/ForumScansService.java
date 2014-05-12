package pl.vgtworld.civcrawler.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.civcrawler.dao.ForumScansDao;
import pl.vgtworld.civcrawler.entities.ForumScan;

@Stateless
public class ForumScansService {
	
	@Inject
	private ForumScansDao dao;
	
	public void saveScan(int allPosts, int newPosts) {
		ForumScan scan = new ForumScan();
		scan.setAllPosts(allPosts);
		scan.setNewPosts(newPosts);
		scan.setCreatedAt(new Date());
		dao.add(scan);
	}
	
	public Date findLastScanDate() {
		ForumScan scan = dao.findLast();
		if (scan == null) {
			return null;
		}
		return scan.getCreatedAt();
	}
	
}
