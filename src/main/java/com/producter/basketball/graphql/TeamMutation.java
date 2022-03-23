package com.producter.basketball.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.producter.basketball.entity.Team;
import com.producter.basketball.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

@Component
@Validated
public class TeamMutation implements GraphQLMutationResolver {

    @Autowired
    private TeamService teamService;

    public Team addTeam(@Valid String name) {
        return teamService.addTeam(name);
    }
}
