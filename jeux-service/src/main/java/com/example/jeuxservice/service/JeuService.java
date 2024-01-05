package com.example.jeuxservice.service;

import com.example.jeuxservice.api.request.JeuxCreationRequest;
import com.example.jeuxservice.api.response.EditeurResponse;
import com.example.jeuxservice.entity.Jeux;
import com.example.jeuxservice.repository.JeuxRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JeuService {
    private final JeuxRepository jeuxRepository;


    private final WebClient.Builder webClient ;

    public Jeux getById(String jeuxId){
        return jeuxRepository.findById(new ObjectId(jeuxId))
                .orElse((null));
    }


    public List<EditeurResponse> getListeEditeurs(){
        return webClient.baseUrl("http://localhost:8080/")
                .build()
                .get()
                .uri("editeurs/getAllEditeurs")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(EditeurResponse.class)
                .collectList()
                .block();
    }
    public EditeurResponse trouver(String nom){
        List<EditeurResponse> liste = getListeEditeurs();
        for(int i=0; i<liste.size(); i++)
        {
            if(liste.get(i).getEditeurs().getNom().equals(nom))
            {
                return liste.get(i);
            }
        }
        return null;
    }

    public Jeux create(JeuxCreationRequest request){

        final Jeux jeux = Jeux.builder()
                .nom(request.getNom())
                .nomEdi(request.getNomEdi())
                .build();

        return jeuxRepository.insert(jeux);
    }

    public List<Jeux> getAll(){return jeuxRepository.findAll();}
}
