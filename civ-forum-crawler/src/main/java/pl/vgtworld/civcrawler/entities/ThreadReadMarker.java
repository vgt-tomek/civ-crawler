package pl.vgtworld.civcrawler.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "thread_read_markers")
@NamedQueries({
		@NamedQuery(name = ThreadReadMarker.QUERY_FIND_BY_THREAD_FOR_USER,
			query = "SELECT m FROM ThreadReadMarker m WHERE m.user.id = :userId AND m.thread.id = :threadId")
})
public class ThreadReadMarker {
	
	public static final String QUERY_FIND_BY_THREAD_FOR_USER = "ThreadReadMarker.findByThreadForUser";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Thread thread;
	
	@Column(name = "read_at")
	private Date readAt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Thread getThread() {
		return thread;
	}
	
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
	public Date getReadAt() {
		return readAt;
	}
	
	public void setReadAt(Date readAt) {
		this.readAt = readAt;
	}
	
}
