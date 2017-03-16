package se.rejjd.service;

import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import se.rejjd.AbstractTest;
import se.rejjd.model.Team;
import se.rejjd.model.User;
import se.rejjd.repository.TeamRepository;
import se.rejjd.repository.UserRepository;

//@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class TeamServiceTest extends AbstractTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	@Autowired
	TeamService teamService;
	@MockBean
	UserRepository userRepository;
	@MockBean
	TeamRepository teamRepository;

	User user = new User("Robertasdasdasd", "roberts", "Hello", "world");
	Team team = new Team("Team");

	@Test
	public void shouldThrowExceptionWhenTeamIsFull() throws ServiceException {
		expectedException.expect(ServiceException.class);
		expectedException.expectMessage("Team is full!");
		when(teamRepository.save(team)).thenReturn(team);
		when(userRepository.countByTeamId(team.getId())).thenReturn(12L);
		teamService.addUserToTeam(user, team);

	}

}
