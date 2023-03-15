package com.br.actionsitesale.controller.dto.response;

import com.br.actionsitesale.model.Token;
import com.br.actionsitesale.model.Users;
import com.br.actionsitesale.model.enums.StatusLogin;
import com.br.actionsitesale.utils.UserUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {

    @Id
    private String id;
    private String unit;
    private String serialNumberUnit;
    private Token credential;
    private StatusLogin statusCorp;

    public static DataResponse converter(Users c){
        return DataResponse.builder()
                .id(c.getId())
                .unit(c.getUnit())
                .serialNumberUnit(c.getSerialNumberUnit())
                .credential(c.getCredential())
                .statusCorp(c.getStatusCorp())
                .build();
    }
}