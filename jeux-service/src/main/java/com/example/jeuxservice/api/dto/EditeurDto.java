package com.example.jeuxservice.api.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;

@Getter
@Builder
public class EditeurDto {

    @Schema(
            description = "identifiant unique qu'un éditeur posséde",
            example = "1234578288456abcde778"
    )
    private String id;

    @Schema(
            description = "Le nom de l'éditeur de jeux vidéo ",
            example = "Nintendo"
    )
    private String nom ;

    @Schema(
            description = "Correspond à la description de l'entreprise" +
                    " peux correspondre à l'histoire de l'entreprise",
            example = "Est un studio de jeux-vidéo connu mondialement ..."
    )
    private String description ;

    @Schema(
            description = "Est une liste contenant les jeux vidéo crées par l'entreprise",
            example = "{Pokemon,Zelda,Smash Bros,Mario ...}"
    )
    private ArrayList<String> Ljv;
}
