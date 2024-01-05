package com.example.jeuxservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JeuService {
    private final JeuxRepository jeuxRepository;

}
