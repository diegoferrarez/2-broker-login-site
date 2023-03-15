package com.br.actionsitesale.service.mapper;

import com.br.actionsitesale.controller.dto.response.DataResponse;
import com.br.actionsitesale.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterProductMapper {

    private final ObjectMapper mapper;

    @SneakyThrows
    public DataResponse toResponse(Users users) {
        var json = this.mapper.writeValueAsString(users);
        return this.mapper.readValue(json, DataResponse.class);
    }
}
