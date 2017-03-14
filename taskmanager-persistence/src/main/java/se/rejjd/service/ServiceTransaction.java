package se.rejjd.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import se.rejjd.model.AbstractEntity;

@Component
class ServiceTransaction {

	@Transactional
	public <E extends AbstractEntity> E execute(Action<E> action) throws ServiceException {
		return action.action();
	}

	@FunctionalInterface
	public static interface Action<E extends AbstractEntity> {

		E action() throws ServiceException;
	}

}
