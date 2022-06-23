package com.cvitae.projectcv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table @Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ContactAndPortfolio {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String logoUrl;
    private String contactName;
    private String contactUrl;
}
