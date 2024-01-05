package com.example.jeuxservice.controller;

import com.example.jeuxservice.api.dto.JeuxDto;
import com.example.jeuxservice.api.request.JeuxCreationRequest;
import com.example.jeuxservice.entity.Jeux;
import com.example.jeuxservice.mapper.JeuxMapper;
import com.example.jeuxservice.service.JeuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/jeux")
@Slf4j
@RequiredArgsConstructor
@Tag(
        name = "Jeux controller",
        description = "API to manage Jeux"
)
public class JeuxController {
    private final JeuxMapper jeuxMapper;

    private final JeuService jeuService;
/*
    @PostMapping
    public ResponseEntity<JeuxDto> createJeu(@Valid @RequestBody JeuxCreationRequest request)
    {
        final Jeux jeu = jeuService.create(request)
    }

 */


}
