package com.example.backgeotracker.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trajet")
public class Trajet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private User user;

    @ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.REMOVE} )
    @JoinTable(
            name = "trajet_trace",
            joinColumns = @JoinColumn(name = "trajet_id"),
            inverseJoinColumns = @JoinColumn(name = "trace_id")
    )

    private Set<Trace> traces = new HashSet<>();

    private Date date;

    // Constructors, getters, and setters
}