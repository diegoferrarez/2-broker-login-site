package com.br.actionsitesale.repository;

import com.br.actionsitesale.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataUsersRepository extends MongoRepository<Users, String> {

    @Query("{$and : [{'unit' : ?0 }, {'serialNumberUnit' : ?1 } ]}")
    List<Users> findCredentialByHeader(String unit, String serialNumberUnit);
}
