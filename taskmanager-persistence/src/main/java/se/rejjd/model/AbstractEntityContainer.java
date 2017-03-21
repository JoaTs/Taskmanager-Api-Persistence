package se.rejjd.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AbstractEntityContainer {

	@XmlElement
	private Team team;
	@XmlElement
	private User user;

	public Team getTeam() {
		return team;
	}

	public User getUser() {
		return user;
	}

}
