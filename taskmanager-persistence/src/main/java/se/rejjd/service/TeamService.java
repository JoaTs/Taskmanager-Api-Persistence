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

	@Autowired
	public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
		this.teamRepository = teamRepository;
		this.userRepository = userRepository;
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
		Team teamToDB = getTeamById(team.getId());
		if (teamToDB != null) {
			if (isValidTeamSize(teamToDB)) {
				user.setTeam(teamToDB);
				userRepository.save(user);
				teamRepository.save(teamToDB);

			} else {
				throw new ServiceException("Team is full!");
			}
		} else {
			throw new ServiceException("Team not found");
		}
	}

	private boolean isValidTeamSize(Team team) {
		return userRepository.countByTeamId(team.getId()) < 10;
	}

	public Team getTeamById(Long id) {
		return teamRepository.findOne(id);
	}

}
