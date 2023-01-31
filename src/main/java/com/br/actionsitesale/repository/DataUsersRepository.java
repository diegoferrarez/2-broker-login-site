package com.br.actionsitesale.repository;

import com.br.actionsitesale.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataUsersRepository extends MongoRepository<Users, String> {
}
