package com.br.actionsitesale.controller.dto;

import com.br.actionsitesale.model.enums.StatusLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataRequest {

    @Id
    private String id;
    private String login;
    private String password;
    private String numberCorp;
    private String emailCorp;
    private StatusLogin statusCorp;

}