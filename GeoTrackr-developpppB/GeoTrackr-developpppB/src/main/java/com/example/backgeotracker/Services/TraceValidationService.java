package com.example.backgeotracker.Services;

import com.example.backgeotracker.Entities.Trace;
import com.example.backgeotracker.Entities.TraceStatus;
import com.example.backgeotracker.Repositories.traceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraceValidationService {

    @Autowired
    private traceRepository tracerepository;
    public Trace validerTrace(Long Id) {
        Trace trace = tracerepository.findById(Id).orElse(null);
        if (trace != null) {
            trace.setStatus(TraceStatus.VALIDE);
            return tracerepository.save(trace);
        }
        return null;
    }

    public Trace rejeterTrace(Long Id) {
        Trace trace = tracerepository.findById(Id).orElse(null);
        if (trace != null) {
            trace.setStatus(TraceStatus.REJETE);
            return tracerepository.save(trace);
        }
        return null;
    }


}