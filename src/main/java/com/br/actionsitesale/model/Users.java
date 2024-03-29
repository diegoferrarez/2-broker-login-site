package com.br.actionsitesale.model;

import com.br.actionsitesale.model.enums.StatusLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usersCorp")
public class Users {

    @Id
    private String id;
    private String unit;
    private String serialNumberUnit;
    private Token credential;
    private StatusLogin statusCorp;

}