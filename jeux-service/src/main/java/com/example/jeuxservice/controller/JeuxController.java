package com.example.jeuxservice.controller;

import com.example.jeuxservice.api.dto.JeuxDto;
import com.example.jeuxservice.api.request.EditeurCreationRequest;
import com.example.jeuxservice.api.request.JeuxCreationRequest;
import com.example.jeuxservice.api.response.JeuxCreationResponse;
import com.example.jeuxservice.api.response.EditeurResponse;
import com.example.jeuxservice.api.response.JeuxResponse;
import com.example.jeuxservice.communs.HttpErreurFonctionnelle;
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
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;
    @PostMapping
    @Operation(
            summary = "Permet de creer un jeux vidéo",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Le jeux vidé à bien été créé",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = JeuxDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Imposssbile de crrer le jeux mauvaise entrée",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity createJeux(@Valid @RequestBody JeuxCreationRequest request)
    {
        try{
            final Jeux jeux = jeuService.create(request);
            final JeuxDto dto = jeuxMapper.toDto(jeux);

            EditeurCreationRequest e = new EditeurCreationRequest(request.getNomEdi(), "");
            jeuService.appelPostALAutreApiPost(e);

            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e){

            return verifNom(request);
        }

    }

    /*private void creatEditeur(String nomEditeur, String description){

        String editeurApiUrl = "http://editeurs:8080/editeurs";

        EditeurCreationRequest editeurRequest = new EditeurCreationRequest();
        editeurRequest.setDescription(description);
        editeurRequest.setNom(nomEditeur);

        restTemplate.postForEntity(editeurApiUrl,editeurRequest, Void.class);
    }*/

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
    public ResponseEntity<JeuxResponse> getAllJeux(){
        final List<Jeux> jeux = jeuService.getAll();
        final JeuxResponse response = jeuxMapper.toResponse(jeux);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Permet d'obtenir l'ensemble des éditeurs présent dans l'autra API",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Response in case of success",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = EditeurResponse.class))
                    ),
            }
    )
    @GetMapping("/jeux-editeurs")
    public  ResponseEntity<String>getAllEditeur(){

       return jeuService.getAllEdi();
    }

    public ResponseEntity verifNom(JeuxCreationRequest request)
    {
        List<Jeux> liste = jeuService.getByNom(request.getNom());
        for (Jeux j:
                liste) {
            if(j.getNomEdi().equals(request.getNomEdi()))
            {
                return ResponseEntity.ok(jeuxMapper.editeurCreationResponse(j));
            }

        }
        return ResponseEntity
                .badRequest()
                .body(HttpErreurFonctionnelle.builder().message("Le jeu"+request.getNom()+ " n'a pas été créé avec l'éditeur "+request.getNomEdi()+" qui existait déjà").build());
    }


}
