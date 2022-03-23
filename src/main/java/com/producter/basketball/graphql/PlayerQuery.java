package com.producter.basketball.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.producter.basketball.entity.Player;
import com.producter.basketball.entity.Team;
import com.producter.basketball.service.PlayerService;
import com.producter.basketball.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerQuery implements GraphQLQueryResolver {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    public Player findPlayerByName(String name, String surname) {
        return playerService.findPlayerByName(name, surname);
    }

    public Iterable<Player> findAllPlayers() {
        return playerService.findAllPlayers();
    }

    public Iterable<Team> getTeams() {
        return teamService.getTeams();
    }
}
