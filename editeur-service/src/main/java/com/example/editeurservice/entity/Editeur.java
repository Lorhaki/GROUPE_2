package com.example.editeurservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "editeurs")
public class Editeur {
    @Id
    private ObjectId id ;

    private String nom ;
    private String description;

    ArrayList<String> Ljv ; // va contenir la liste des jeux vidéo crées par un éditeur
}
