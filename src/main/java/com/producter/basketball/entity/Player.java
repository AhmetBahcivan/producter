package com.producter.basketball.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Player {
    /*
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private UUID id;


     */
    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=3, max = 20)
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min=3, max = 20)
    @NotBlank
    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "position", nullable = false)
    private Position position;

    @ManyToOne
    @JoinColumn(name = "fk_team_id", nullable = false)
    private Team team;

    public Player(String name, String surname, Position position) {
        this.name = name;
        this.surname = surname;
        this.position = position;
    }
    public Player(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }


}


