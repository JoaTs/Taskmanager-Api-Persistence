package se.rejjd.resource;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import se.rejjd.model.Team;
import se.rejjd.model.User;
import se.rejjd.service.TeamService;
import se.rejjd.service.UserService;

@Component
@Path("/teams")
public final class TeamResource {

	private final TeamService teamService;

	public TeamResource(TeamService teamService) {
		this.teamService = teamService;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTeams() {

		Collection<Team> teams = teamService.getAllTeams();

		System.out.println(teams);

		return Response.ok(teams).build();

	}

}
