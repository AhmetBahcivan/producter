package com.producter.basketball.repository;

import com.producter.basketball.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface TeamRepository extends CrudRepository<Team, Long> {

    Optional<Team> findTeamByName(String name);
}
