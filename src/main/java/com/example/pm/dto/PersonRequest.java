package com.example.pm.dto;

import com.example.pm.dto.AddressRequest;
import com.example.pm.dto.CompanyRequest;
import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    private String name;
    private Integer age;
    private String email;
    private Long companyId; // Use if linking to existing
    private CompanyRequest company; // Use if creating new
    private List<AddressRequest> addresses;
}
