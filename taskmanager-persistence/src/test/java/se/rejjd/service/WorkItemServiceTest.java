package se.rejjd.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import se.rejjd.AbstractTest;
import se.rejjd.model.User;
import se.rejjd.model.WorkItem;
import se.rejjd.model.WorkItem.Status;

public class WorkItemServiceTest extends AbstractTest {

	@Autowired
	public WorkItemService workItemService;
	@Autowired
	public IssueService issueService;
	@Autowired
	public UserService userService;
	@Autowired
	private EmbeddedDatabase database;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void canAddOrUpdateWorkItem() {
		WorkItem workitem = workItemService.addOrUpdateWorkItem(new WorkItem("broken windows", "replace the windows"));
		WorkItem workItemFromDb = workItemService.getWorkItemById(workitem.getId());

		assertNotNull(workitem);
		assertThat(workitem, is(workItemFromDb));
	}

	@Test
	public void canAddWorkItemToUser() throws ServiceException {
		User user = userService.addOrUpdateUser(new User("username10", "firstname", "lastname", "userId"));
		WorkItem workItem = workItemService.addOrUpdateWorkItem(new WorkItem("title", "description"));

		workItemService.addUserToWorkItem(workItem, user);
		ArrayList<WorkItem> listOfUserWorkItems = (ArrayList<WorkItem>) workItemService.getAllWorkItemsByUser(user);

		assertThat(listOfUserWorkItems, contains(workItem));
	}

	@Test
	public void shouldThrowServiceExceptionWhenGivingUserTooManyWorkItems() throws ServiceException {
		expectedException.expect(ServiceException.class);
		expectedException.expectMessage("user must be active and cannot have more than 5 work items");
		User user = userService.addOrUpdateUser(new User("validUsername", "firstname", "lastname", "userId"));

		for (int i = 0; i < 6; i++) {
			workItemService.addUserToWorkItem(new WorkItem("title" + i, "description"), user);
		}
	}

	@Test
	public void canUpdateWorkItemStatus() throws ServiceException {
		String statusDone = "DONE";
		String statusArchived = "ARCHIVED";
		WorkItem workitem = workItemService.addOrUpdateWorkItem(new WorkItem("buggy code", "fix the code"));
		WorkItem secondWorkitem = workItemService.addOrUpdateWorkItem(new WorkItem("more buggy code", "fix the code"));

		WorkItem workItemFromDb = workItemService.updateWorkItemStatus(workitem, Status.DONE);
		WorkItem secondWorkItemFromDb = workItemService.updateWorkItemStatus(secondWorkitem, Status.ARCHIVED);

		assertThat(workItemFromDb.getStatus().toString(), is(statusDone));
		assertThat(secondWorkItemFromDb.getStatus().toString(), is(statusArchived));
	}

	@Test
	public void canGetWorkItemsByStatus() throws ServiceException {
		WorkItem workitemOne = workItemService.addOrUpdateWorkItem(new WorkItem("workitem-1", "de"));
		WorkItem workitemTwo = workItemService.addOrUpdateWorkItem(new WorkItem("workitem-2", "desc"));
		workItemService.addOrUpdateWorkItem(new WorkItem("workitem-3", "descrip"));
		workItemService.addOrUpdateWorkItem(new WorkItem("workitem-4", "description"));

		workItemService.updateWorkItemStatus(workitemOne, Status.DONE);
		workItemService.updateWorkItemStatus(workitemTwo, Status.DONE);
		ArrayList<WorkItem> listOfWorkItems = (ArrayList<WorkItem>) workItemService.getWorkItemsByStatus(Status.DONE);

		assertThat(listOfWorkItems.size(), is(2));
		assertThat(listOfWorkItems, contains(workitemOne, workitemTwo));
	}

	@Test
	public void canGetWorkItemById() {

	}
}
