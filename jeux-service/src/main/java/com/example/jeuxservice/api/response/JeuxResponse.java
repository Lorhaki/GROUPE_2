package com.example.jeuxservice.api.response;

import com.example.jeuxservice.api.dto.JeuxDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JeuxResponse {
    private List<JeuxDto> jeux ;
}
