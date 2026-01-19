package com.example.pm.service;

import com.example.pm.dto.*;
import com.example.pm.entity.Address;
import com.example.pm.entity.Company;
import com.example.pm.entity.Person;
import com.example.pm.dto.PersonRequest;
import com.example.pm.repository.CompanyRepository;
import com.example.pm.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public PersonDetailResponse createPerson(PersonRequest request) {
        // Handle Company logic
        Company company = null;
        if (request.getCompanyId() != null) {
            company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new RuntimeException("Company not found with id: " + request.getCompanyId()));
        } else if (request.getCompany() != null) {
            company = Company.builder()
                    .name(request.getCompany().getName())
                    .industry(request.getCompany().getIndustry())
                    .build();
            company = companyRepository.save(company);
        }

        // Initialize Person
        Person person = Person.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .company(company)
                .build();

        // Handle Addresses logic
        if (request.getAddresses() != null && !request.getAddresses().isEmpty()) {
            List<Address> addressEntities = request.getAddresses().stream()
                    .map(aReq -> Address.builder()
                            .street(aReq.getStreet())
                            .city(aReq.getCity())
                            .state(aReq.getState())
                            .zipCode(aReq.getZipCode())
                            .person(person) // Link to person
                            .build())
                    .collect(Collectors.toList());
            person.setAddresses(addressEntities);
        } else {
            person.setAddresses(new ArrayList<>());
        }

        Person saved = personRepository.save(person);
        return mapToDetailResponse(saved);
    }

    private PersonDetailResponse mapToDetailResponse(Person p) {
        return PersonDetailResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .age(p.getAge())
                .email(p.getEmail())
                .company(p.getCompany() == null ? null : CompanyResponse.builder()
                        .id(p.getCompany().getId())
                        .name(p.getCompany().getName())
                        .industry(p.getCompany().getIndustry())
                        .build())
                .addresses(p.getAddresses() == null ? new ArrayList<>() : p.getAddresses().stream()
                        .map(a -> AddressResponse.builder()
                                .id(a.getId())
                                .street(a.getStreet())
                                .city(a.getCity())
                                .state(a.getState())
                                .zipCode(a.getZipCode())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public PagedResponse<PersonListResponse> getAllPersons(Pageable pageable) {
        Page<Person> personPage = personRepository.findAll(pageable);
        
        var content = personPage.getContent().stream()
                .map(p -> PersonListResponse.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .age(p.getAge())
                        .companyName(p.getCompany() != null ? p.getCompany().getName() : "N/A")
                        .build())
                .collect(Collectors.toList());

        return PagedResponse.<PersonListResponse>builder()
                .content(content)
                .totalElements(personPage.getTotalElements())
                .totalPages(personPage.getTotalPages())
                .size(personPage.getSize())
                .number(personPage.getNumber())
                .build();
    }

    public PersonDetailResponse getPersonById(Long id) {
        Person p = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));

        return mapToDetailResponse(p);
    }
}
