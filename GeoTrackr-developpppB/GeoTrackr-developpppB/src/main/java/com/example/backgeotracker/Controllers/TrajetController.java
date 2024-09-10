package com.example.backgeotracker.Controllers;

import com.example.backgeotracker.DTOEntities.TrajetDto;
import com.example.backgeotracker.DTOEntities.userDto;
import com.example.backgeotracker.Entities.Trajet;
import com.example.backgeotracker.Entities.User;
import com.example.backgeotracker.Services.trajetService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backgeotracker.Repositories.userRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trajets")
@CrossOrigin("*")
public class TrajetController {
    @Autowired
    private ModelMapper modelMapper;
    private trajetService trajetService;
    @Autowired
    private userRepository userRepository;

    @Autowired
    public TrajetController(trajetService trajetService) {
        super();
        this.trajetService = trajetService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<TrajetDto>> getAllTrajets() {
        List<TrajetDto> trajets = trajetService.getAllTrajetsWithUserDto();
        return ResponseEntity.ok(trajets);
    }

    @PostMapping("/add")
    public ResponseEntity<Trajet> createTrajet(@RequestBody Trajet trajet) {
        String email = trajet.getUser().getEmail();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            trajet.setUser(user);
            Trajet savedTrajet = trajetService.saveTrajet(trajet);
            return ResponseEntity.ok(savedTrajet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteTrajet(@PathVariable Long id) {
            trajetService.deleteTrajet(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }


}
