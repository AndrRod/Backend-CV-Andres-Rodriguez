package com.cvitae.projectcv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data @Entity @Table
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE person SET deleted = true WHERE id=?")
@Where(clause= "deleted = false")
@JsonIgnoreProperties(value="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String typeProgramerName;
    private String toolsName;
    private String profileDescription;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private Collection<ContactAndPortfolio> contactAndPortfolio;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private Collection<Skills> skills;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private Collection<Experience> experiences;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private Collection<Education> education;
    private Boolean deleted = Boolean.FALSE;
    @Email(regexp = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message="Email format error")
    private String email;
    @Size(min = 8, message = "The password must be at least 8 characters")
    private String password;
}