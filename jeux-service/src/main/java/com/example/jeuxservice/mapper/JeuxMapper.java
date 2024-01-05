package com.example.jeuxservice.mapper;

import com.example.jeuxservice.api.dto.JeuxDto;
import com.example.jeuxservice.entity.Jeux;
import org.springframework.stereotype.Component;

@Component
public class JeuxMapper {

    public JeuxDto toDto(Jeux jeu)
    {
        return JeuxDto.builder()
                .id(jeu.getId().toHexString())
                .nom(jeu.getNom())
                .editeur(jeu.getIdAditeur().toHexString())
                .build();
    }
}
