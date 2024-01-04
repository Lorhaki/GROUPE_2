package com.example.editeurservice.mapper;

import com.example.editeurservice.api.dto.EditeurDto;
import com.example.editeurservice.api.response.EditeurResponse;
import com.example.editeurservice.entity.Editeur;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EditeurMapper {

    /**
     * Converts a {@link Editeur} to an {@link EditeurDto}
     *
     * @param editeur the {@link Editeur} to map
     * @return The mapped dto
     */
    public EditeurDto toDto(Editeur editeur){
        return EditeurDto.builder()
                .id(editeur.getId().toHexString())
                .nom(editeur.getNom())
                .description((editeur.getDescription()))
                .Ljv((editeur.getLjv()))
                .build();
    }

    public EditeurResponse toResponse(List<Editeur> editeurs) {
        final List<EditeurDto> dtos = editeurs.stream()
                .map(this::toDto)
                .toList();

        return EditeurResponse.builder()
                .editeurs(dtos)
                .build();

    }
}
