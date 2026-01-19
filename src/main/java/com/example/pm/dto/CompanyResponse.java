package com.example.pm.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CompanyResponse {
    private Long id;
    private String name;
    private String industry;
}
