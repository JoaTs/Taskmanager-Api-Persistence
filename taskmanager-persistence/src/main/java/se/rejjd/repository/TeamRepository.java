package se.rejjd.repository;

import org.springframework.data.repository.CrudRepository;

import se.rejjd.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

}
