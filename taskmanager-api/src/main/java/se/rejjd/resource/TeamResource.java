package se.rejjd.resource;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import se.rejjd.model.Team;
import se.rejjd.model.User;
import se.rejjd.service.ServiceException;
import se.rejjd.service.TeamService;
import se.rejjd.service.UserService;

@Component
@Path("/teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class TeamResource {

	private final TeamService teamService;
	private final UserService userService;

	@Context
	private UriInfo uriInfo;

	public TeamResource(TeamService teamService, UserService userService) {
		this.teamService = teamService;
		this.userService = userService;
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

		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("{id}")
	public Response updateTeam(Team team, @PathParam("id") long id) {
		if (team.getId() != id) {
			throw new WebApplicationException("conflicting id's", Status.BAD_REQUEST);
		}
		Team teamFromDb = teamService.getTeamById(team.getId());
		if (teamFromDb != null) {
			teamService.addOrUpdateTeam(team);
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

//	@PUT
//	@Path("{id}")
//	public Response addUserToTeam(User user, Team team, @PathParam("id") long id) throws WebApplicationException {
//		if (team.getId() != id) {
//			throw new WebApplicationException("conflicting id's", Status.BAD_REQUEST);
//		}
//		try {
//			teamService.addUserToTeam(user, team);
//		} catch (ServiceException e) {
//			throw new WebApplicationException(e, Status.NOT_FOUND);
//		}
//		return Response.ok().build();
//	}

}
