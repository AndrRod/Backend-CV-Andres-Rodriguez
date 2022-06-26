package com.cvitae.projectcv.service.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cvitae.projectcv.dto.PersonDtoCreate;
import com.cvitae.projectcv.dto.PersonDtoPart;
import com.cvitae.projectcv.dto.UserTokenResponseDto;
import com.cvitae.projectcv.dto.mapper.PersonMapper;
import com.cvitae.projectcv.messagesHandler.BadRequestException;
import com.cvitae.projectcv.messagesHandler.MessageGeneral;
import com.cvitae.projectcv.messagesHandler.NotFoundException;
import com.cvitae.projectcv.model.Person;
import com.cvitae.projectcv.repository.PersonRepository;
import com.cvitae.projectcv.service.PersonAuthService;
import com.cvitae.projectcv.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class PersonServiceImpl implements PersonService, PersonAuthService, UserDetailsService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public PersonDtoPart createPerson(PersonDtoPart personDtoPart) {
        if(!personRepository.findAll().isEmpty()) throw new BadRequestException("Ya existe un perfil");
        return personMapper.entityToDto(personRepository.save(personMapper.dtoToEntity(personDtoPart)));
    }
    @Override
    public PersonDtoPart updatePerson(PersonDtoPart personDtoPart) {
        Person person = getPersonEntity();
        Optional.of(person).stream().forEach(
                (e)-> {
                if(personDtoPart.getImageProfileUrl() != null) e.setImageProfileUrl(personDtoPart.getImageProfileUrl());
                if(personDtoPart.getFirstName() != null) e.setFirstName(personDtoPart.getFirstName());
                if(personDtoPart.getLastName() != null) e.setLastName(personDtoPart.getLastName());
                if(personDtoPart.getProfileDescription() != null)  e.setProfileDescription(personDtoPart.getProfileDescription());
                if(personDtoPart.getTypeProgramerName()!= null) e.setTypeProgramerName(personDtoPart.getTypeProgramerName());
                if(personDtoPart.getToolsName() != null) e.setToolsName(personDtoPart.getToolsName());
                }
        );
        return personMapper.entityToDto(personRepository.save(person));
    }
    @Override
    public PersonDtoPart getPerson() {
        return personMapper.entityToDto(getPersonEntity());
    }

    @Override
    public Person getPersonEntity() {
        if(personRepository.findAll().isEmpty()) throw new NotFoundException("Perfil no encontrado");
        return personRepository.findAll().stream().findFirst().get();
    }

    @Override
    public String deletePerson(HttpServletRequest request){
        personRepository.delete(getPersonEntity());
            return "the perfil was deleted succesful";
    }

    @Override
    public Authentication authenticationFilter(String email, String password) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication autenticacionFilter = authenticationManager.authenticate(authenticationToken);
        return autenticacionFilter;
    }
    public Person findUserByEmail(String email){
        return Optional.ofNullable(personRepository.findByEmail(email)).orElseThrow(() -> new NotFoundException("person not found"));
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person user = findUserByEmail(email);
        Collection<SimpleGrantedAuthority> authorizations = Collections.singleton(new SimpleGrantedAuthority("none"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorizations);
    }
    @Override
    public UserTokenResponseDto userLogin(String email, String password, HttpServletRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Person user = findUserByEmail(email);
        if(password == null) throw new BadRequestException("password cannot be empty");
        if(!passwordEncoder.matches(password, user.getPassword())) throw new NotFoundException("the password is not the same");
        org.springframework.security.core.userdetails.User userAut = (org.springframework.security.core.userdetails.User) authenticationFilter(email, password).getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        String access_token = JWT.create()
                .withSubject(userAut.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10 minutos
                .withIssuer(request.getRequestURL().toString())
                .withClaim("role",userAut.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String update_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minutos
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        return new UserTokenResponseDto(user.getEmail(), access_token,  update_token);
    }

    @Override
    public void refreshToken(UserTokenResponseDto form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(form.getRefresh_Token() == null || !form.getRefresh_Token().startsWith("Bearer ")) throw new BadRequestException("token refresh error");
        try {
            String refresh_token = form.getRefresh_Token().substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String email = decodedJWT.getSubject();
            Person user = findUserByEmail(email);
            String acceso_token = JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10 minutos
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("role", Collections.singletonList("none"))
                    .sign(algorithm);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),  new HashMap<>(){{put("message", "the user " + user.getEmail()+ " refresh the token succesfully"); put("access_token", acceso_token); put("update_token", refresh_token);}});
        }catch (Exception exception){
            response.setStatus(FORBIDDEN.value());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), new MessageGeneral(exception.getMessage(), request.getRequestURI()));
        }
    }
    @Override
    public MessageGeneral registerUser(PersonDtoCreate personDtoCreate, HttpServletRequest request) {
        if (personRepository.existsByEmail(personDtoCreate.getEmail()))throw new BadRequestException("email already exist");
        if(personDtoCreate.getPassword()== null) throw new BadRequestException("password is not present");
        Person user = personMapper.dtoToCreateEntity(personDtoCreate);
        personRepository.save(user);
        return new MessageGeneral("Person created", request.getRequestURI());
    }

}
