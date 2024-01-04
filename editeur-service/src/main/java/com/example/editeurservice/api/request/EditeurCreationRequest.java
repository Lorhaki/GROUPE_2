package com.example.editeurservice.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditeurCreationRequest {

    @NotBlank
    private String nom ;

    private String description ;

    private ArrayList<String> Ljv ;


}
