package com.example.jeuxservice.controller;

import com.example.jeuxservice.api.dto.JeuxDto;
import com.example.jeuxservice.api.request.JeuxCreationRequest;
import com.example.jeuxservice.api.response.JeuxResponse;
import com.example.jeuxservice.entity.Jeux;
import com.example.jeuxservice.mapper.JeuxMapper;
import com.example.jeuxservice.service.JeuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/jeux")
@Slf4j
@RequiredArgsConstructor
@Tag(
        name = "Jeux controller",
        description = "API to manage Jeux"
)
public class JeuxController {
    private final JeuService jeuService;
    private final JeuxMapper jeuxMapper;
    @PostMapping
    @Operation(
            summary = "Permet de creer un jeux vidéo",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Response if the jeux has been successfully created",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = JeuxDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Response if the provided data is not valid",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<JeuxDto> createJeux(@Valid @RequestBody JeuxCreationRequest request)
    {
        final Jeux jeux = jeuService.create(request);
        final JeuxDto dto = jeuxMapper.toDto(jeux);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{JeuxId}")
    @Operation(
            summary = "Get a Jeux by id",
            description = "Get the jeux with the provided id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Response in case of success",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = JeuxDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Jeux is matching the provided id"
                    )
            }
    )
    public ResponseEntity<JeuxDto> getJeux(
            @Parameter(description = "Id du jeux" , example = "azersdfazefazef11186811666161azer")
            @PathVariable
            String JeuxId
    ){
        final Jeux jeux = jeuService.getById(JeuxId);

        return jeux != null
                ? ResponseEntity.ok(jeuxMapper.toDto(jeux))
                : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Permet d'obtenir l'ensemble des JeuxVidéos",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Response in case of success",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = JeuxResponse.class))
                    ),
            }
    )
    @GetMapping
    public ResponseEntity<JeuxResponse> getAllEditeurs(){
        final List<Jeux> jeux = jeuService.getAll();
        final JeuxResponse response = jeuxMapper.toResponse(jeux);

        return ResponseEntity.ok(response);
    }
}
