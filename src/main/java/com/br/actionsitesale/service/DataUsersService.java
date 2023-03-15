package com.br.actionsitesale.service;

import com.br.actionsitesale.controller.dto.request.DataRequest;
import com.br.actionsitesale.controller.dto.response.DataResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DataUsersService {

   DataResponse createUsers(DataRequest request);
   ResponseEntity<Optional<DataRequest>> InactivateUser(String id);
   List<DataResponse> findAll();
   Optional<DataResponse> findById(String id);
   List<DataResponse> findUser(String unit, String serialNumberUnit);

}
