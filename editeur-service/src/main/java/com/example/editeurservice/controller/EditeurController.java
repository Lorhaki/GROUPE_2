package com.example.editeurservice.controller;

import com.example.editeurservice.api.dto.EditeurDto;
import com.example.editeurservice.api.request.EditeurCreationRequest;
import com.example.editeurservice.api.request.EditeurUpdateRequest;
import com.example.editeurservice.api.response.EditeurResponse;
import com.example.editeurservice.entity.Editeur;
import com.example.editeurservice.mapper.EditeurMapper;
import com.example.editeurservice.service.EditeurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/editeurs")
@Slf4j
@RequiredArgsConstructor
@Tag(
        name = "Editeurs controller",
        description = "API to manage Editeur"
)
public class EditeurController {
    private final EditeurService editeurService;

    private final EditeurMapper editeurMapper;

    /*CREATE*/
    @PostMapping
    @Operation(
            summary = "Create an equipment",
            description = "Create an equipment from the provided data.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Response if the editeur has been successfully created",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = EditeurDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Response if the provided data is not valid",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<EditeurDto> createEditeur(@Valid @RequestBody EditeurCreationRequest request) {
        final Editeur editeur = editeurService.create(request);
        final EditeurDto dto = editeurMapper.toDto(editeur);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    /*GET*/
    @GetMapping("/{editeurId}")
    @Operation(
            summary = "Get an equipment by id",
            description = "Get the equipment with the provided id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Response in case of success",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = EditeurDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No editeur is matching the provided id"
                    )
            }
    )
    public ResponseEntity<EditeurDto> getEditeur(
            @Parameter(description = "Id de l'éditeur" , example = "azersdfazefazef11186811666161azer")
            @PathVariable
            String editeurId

    ){
        final Editeur editeur = editeurService.getById(editeurId);

        return editeur != null
                ? ResponseEntity.ok(editeurMapper.toDto(editeur))
                : ResponseEntity.notFound().build();
    }

    /*UPDATE*/
    @PutMapping("/{editeurId}")
    @GetMapping("/{editeurId}")
    @Operation(
            summary = "Update an editeur by id",
            description = "Update the editeur with the provided id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Reponse en cas de succés",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = EditeurDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Response if the provided data is not valid",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No editeur is matching the provided id"
                    )
            }
    )
    public ResponseEntity<EditeurDto> updateEditeur(
            @PathVariable
            @Parameter(description = "Id de l'éditeur")
            String editeurId,

            @Valid
            @RequestBody
            EditeurUpdateRequest request
    ){
        final Editeur editeur = editeurService.update(editeurId,request);

        return editeur != null
                ? ResponseEntity.ok(editeurMapper.toDto(editeur))
                : ResponseEntity.notFound().build();
    }

    /*DELETE*/
    @DeleteMapping("/{editeurId}")
    @GetMapping("/{editeurId}")
    @Operation(
            summary = "Effacer un editeur par son Id" ,
            description = "Effacer un editeur avec le bon id",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "L'editeur à bien été supprimé"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Aucun id correspondant"
                    )
            }
    )
    public ResponseEntity<Void> deleteEditeur(
            @Parameter(description = "Id de l'éditeur à delete")
            @PathVariable String editeurId){
        editeurService.delete(editeurId);
        return ResponseEntity.accepted().build();
    }

    @Operation(
            summary = "Permet d'obtenir l'ensemble des éditeurs",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Response in case of success",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = EditeurResponse.class))
                    ),
            }
    )
    @GetMapping
    public ResponseEntity<EditeurResponse> getAllEditeurs(){
        final List<Editeur> editeurs = editeurService.getAll();
        final EditeurResponse response = editeurMapper.toResponse(editeurs);

        return ResponseEntity.ok(response);
    }
}
