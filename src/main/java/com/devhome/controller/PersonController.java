package com.devhome.controller;

import com.devhome.entity.Person;
import com.devhome.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/persons")
    public ResponseEntity<?> getAllPersons() {
        Iterable<Person> allPersons = personRepository.findAll();
        return new ResponseEntity<>(allPersons, HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<?> createPerson(@RequestBody Person person) {

        person = personRepository.save(person);

        HttpHeaders responseHeaders = new HttpHeaders();

        URI newPerson = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId())
                .toUri();

        responseHeaders.setLocation(newPerson);
        return new ResponseEntity(person, HttpStatus.CREATED);
    }

}
