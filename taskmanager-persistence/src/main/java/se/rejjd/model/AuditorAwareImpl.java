package se.rejjd.model;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<User> {

	private User auditor;

	public void setAuditor(User auditor) {
		this.auditor = auditor;
	}

	@Override
	public User getCurrentAuditor() {
		return auditor;
	}

}
