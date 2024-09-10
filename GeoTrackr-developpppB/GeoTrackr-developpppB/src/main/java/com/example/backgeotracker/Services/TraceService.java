package com.example.backgeotracker.Services;


import com.example.backgeotracker.Entities.Trace;
import com.example.backgeotracker.Entities.TraceStatus;
import com.example.backgeotracker.Repositories.traceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
@Transactional
public class TraceService {
    @Autowired
    private com.example.backgeotracker.Repositories.traceRepository traceRepository;

    public TraceService(traceRepository traceRepository){
        this.traceRepository=traceRepository;
    }

    public Trace addTraceByAdmin(Trace trace) {
        trace.setStatus(TraceStatus.VALIDE);
        return traceRepository.save(trace);
    }
    public Trace addTrace(Trace trace) {
        return traceRepository.save(trace);
    }


    public Trace updateTrace(Trace trace, Trace updatedTrace) {


        // Update the trace fields based on the provided values in the updatedTrace object
        if (updatedTrace.getLabel() != null) {
            trace.setLabel(updatedTrace.getLabel());
        }

        if (updatedTrace.getLongitudes() != null) {
            trace.setLongitudes(updatedTrace.getLongitudes());
        }

        if (updatedTrace.getLatitudes() != null) {
            trace.setLatitudes(updatedTrace.getLatitudes());
        }

        return traceRepository.save(trace);
    }

    public List<Trace> getAllTraces() {
        return traceRepository.findAll();
    }
    public void deleteTrace(Long id){
        traceRepository.deleteTraceById(id);
    }

    public List<Trace> getValideTraces() {
        return traceRepository.findValideTraces();
    }
    public List<Trace> getBrouillons() {
        return traceRepository.findBrouillons();
    }

}