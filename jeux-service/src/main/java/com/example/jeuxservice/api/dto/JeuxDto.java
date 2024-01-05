package com.example.jeuxservice.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JeuxDto {
    @Schema(
            description = "Correspond à l'id du jeux"
                 ,
            example = "1635616116qzthj"
    )
    private String id;

    @Schema(
            description = "Correspond au nom du jeux vidéo "
                    ,
            example = "Pokemon"
    )
    private String nom;

    @Schema(
            description = "Correspond au nom de l'entrerpsie",
            example = "sdhsfb7214"
    )
    private String nomEdi;
}
