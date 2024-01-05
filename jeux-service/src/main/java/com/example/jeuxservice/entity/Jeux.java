package com.example.jeuxservice.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "jeux-videos")
public class Jeux {
    @Id
    private ObjectId id;
    private String nom;

    private String nomEdi;
}
