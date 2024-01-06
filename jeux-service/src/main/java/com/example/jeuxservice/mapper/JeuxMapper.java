package com.example.jeuxservice.mapper;

import com.example.jeuxservice.api.dto.JeuxDto;
import com.example.jeuxservice.api.response.JeuxCreationResponse;
import com.example.jeuxservice.api.response.EditeurResponse;
import com.example.jeuxservice.api.response.JeuxResponse;
import com.example.jeuxservice.entity.Jeux;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JeuxMapper {

    public JeuxDto toDto(Jeux jeu)
    {
        return JeuxDto.builder()
                .id(jeu.getId().toHexString())
                .nom(jeu.getNom())
                .nomEdi(jeu.getNomEdi())
                .build();
    }

    public JeuxResponse toResponse(List<Jeux> jeu){
        final List<JeuxDto> dtos = jeu.stream()
                .map(this::toDto)
                .toList();

        return JeuxResponse.builder()
                .jeux(dtos)
                .build();
    }

    public JeuxCreationResponse editeurCreationResponse(Jeux jeux)
    {
        return JeuxCreationResponse.builder()
                .nom(jeux.getNom())
                .nomEdi(jeux.getNomEdi())
                .id(jeux.getId().toHexString())
                .message("L'eiteur existait déjà, un nouveau jeu a été créé mais pas de nouvel editeur").build();
    }
}
