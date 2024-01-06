package com.example.jeuxservice.api.response;

import com.example.jeuxservice.api.dto.EditeurDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JeuxCreationResponse {
    private String id;
    private String nom;
    private String nomEdi;
    String message;
}
