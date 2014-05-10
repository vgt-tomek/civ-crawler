package pl.vgtworld.civcrawler.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "forum_read_markers")
@NamedQueries({
	@NamedQuery(name=ForumReadMarker.QUERY_LAST_FOR_USER,
		query="SELECT m.readAt FROM ForumReadMarker m WHERE m.user.id = :userId ORDER BY m.id DESC")
})
public class ForumReadMarker {
	
	public enum Executions {
		MANUAL, AUTOMATIC
	}
	
	public static final String QUERY_LAST_FOR_USER = "ForumReadMarker.lastForUser";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@Column(name = "read_at")
	private Date readAt;
	
	@Enumerated(EnumType.STRING)
	private Executions execution;
	
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
	
	public Date getReadAt() {
		return readAt;
	}
	
	public void setReadAt(Date readAt) {
		this.readAt = readAt;
	}
	
	public Executions getExecution() {
		return execution;
	}
	
	public void setExecution(Executions execution) {
		this.execution = execution;
	}
	
}
