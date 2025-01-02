package com.jose.gabriel.tds.challenge.repository;

import com.jose.gabriel.tds.challenge.entities.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}