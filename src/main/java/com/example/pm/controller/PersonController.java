package com.example.pm.controller;

import com.example.pm.dto.PagedResponse;
import com.example.pm.dto.PersonDetailResponse;
import com.example.pm.dto.PersonListResponse;
import com.example.pm.dto.PersonRequest;
import com.example.pm.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // For demo purposes
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<PagedResponse<PersonListResponse>> getPersons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(personService.getAllPersons(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDetailResponse> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDetailResponse> createPerson(@RequestBody PersonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(request));
    }
}
