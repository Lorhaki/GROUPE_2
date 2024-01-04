package com.example.editeurservice.repository;

import com.example.editeurservice.entity.Editeur;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface EditeurRepository extends MongoRepository<Editeur, ObjectId> {

    List<Editeur> findAllByNom(String nom);
    List<Editeur> findAllById(ObjectId id);

}
