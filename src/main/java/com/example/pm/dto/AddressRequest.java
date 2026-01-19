package com.example.pm.dto;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AddressRequest {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
