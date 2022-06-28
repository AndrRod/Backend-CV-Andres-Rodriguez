package com.cvitae.projectcv.model;

import com.cvitae.projectcv.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Table @Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Education {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private State state;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate finishDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}
