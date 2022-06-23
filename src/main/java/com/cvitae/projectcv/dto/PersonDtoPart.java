package com.cvitae.projectcv.dto;

import com.cvitae.projectcv.model.ContactAndPortfolio;
import com.cvitae.projectcv.model.Education;
import com.cvitae.projectcv.model.Experience;
import com.cvitae.projectcv.model.Skills;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;

@Getter @Setter @AllArgsConstructor
public class PersonDtoPart {
    private Long id;
    private String firstName;
    private String lastName;
    private String typeProgramerName;
    private String toolsName;
    private String profileDescription;
    private Collection<ContactAndPortfolio> contactAndPortFolio;
    private Collection<Skills> skills;
    private Collection<Experience> experiences;
    private Collection<Education> education;
}
