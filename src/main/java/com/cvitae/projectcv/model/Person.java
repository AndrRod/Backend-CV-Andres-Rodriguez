package com.cvitae.projectcv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;

@Data @Entity @Table
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE person SET deleted = true WHERE id=?")
@Where(clause= "deleted = false")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String typeProgramerName;
    private String toolsName;
    private String profileDescription;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ContactAndPortfolio> contactAndPortFolio;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Skills> skills;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Experience> experiences;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Education> education;
    private Boolean deleted = Boolean.FALSE;
}