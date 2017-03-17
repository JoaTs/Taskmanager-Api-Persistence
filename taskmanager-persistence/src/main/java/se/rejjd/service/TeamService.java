package se.rejjd.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.rejjd.model.Team;
import se.rejjd.model.User;
import se.rejjd.repository.TeamRepository;
import se.rejjd.repository.UserRepository;

@Component
public final class TeamService {

	private final TeamRepository teamRepository;
	private final UserRepository userRepository;
	private final UserService userService;

	@Autowired
	public TeamService(TeamRepository teamRepository, UserRepository userRepository, UserService userService) {
		this.teamRepository = teamRepository;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public Team addOrUpdateTeam(Team team) {
		return teamRepository.save(team);
	}

	public Team updateTeamStatus(Team team, boolean status) {
		team.setActiveTeam(status);
		return addOrUpdateTeam(team);
	}

	public Collection<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	public void addUserToTeam(User user, Team team) throws ServiceException {
		if (teamExists(team) && userService.userExists(user)) {
			if (isValidTeamSize(team)) {
				user.setTeam(team);
				userService.addOrUpdateUser(user);
				teamRepository.save(team);

			} else {
				throw new ServiceException("Team is full!");
			}
		} else {
			throw new ServiceException("Team or user not found");
		}
	}

	public Team getTeamById(Long id) {
		return teamRepository.findOne(id);
	}

	public boolean teamExists(Team team) {
		return teamRepository.findOne(team.getId()) != null;
	}

	private boolean isValidTeamSize(Team team) {
		return userRepository.countByTeamId(team.getId()) < 10;
	}
}
