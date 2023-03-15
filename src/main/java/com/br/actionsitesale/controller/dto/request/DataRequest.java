package com.br.actionsitesale.controller.dto.request;

import com.br.actionsitesale.model.Token;
import com.br.actionsitesale.model.enums.StatusLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataRequest {

    @Id
    private String id;
    private String unit;
    private String serialNumberUnit;
    private Token credential;
    private StatusLogin statusCorp;

}