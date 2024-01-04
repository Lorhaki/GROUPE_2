package com.example.editeurservice.api.response;

import com.example.editeurservice.api.dto.EditeurDto;
import com.example.editeurservice.entity.Editeur;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EditeurResponse {
    private List<EditeurDto> editeurs;


}
