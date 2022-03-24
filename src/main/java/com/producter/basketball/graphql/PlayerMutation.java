package com.producter.basketball.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.producter.basketball.entity.Player;
import com.producter.basketball.entity.Position;
import com.producter.basketball.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

@Component
@Validated
public class PlayerMutation implements GraphQLMutationResolver {

    @Autowired
    private PlayerService playerService;

    public Player addPlayer(@Valid String name, @Valid String surname, @Valid Position position, @Valid String teamName) {
       return playerService.addPlayer(name, surname, position, teamName);
    }

    public Boolean deletePlayer(@Valid String name, @Valid String surname) {
        return playerService.deletePlayer(name, surname);
    }

}
