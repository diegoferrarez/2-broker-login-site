package com.br.actionsitesale.service.impl;

import com.br.actionsitesale.controller.dto.DataRequest;
import com.br.actionsitesale.controller.dto.DataResponse;
import com.br.actionsitesale.model.Users;
import com.br.actionsitesale.model.enums.StatusLogin;
import com.br.actionsitesale.repository.DataUsersRepository;
import com.br.actionsitesale.service.DataUsersService;
import com.br.actionsitesale.service.mapper.RegisterProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.*;


@Service
@Slf4j
public class DataUsersServiceImpl implements DataUsersService {

    private final RegisterProductMapper mapper;
    @Autowired
    private DataUsersRepository repository;

    @Override
    @Transactional
    public DataResponse createUsers(DataRequest dto) {
        var registerProduct = this.repository.save(savedUser(dto));
        return this.mapper.toResponse(registerProduct);
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<DataRequest>> InactivateUser(String id) {
        Optional<Users> product = repository.findById(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        product.ifPresent(s -> {
            s.setStatusCorp(StatusLogin.INACTIVE);
            repository.save(s);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public List<DataResponse> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Users> users = repository.findAll();
        return Arrays.asList(modelMapper.map(users, DataResponse[].class));
    }

    @Override
    @Transactional
    public Optional<DataResponse> findById(String id){
        return repository.findById(id).map(DataResponse::converter);
    }

    @Override
    public Optional<DataResponse> findByName(String userLogin, String password) {
        return Optional.empty();
    }

    private Users savedProduct (DataRequest p){
        return Users.builder()
                .login(p.getLogin())
                .password(p.getPassword())
                .numberCorp(p.getNumberCorp())
                .emailCorp(p.getEmailCorp())
                .statusCorp(p.getStatusCorp())
                .build();
    }

    private Users savedUser (DataRequest p) {
        String uuid = "corp_" + UUID.randomUUID().toString().substring(0,3);
        return Users.builder()
                .login(p.getLogin())
                .password(p.getPassword())
                .numberCorp(uuid)
                .emailCorp(p.getEmailCorp())
                .statusCorp(p.getStatusCorp())
                .build();
    }


    public DataUsersServiceImpl(RegisterProductMapper mapper) {
        this.mapper = mapper;
    }
}
