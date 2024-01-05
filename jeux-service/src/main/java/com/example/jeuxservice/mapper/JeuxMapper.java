package com.example.jeuxservice.mapper;

import com.example.jeuxservice.api.dto.JeuxDto;
import com.example.jeuxservice.api.response.JeuxResponse;
import com.example.jeuxservice.entity.Jeux;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
}
