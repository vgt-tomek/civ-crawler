package pl.vgtworld.civcrawler.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
@NamedQueries({
		@NamedQuery(name = Post.QUERY_FIND_NEW_SINCE,
			query = "SELECT p FROM Post p WHERE p.createdAt >= :date ORDER BY p.createdAt ASC")
})
public class Post {
	
	public static final String QUERY_FIND_NEW_SINCE = "Post.findNewSince";
	
	public static final String NATIVE_QUERY_FIND_UNREAD = "SELECT  p.* "
		+ "FROM posts p "
		+ "LEFT JOIN thread_read_markers m "
		+ "ON p.thread_id = m.thread_id AND m.user_id = :userId  "
		+ "WHERE p.created_at > :date "
		+ "AND (m.read_at IS NULL OR p.created_at > m.read_at) "
		+ "ORDER BY p.id ASC";
	
	@Id
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Thread thread;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Author author;
	
	private int page;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Thread getThread() {
		return thread;
	}
	
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
}
