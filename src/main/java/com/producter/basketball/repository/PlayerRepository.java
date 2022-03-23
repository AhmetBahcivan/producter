package com.producter.basketball.repository;

import com.producter.basketball.entity.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface PlayerRepository extends CrudRepository<Player, Long> {

    List<Player> findAllByNameAndSurname(String name, String surname);

    Optional<Player> findFirstByNameAndSurname(String name, String surname);

    long countPlayerByTeam_Name(String name);
}
