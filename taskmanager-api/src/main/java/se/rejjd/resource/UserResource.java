package se.rejjd.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import se.rejjd.model.User;
import se.rejjd.service.ServiceException;
import se.rejjd.service.UserService;

@Component
@Path("/users")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserResource {
    
    private UserService userService;
    @Context
    private UriInfo uriInfo;
    
    public UserResource(UserService userService) {
        this.userService = userService;
    }
    
    @PUT
    @Path("{userId}")
    public Response updateUser(@PathParam("userId") String userId, User user) throws ServiceException{
    	if(!userId.equals(user.getUserId())){
    		System.out.println(userId);
    		System.out.println(user.getUserId());
    		return Response.status(Status.BAD_REQUEST).build();
    	}
    	User userfromDb = userService.getUserByUserId(userId);
    	if(userfromDb == null){
    		return Response.status(Status.NOT_FOUND).build();
        }
    	userService.addOrUpdateUser(user);
    	return Response.ok().build();
//    	if (user.getFirstname() != null && user.getFirstname() != userfromDb.getFirstname()) {
//    		userfromDb.setFirstName(user.getFirstname());
//			userService.updateFirstName(userfromDb, user.getFirstname());
//		}if (user.getLastname() != null && user.getLastname() != userfromDb.getLastname()) {
//			userService.updateLastName(userfromDb, user.getLastname());
//		}if (user.getUsername() != null && user.getUsername() != userfromDb.getUsername()) {
//			userService.updateUsername(userfromDb, user.getUsername());
//		}if (user.isActiveUser() != false) {
//			userService.updateUserStatus(userfromDb, true);
//		}
    }
    
    @POST
    public Response addUser(User user) throws ServiceException {
    	User fromDb = userService.getUserByUserId(user.getUserId());
    	if(fromDb != null){
    		return Response.status(Status.FOUND).build();
    	}
        user = new User(user.getUsername(), user.getFirstname(), user.getLastname(), user.getUserId());
        userService.addOrUpdateUser(user);
        URI location = uriInfo.getAbsolutePathBuilder().path(user.getUserId()).build();
        return Response.created(location).build();
         
    }
    
    
    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") String id) {
        User user = userService.getUserByUserId(id);
        
        if (user == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
        
    }
    
    private User identifyJsonParameters(User user){
    	
    	return null;
    }
}
