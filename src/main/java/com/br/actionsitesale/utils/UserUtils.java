package com.br.actionsitesale.utils;

import java.util.Base64;

public class UserUtils {

    public UserUtils(){}

    public static String criptografarBase64(String valor){
        return String.valueOf(Base64.getEncoder().encodeToString(valor.getBytes()));
    }

    public static String descriptografarBase64(String valor){
        return new String(Base64.getDecoder().decode(valor.getBytes()));
    }
}
