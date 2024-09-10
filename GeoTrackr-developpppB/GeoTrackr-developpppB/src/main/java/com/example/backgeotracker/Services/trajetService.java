package com.example.backgeotracker.Services;

import com.example.backgeotracker.DTOEntities.TraceDto;
import com.example.backgeotracker.DTOEntities.TrajetDto;
import com.example.backgeotracker.DTOEntities.userDto;
import com.example.backgeotracker.Entities.Trace;
import com.example.backgeotracker.Entities.Trajet;
import com.example.backgeotracker.Entities.User;
import com.example.backgeotracker.Repositories.traceRepository;
import com.example.backgeotracker.Repositories.trajetRepository;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class trajetService {
    @Autowired
    private ModelMapper modelMapper;
    private  trajetRepository trajetRepository;
    public trajetService(trajetRepository trajetRepository){
        this.trajetRepository=trajetRepository;
    }

    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll();
    }

    public Trajet saveTrajet(Trajet trajet) {
        return trajetRepository.save(trajet);
    }

    public List<TrajetDto> getAllTrajetsWithUserDto() {
        List<Trajet> trajets = trajetRepository.findAll();
        List<TrajetDto> trajetDtos = new ArrayList<>();

        for (Trajet trajet : trajets) {
            TrajetDto trajetDto = modelMapper.map(trajet, TrajetDto.class);

            // Check if the user property is not null before mapping
            if (trajet.getUser() != null) {
                userDto userDto = modelMapper.map(trajet.getUser(), userDto.class);
                trajetDto.setUserDto(userDto);
            }

            // Mapping traces property (similar to the previous example)
            if (trajet.getTraces() != null) {
                List<TraceDto> traceDtos = trajet.getTraces().stream()
                        .map(trace -> modelMapper.map(trace, TraceDto.class))
                        .collect(Collectors.toList());
                trajetDto.setTraces(traceDtos);
            }

            trajetDtos.add(trajetDto);
        }

        return trajetDtos;
    }


    public void deleteTrajet(Long id) {

        trajetRepository.deleteReferencesFromTrajetTrace(id);
        trajetRepository.deleteTrajetById(id);
    }
}
