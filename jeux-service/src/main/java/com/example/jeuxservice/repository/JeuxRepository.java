package com.example.jeuxservice.repository;

import com.example.jeuxservice.entity.Jeux;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface JeuxRepository extends MongoRepository<Jeux, ObjectId> {
    List<Jeux> findAllByNom(String nom);
    List<Jeux> findAllById(ObjectId id);

}
