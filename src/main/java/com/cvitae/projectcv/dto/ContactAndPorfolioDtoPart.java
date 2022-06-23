package com.cvitae.projectcv.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter @AllArgsConstructor
public class ContactAndPorfolioDtoPart {
    private Long id;
    private String logoUrl;
    private String contactName;
    private String contactUrl;
}
