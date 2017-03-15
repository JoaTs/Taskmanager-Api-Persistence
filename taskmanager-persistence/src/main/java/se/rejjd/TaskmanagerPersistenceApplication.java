package se.rejjd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import se.rejjd.model.Issue;
import se.rejjd.model.User;
import se.rejjd.model.WorkItem;
import se.rejjd.model.WorkItem.Status;
import se.rejjd.service.IssueService;
import se.rejjd.service.UserService;
import se.rejjd.service.WorkItemService;


@SpringBootApplication
public class TaskmanagerPersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagerPersistenceApplication.class, args);
	}
	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {

			WorkItem workItem = new WorkItem("title", "description");
			
			WorkItemService workItemService = context.getBean(WorkItemService.class);
			workItemService.addOrUpdateWorkItem(workItem);
			
			User user = new User("usernamhytd", "firstname", "lastnam", "10");
			
			UserService userService = context.getBean(UserService.class);
			
			userService.addOrUpdateUser(user);
			
			workItemService.addUserToWorkItem(workItem, user);
			
			userService.updateUserStatus(user, false);
			
			IssueService issueService = context.getBean(IssueService.class);

			Issue issue = new Issue(workItem, "description");
			
			issueService.addOrUpdate(issue);

		};
	}
}
