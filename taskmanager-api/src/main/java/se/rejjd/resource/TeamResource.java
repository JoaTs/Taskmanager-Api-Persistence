package se.rejjd.resource;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import se.rejjd.model.Team;
import se.rejjd.service.TeamService;

@Component
@Path("/teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class TeamResource {

	private final TeamService teamService;

	@Context
	private UriInfo uriInfo;

	public TeamResource(TeamService teamService) {
		this.teamService = teamService;
	}

	@GET
	public Response getAllTeams() {
		Collection<Team> teams = teamService.getAllTeams();

		return Response.ok(teams).build();
	}

	@POST
	public Response addTeam(Team team) {
		Team newTeam = teamService.getTeamById(team.getId());
		if (newTeam == null) {
			Team teamfromDB = teamService.addOrUpdateTeam(team);
			URI location = uriInfo.getAbsolutePathBuilder().path(teamfromDB.getId().toString()).build();
			return Response.created(location).build();

		} else {
			teamService.addOrUpdateTeam(team);
			return Response.ok().build();
		}
	}

}
