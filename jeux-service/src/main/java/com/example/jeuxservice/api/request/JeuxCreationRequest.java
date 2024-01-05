package com.example.jeuxservice.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JeuxCreationRequest {
    @NotBlank
    private String nom;
    @NotBlank
    private String idEditeur;

}
