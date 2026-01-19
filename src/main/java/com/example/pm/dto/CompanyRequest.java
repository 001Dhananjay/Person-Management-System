package com.example.pm.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CompanyRequest {
    private String name;
    private String industry;
}
