package com.example.backgeotracker.DTOEntities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class userDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String username;

    private String role;



}
