package com.br.actionsitesale.service;

import com.br.actionsitesale.model.Token;

public interface CriptografiaService {

    String encrypt(String data);

    String encrypt(Token data);

    String decrypt(String token);

}
