package com.producter.basketball.service;

import com.producter.basketball.exception.*;
import com.producter.basketball.entity.Team;
import com.producter.basketball.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team addTeam(String name) {
        Optional<Team> teamOptional = teamRepository.findTeamByName(name);
        if(teamOptional.isPresent()) {
            throw new TeamAlreadyExistsException("Team already exists with this team name: " + name + ", please try another one", name);
        }

        Team team = new Team();
        team.setName(name);

        teamRepository.save(team);
        return team;
    }

    public Iterable<Team> getTeams() {
        return teamRepository.findAll();
    }

}
