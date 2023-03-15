package com.br.actionsitesale.controller.dto.response;

import com.br.actionsitesale.model.Token;
import com.br.actionsitesale.service.CriptografiaService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    private String token;

    public static TokenResponse converter(DataResponse response, CriptografiaService service){
        return TokenResponse.builder().token(service.encrypt(formatToken(response))).build();
    }

    private static Token formatToken(DataResponse a){
        return new Token(a.getCredential().getUserName(),
                a.getCredential().getPassword(),
                a.getCredential().getNumberCorp(),
                a.getCredential().getEmailCorp());
    }
}
