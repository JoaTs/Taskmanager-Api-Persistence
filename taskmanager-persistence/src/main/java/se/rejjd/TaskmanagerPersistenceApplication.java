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

//			 TeamService teamService = context.getBean(TeamService.class);
//			
//			 Team team = teamService.addOrUpdateTeam(new Team("teamName3"));
//			 teamService.addOrUpdateTeam(new Team("teamName2"));
//			 teamService.addOrUpdateTeam(new Team("teamName3"));
//			 teamService.addOrUpdateTeam(new Team("teamName4"));
			
//			 User user = new User("usernamhytdasddd", "firstname", "lastnam",
//			 "2002");
//			 User user2 = new User("usernamhytdasdddd", "firstname",
//			 "lastnam", "2003");
//			
//			 UserService userService = context.getBean(UserService.class);
//			
//			 userService.addOrUpdateUser(user);
//			 userService.addOrUpdateUser(user2);
			
//			 teamService.addUserToTeam(user, team);
//			 teamService.addUserToTeam(user2, team);
			
			
			
			
//			//WORKITEMS
//			 WorkItem workItem = new WorkItem("title12", "description12");
//			 WorkItem workItem1 = new WorkItem("title122", "description12");
//			 WorkItem workItem2 = new WorkItem("title123", "description12");
//			 WorkItem workItem3 = new WorkItem("title124", "description12");
//			 WorkItem workItem4 = new WorkItem("title125", "description12");
//			 WorkItem workItem5 = new WorkItem("title126", "description12");
//			
			//SERVICE
//			 WorkItemService workItemService =
//			 context.getBean(WorkItemService.class);
			
			
//			 workItemService.addOrUpdateWorkItem(workItem);
//			 workItemService.addOrUpdateWorkItem(workItem1);
//			 workItemService.addOrUpdateWorkItem(workItem2);
//			 workItemService.addOrUpdateWorkItem(workItem3);
//			 workItemService.addOrUpdateWorkItem(workItem4);
//			 workItemService.addOrUpdateWorkItem(workItem5);

			
			
			
//			 workItemService.addUserToWorkItem(workItem, user);
			
//			 userService.updateUserStatus(user, false);
			
			
//			 WorkItem workItem = workItemService.getWorkItemById(8L);
//			 
//			 IssueService issueService = context.getBean(IssueService.class);
			 
//			 Issue issue = new Issue(workItem, "description");
			
//			 issueService.addIssue(workItem, "testes");
//			 addOrUpdate(issue);

		};
	}
}
