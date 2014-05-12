package pl.vgtworld.civcrawler.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "forum_scans")
@NamedQueries({
		@NamedQuery(name = ForumScan.QUERY_FIND_LAST,
			query = "SELECT s FROM ForumScan s ORDER BY s.createdAt DESC")
})
public class ForumScan {
	
	public static final String QUERY_FIND_LAST = "ForumScan.findLast";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "new_posts")
	private int newPosts;
	
	@Column(name = "all_posts")
	private int allPosts;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNewPosts() {
		return newPosts;
	}
	
	public void setNewPosts(int newPosts) {
		this.newPosts = newPosts;
	}
	
	public int getAllPosts() {
		return allPosts;
	}
	
	public void setAllPosts(int allPosts) {
		this.allPosts = allPosts;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
