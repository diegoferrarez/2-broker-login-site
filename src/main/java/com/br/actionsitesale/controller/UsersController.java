package com.br.actionsitesale.controller;

import com.br.actionsitesale.controller.dto.DataRequest;
import com.br.actionsitesale.controller.dto.DataResponse;
import com.br.actionsitesale.service.DataUsersService;
import com.br.actionsitesale.utils.UserConstants;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UsersController {

    @Autowired
    private DataUsersService dataUsersService;

    @ApiOperation(value = "Busca todos os usuários cadastrados")
    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public List<DataResponse> getall(){
        return dataUsersService.findAll();
    }

    @ApiOperation(value = "Busca um usuário pelo nome")
    @GetMapping("/findby/name")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<DataResponse> getByName(@RequestHeader(name = UserConstants.USER_SIGN_HEADER)String userLogin,
                                            @RequestHeader(name = UserConstants.USER_PASS_HEADER)String password){
        return dataUsersService.findByName(userLogin, password);
    }

    @ApiOperation(value = "Busca um usuário por id")
    @GetMapping("/findby/id/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<DataResponse> getById(@PathVariable String id){
        return dataUsersService.findById(id);
    }

    @ApiOperation(value="Registra os usuários da corporação")
    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public DataResponse create(@RequestBody DataRequest request){
        return dataUsersService.createUsers(request);
    }

    @ApiOperation(value = "Altera a informação de um usuário")
    @PatchMapping("/layoff/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<DataRequest>> inactivate(@PathVariable String id){
        return dataUsersService.InactivateUser(id);
    }
}
