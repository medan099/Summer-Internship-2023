package com.example.backgeotracker.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trace")
public class Trace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name = "label")
    private String label;
    @Column(name = "longitudes")
    private Double longitudes;
    @Column(name = "latitudes")
    private Double latitudes;
    @ManyToMany(mappedBy = "traces")
    @JsonIgnore
    private Set<Trajet> trajets = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private TraceStatus status = TraceStatus.BROUILLON;


}