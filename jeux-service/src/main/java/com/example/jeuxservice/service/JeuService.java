package com.example.jeuxservice.service;

import com.example.jeuxservice.api.dto.EditeurDto;
import com.example.jeuxservice.api.request.EditeurCreationRequest;
import com.example.jeuxservice.api.request.JeuxCreationRequest;
import com.example.jeuxservice.api.response.EditeurResponse;
import com.example.jeuxservice.entity.Jeux;
import com.example.jeuxservice.repository.JeuxRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JeuService {
    private final JeuxRepository jeuxRepository;
    @Autowired
    private final WebClient.Builder webClient ;


    public Jeux getById(String jeuxId){
        return jeuxRepository.findById(new ObjectId(jeuxId))
                .orElse((null));
    }

    public void appelPostALAutreApiPost(EditeurCreationRequest requestBody) {
        // Création de l'en-tête avec le type de média
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Création de l'entité HTTP avec le corps de la requête et les en-têtes
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);

        // Création du RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        //String url = "http://localhost:8080/editeurs";
        String url = "http://ms-editeur:8080/editeurs";

        // Envoi de la requête POST à l'autre API
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
    }

    public ResponseEntity<String> getAllEdi(){
        String apiUrl = "http://ms-editeur:8080/editeurs";
        //String apiUrl =  "http://localhost:8080/editeurs" ;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        return responseEntity;
    }
    /*
    public List<EditeurResponse> getListeEditeurs(){
         List<EditeurResponse> liste = webClient.baseUrl("http://localhost:8080/")
                .build()
                .get()
                .uri("editeurs")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(EditeurResponse.class)
                .collectList()
                .block();

         return liste;
    }

    public EditeurResponse trouver(String nom){
        List<EditeurResponse> liste = getListeEditeurs();

        for(int i=0; i<liste.size(); i++)
        {
            String s = liste.get(i).getEditeurs().getNom();
            if(s.equals(nom))
            {
                return liste.get(i);
            }
        }
        return null;
    }
*/
    public Jeux create(JeuxCreationRequest request){

        final Jeux jeux = Jeux.builder()
                .nom(request.getNom())
                .nomEdi(request.getNomEdi())
                .build();

        return jeuxRepository.insert(jeux);
    }

    public List<Jeux> getAll(){return jeuxRepository.findAll();}
}
