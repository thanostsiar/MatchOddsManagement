package com.atsiaras.matchodds.entities;

import com.atsiaras.matchodds.enums.Sport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate match_date;

    @Column(nullable = false)
    private LocalTime match_time;

    @Column(nullable = false)
    private String team_a;

    @Column(nullable = false)
    private String team_b;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sport sport;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MatchOdds> odds;
}
