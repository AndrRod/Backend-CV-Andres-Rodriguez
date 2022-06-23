package com.cvitae.projectcv.dto;

import com.cvitae.projectcv.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class EducationDtoPart {
    private Long id;
    private String title;
    private String description;
    private State state;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime fihishDate;
}
