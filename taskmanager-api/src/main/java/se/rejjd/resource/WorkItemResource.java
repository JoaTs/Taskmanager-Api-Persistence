package se.rejjd.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import se.rejjd.service.WorkItemService;

@Component
@Path("/workitems")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class WorkItemResource {

	private final WorkItemService workItemService;

	public WorkItemResource(WorkItemService workItemService) {
		this.workItemService = workItemService;
	}
}
