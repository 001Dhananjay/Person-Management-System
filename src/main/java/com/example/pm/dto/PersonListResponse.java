package com.example.pm.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PersonListResponse {
    private Long id;
    private String name;
    private Integer age;
    private String companyName;
}
