package com.example.jeuxservice.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JeuxDto {
    private String id;
    private String nom;
    private String editeur;
}
