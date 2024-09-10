package com.example.backgeotracker.Repositories;

import com.example.backgeotracker.Entities.Trace;
import com.example.backgeotracker.Entities.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface trajetRepository extends JpaRepository<Trajet, Long> {
    Optional<Trajet> findById(Long id);
    @Modifying
    @Query(value = "DELETE FROM trajet_trace WHERE trajet_id = :id", nativeQuery = true)
    void deleteReferencesFromTrajetTrace(@Param("id") Long id);
    void deleteTrajetById(Long id);
}
