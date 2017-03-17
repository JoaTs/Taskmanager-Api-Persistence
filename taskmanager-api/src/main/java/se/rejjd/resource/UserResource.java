package se.rejjd.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import se.rejjd.model.User;

@Component
@Path("users")
public class UserResource {

	public static final Map<Long, User> users = new HashMap<>();

	@POST
	public Response addUser() {
		User user = new User("username123", "firstname", "lastname", "userid");
		users.put(user.getId(), user);
		return Response.status(Status.CREATED).header("Location", "users/" + user.getId()).build();
	}
	
	@GET
	@Path("{id}")
	public Response getUserById(@PathParam("id") String id) {
		if (users.containsKey(id)) {
			return Response.ok(users.get(id).toString()).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest() {
		return "Hello";
	}

}
