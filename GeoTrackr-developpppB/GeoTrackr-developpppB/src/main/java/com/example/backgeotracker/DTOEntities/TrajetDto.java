package com.example.backgeotracker.DTOEntities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class TrajetDto {
    private Long id;
    private Date date;
    private List<TraceDto> traces;
    private userDto userDto;
}