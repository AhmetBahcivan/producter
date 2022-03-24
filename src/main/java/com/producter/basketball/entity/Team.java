package com.producter.basketball.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Team {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, max = 20)
    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "team")
    private Set<Player> playerSet  = new HashSet<>();

    public Team(String name) {
        this.name= name;
    }

}
