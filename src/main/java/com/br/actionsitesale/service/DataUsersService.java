package com.br.actionsitesale.service;

import com.br.actionsitesale.controller.dto.DataRequest;
import com.br.actionsitesale.controller.dto.DataResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DataUsersService {

   DataResponse createUsers(DataRequest request);
   ResponseEntity<Optional<DataRequest>> InactivateUser(String id);
   List<DataResponse> findAll();
   Optional<DataResponse> findById(String id);
   Optional<DataResponse> findByName(String userLogin, String password);

}
