package se.rejjd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import se.rejjd.model.Issue;
import se.rejjd.model.Team;
import se.rejjd.model.User;
import se.rejjd.model.WorkItem;
import se.rejjd.model.WorkItem.Status;
import se.rejjd.service.IssueService;
import se.rejjd.service.TeamService;
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

//			TeamService teamService = context.getBean(TeamService.class);
//			
//			Team team = teamService.addOrUpdateTeam(new Team("teamName3"));
//			teamService.addOrUpdateTeam(new Team("teamName2"));
//			teamService.addOrUpdateTeam(new Team("teamName3"));
//			teamService.addOrUpdateTeam(new Team("teamName4"));
//			
//			WorkItem workItem = new WorkItem("title", "description");
//			
//			WorkItemService workItemService = context.getBean(WorkItemService.class);
//			workItemService.addOrUpdateWorkItem(workItem);
//			
//			User user = new User("usernamhytdasddd", "firstname", "lastnam", "2002");
//			User user2 = new User("usernamhytdasdddd", "firstname", "lastnam", "2003");
//			
//			UserService userService = context.getBean(UserService.class);
//			
//			userService.addOrUpdateUser(user);
//			userService.addOrUpdateUser(user2);
//			
//			teamService.addUserToTeam(user, team);
//			teamService.addUserToTeam(user2, team);
//			
//			workItemService.addUserToWorkItem(workItem, user);
//			
//			userService.updateUserStatus(user, false);
//			
//			IssueService issueService = context.getBean(IssueService.class);
//
//			Issue issue = new Issue(workItem, "description");
//			
//			issueService.addOrUpdate(issue);

		};
	}
}
