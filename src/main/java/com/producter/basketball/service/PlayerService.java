package com.producter.basketball.service;

import com.producter.basketball.exception.PlayerAlreadyExistsException;
import com.producter.basketball.exception.PlayerCountLimitException;
import com.producter.basketball.exception.PlayerNotFoundException;
import com.producter.basketball.exception.TeamNotFoundException;
import com.producter.basketball.entity.Player;
import com.producter.basketball.entity.Position;
import com.producter.basketball.entity.Team;
import com.producter.basketball.repository.PlayerRepository;
import com.producter.basketball.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlayerService {

    private final static int teamPlayerlimit = 16;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Player findPlayerByName(String name, String surname) {
        Optional<Player> playerOptional = playerRepository.findFirstByNameAndSurname(name, surname);
        if(playerOptional.isEmpty()) {
            throw new PlayerNotFoundException("We were unable to find a player with the provided credentials", "name", "surname");
        }
        return playerOptional.get();
    }

    public Iterable<Player> findAllPlayers() {
        System.out.println("findAllPlayers is working");
        return playerRepository.findAll();
    }

    public Player addPlayer(String name, String surname, Position position, String teamName) {
        log.info("addPlaayer function is working");
        Optional<Team> teamOptional = teamRepository.findTeamByName(teamName);
        if(teamOptional.isEmpty()){
            //TODO return no team is found -> something meaningfull
            throw new TeamNotFoundException("We were unable to find a team with the provided name:" + teamName, "name");
        }

        if(playerExists(name, surname)) {
            //TODO user already exist
            throw new PlayerAlreadyExistsException("A user already exists with this name and surname, please try another one", "name", "surname");
        }

        long currentPlayerCount = playerRepository.countPlayerByTeam_Name(teamName);
        if(currentPlayerCount>=teamPlayerlimit) {
            //TODO return out of count -> something meaningfull
            throw new PlayerCountLimitException("Team is already full: " + teamPlayerlimit, teamPlayerlimit);
        }

        Player player = new Player();
        player.setName(name);
        player.setSurname(surname);
        player.setPosition(position);
        player.setTeam(teamOptional.get());
        playerRepository.save(player);

        return player;
    }

    public Boolean deletePlayer(String name, String surname) {

        Optional<Player> playerOptional = playerRepository.findFirstByNameAndSurname(name, surname);
        if(playerOptional.isEmpty()) {
            System.out.println("deletePlayer error");
            throw new PlayerNotFoundException("Player with name: " + name + " surname: " + surname + " is not found! ", "id");
        }
        playerRepository.delete(playerOptional.get());
        return true;

    }

    public boolean playerExists(String name, String surname) {
        List<Player> players = playerRepository.findAllByNameAndSurname(name, surname);
        for (Player user : players){
            if (user.getName().equals(name) && user.getSurname().equals(surname))
                return true;
        }
        return false;
    }

}
