package com.example.backgeotracker.DTOEntities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraceDto {
    private Long id;
    private String label;
    private Double longitudes;
    private Double latitudes;

}
