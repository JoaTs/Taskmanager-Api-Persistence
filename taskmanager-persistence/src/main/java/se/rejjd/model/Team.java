package se.rejjd.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@Entity
@Table(name = "teams")
public class Team extends AbstractEntity {

	@XmlElement
	@Column(nullable = false, unique = true)
	private String teamName;
	@XmlElement
	@Column(nullable = false)
	private boolean activeTeam;
	@XmlElement(name="user")
	@XmlElementWrapper(name="users")
	@OneToMany(mappedBy = "team")
	private Collection<User> users;

	protected Team() {
	}

	public Team(String teamName) {
		this.teamName = teamName;
		this.activeTeam = true;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public void setActiveTeam(boolean activeTeam) {
		this.activeTeam = activeTeam;
	}

	public String getTeamName() {
		return teamName;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public boolean isActiveTeam() {
		return activeTeam;
	}

	@Override
	public String toString() {
		return "Team id: " + getId() + ", team name: " + teamName + ", active: " + activeTeam;
	}

}
