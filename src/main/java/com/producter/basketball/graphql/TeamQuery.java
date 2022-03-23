package com.producter.basketball.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.producter.basketball.entity.Team;
import com.producter.basketball.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

public class TeamQuery implements GraphQLQueryResolver {

    @Autowired
    private TeamService teamService;

    public Iterable<Team> getTeams() {
        return teamService.getTeams();
    }
}
