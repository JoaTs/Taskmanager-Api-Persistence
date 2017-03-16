package se.rejjd.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import se.rejjd.AbstractTest;
import se.rejjd.model.User;
import se.rejjd.repository.UserRepository;

public class UserServiceTest extends AbstractTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepository;

	@Test
	public void shouldThrowExceptionWhenUsernameTooShort() throws ServiceException {
		expectedException.expect(ServiceException.class);
		expectedException.expectMessage("Username is too short!");
		User user = new User("cl1", "Robert", "Savela", "12");
		userService.addOrUpdateUser(user);
	}

}
