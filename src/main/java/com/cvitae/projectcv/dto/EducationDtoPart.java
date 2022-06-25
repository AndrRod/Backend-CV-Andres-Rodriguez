package com.cvitae.projectcv.dto;

import com.cvitae.projectcv.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Getter @Setter @AllArgsConstructor
public class EducationDtoPart {
    private Long id;
    private String title;
    private String description;
    private State state;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private LocalDate finishDate;
}
