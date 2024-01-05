package com.example.jeuxservice.api.response;

import com.example.jeuxservice.api.dto.EditeurDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class EditeurResponse {
    private EditeurDto editeurs;

}
