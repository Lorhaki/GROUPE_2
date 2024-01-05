package com.example.jeuxservice.service;

import com.example.jeuxservice.api.request.JeuxCreationRequest;
import com.example.jeuxservice.entity.Jeux;
import com.example.jeuxservice.repository.JeuxRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JeuService {
    private final JeuxRepository jeuxRepository;



    public Jeux getById(String jeuxId){
        return jeuxRepository.findById(new ObjectId(jeuxId))
                .orElse((null));
    }


    public Jeux create(JeuxCreationRequest request){
        final Jeux jeux = Jeux.builder()
                .nom(request.getNom())
                .idEditeur(request.getIdEditeur())
                .build();

        return jeuxRepository.insert(jeux);
    }
}
