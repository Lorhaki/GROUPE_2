package com.example.jeuxservice.repository;

import com.example.jeuxservice.entity.Jeux;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JeuxRepository extends MongoRepository<Jeux, ObjectId> {

}
