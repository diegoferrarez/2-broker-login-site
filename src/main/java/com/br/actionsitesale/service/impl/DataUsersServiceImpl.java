package com.br.actionsitesale.service.impl;

import com.br.actionsitesale.controller.dto.request.DataRequest;
import com.br.actionsitesale.controller.dto.response.DataResponse;
import com.br.actionsitesale.model.Users;
import com.br.actionsitesale.model.enums.StatusLogin;
import com.br.actionsitesale.repository.DataUsersRepository;
import com.br.actionsitesale.service.DataUsersService;
import com.br.actionsitesale.service.mapper.RegisterProductMapper;
import com.br.actionsitesale.utils.UserUtils;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var userRegister = savedUser(dto);
        String password = UserUtils.criptografarBase64(dto.getCredential().getPassword());
        userRegister.getCredential().setPassword(password);
        userRegister.setStatusCorp(StatusLogin.ACTIVE);
        var registerUsers = this.repository.save(savedUser(dto));
        return this.mapper.toResponse(registerUsers);
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
    @Transactional
    public List<DataResponse> findUser(String unit, String serialNumberUnit){
        ModelMapper modelMapper = new ModelMapper();
        List<Users> users = repository.findCredentialByHeader(unit, serialNumberUnit);
        return Arrays.asList(modelMapper.map(users, DataResponse[].class));
    }

    private Users savedProduct (DataRequest p){
        return Users.builder()
                .build();
    }

    private Users savedUser (DataRequest p) {
        return Users.builder()
                .unit(p.getUnit())
                .serialNumberUnit(p.getSerialNumberUnit())
                .credential(p.getCredential())
                .statusCorp(StatusLogin.ACTIVE)
                .build();
    }


    public DataUsersServiceImpl(RegisterProductMapper mapper) {
        this.mapper = mapper;
    }
}
