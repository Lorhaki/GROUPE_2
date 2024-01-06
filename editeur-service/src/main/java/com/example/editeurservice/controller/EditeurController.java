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
        name = "Editeurs CONTROLLER",
        description = "API pour manage les éditeurs"
)
public class EditeurController {
    private final EditeurService editeurService;

    private final EditeurMapper editeurMapper;

    /*CREATE*/
    @PostMapping
    @Operation(
            summary = "Creer un editeur",
            description = "permet de creer un editeur Si doublon dans la base (meme nom) renvoir erreur 500",
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
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "L'utilisateur existe deja dans la BDD ou bien l'entrée n'est pas bonne",
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
            summary = "Permet d'obtenir un éditeur par son ID",
            description = "Obtenir l'éditeur en précisant son ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "L'editeur existe",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = EditeurDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Aucun editeur avec cet ID"
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

    @GetMapping("/byNom/{editeurNom}")
    @Operation(
            summary = "Trouver un éditeur avec son nom",
            description = "Permet d'obtenir un editeur par l'intermédiaire de son nom.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "l'editeur avec ce nom existe",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = EditeurDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Aucun editeur ne posséde ce nom"
                    )
            }
    )
    public ResponseEntity<EditeurDto> getByNom(
            @Parameter(description = "Nom de l'éditeur" , example = "Nintendo")
            @PathVariable
            String editeurNom
    ){
        final Editeur editeur = editeurService.getByNom(editeurNom);

        return editeur != null
                ? ResponseEntity.ok(editeurMapper.toDto(editeur))
                : ResponseEntity.notFound().build();
    }

    /*UPDATE*/
    @PutMapping("/{editeurId}")
    @GetMapping("/{editeurId}")
    @Operation(
            summary = "Metre à jour un editeur avec son id",
            description = "Permet de changer le nom ou la description d'un éditeur",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "L'éditeur à bien été modifié",
                            content = @Content(mediaType = "application/json", schema = @Schema(allOf = EditeurDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Response if the provided data is not valid",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Aucun éditeur ne posééde cet ID"
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
                            description = "Aucun id correspondant , aucun éditeur supprimé"
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
                            description = "Tous les éditeurs sont affichés",
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
