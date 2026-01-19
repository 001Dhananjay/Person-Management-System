package com.example.pm.dto;

import lombok.*;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PersonDetailResponse {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private CompanyResponse company;
    private List<AddressResponse> addresses;
}
