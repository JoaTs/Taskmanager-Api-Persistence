package se.rejjd.resource;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Component;

import se.rejjd.model.WorkItem;
import se.rejjd.service.WorkItemService;

@Component
@Path("/workitems")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WorkItemResource {

	private WorkItemService workItemService;

	@Context
	private UriInfo uriInfo;

	public WorkItemResource(WorkItemService workItemService) {
		this.workItemService = workItemService;
	}

	@POST
	public Response createWorkItem(WorkItem workItem) {
		WorkItem workitemFromDb = workItemService.getWorkItemById(workItem.getId());
		if (workitemFromDb != null) {
			return Response.status(Status.FOUND).build();
		}
		workItem = new WorkItem(workItem.getTitle(), workItem.getDescription());
		workItemService.addOrUpdateWorkItem(workItem);
		URI location = uriInfo.getAbsolutePathBuilder().path(workItem.getId().toString()).build();
		return Response.created(location).build();
	}

	@GET
	@Path("{id}")
	public Response getWorkItem(@PathParam("id") Long id) {
		WorkItem workitem = workItemService.getWorkItemById(id);
		if (workitem == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(workitem).build();
	}

	@GET
	public Response getWorkItemByStatusOrDescription(@BeanParam WorkItemQueryParam param) {
		Collection<WorkItem> workitems = null;
		if (param.getStatus() != null) {
			workitems = workItemService.getWorkItemsByStatus(param.getStatus());
			if (workitems.isEmpty()) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(workitems).build();
		} else{
			workitems = workItemService.getWorkItemByDescripton(param.getDescription());
			if (workitems.isEmpty()) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(workitems).build();
		}
	}
}
