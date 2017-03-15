package se.rejjd.service;

import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import se.rejjd.model.AbstractEntity;

@Component
class ServiceTransaction {

	@Transactional
	public <E extends AbstractEntity> E execute(Action<E> action) throws ServiceException {
		try {
			return action.action();
		} catch (DataAccessException e) {
			throw new ServiceException("Nobaie");
		}
	}

	@FunctionalInterface
	public static interface Action<E extends AbstractEntity> {
		E action() throws ServiceException;
	}
}
