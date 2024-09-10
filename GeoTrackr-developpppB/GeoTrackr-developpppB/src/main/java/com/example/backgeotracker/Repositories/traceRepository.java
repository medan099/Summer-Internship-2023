package com.example.backgeotracker.Repositories;

import com.example.backgeotracker.Entities.Trace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface traceRepository extends JpaRepository<Trace, Long> {
    void deleteTraceById(Long id);
    Optional<Trace> findById(Long id);
    @Query(value = "SELECT * FROM trace WHERE status = 'Valide'", nativeQuery = true)
    List<Trace> findValideTraces();

    @Query(value = "SELECT * FROM trace WHERE status = 'BROUILLON'", nativeQuery = true)
    List<Trace> findBrouillons();

}
	