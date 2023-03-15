package com.br.actionsitesale.service.impl;

import com.br.actionsitesale.model.Token;
import com.br.actionsitesale.service.CriptografiaService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Slf4j
@Service
public class CriptografiaServiceImpl implements CriptografiaService {

    @NonNull
    @Value("${secret}")
    private String secret;
    private static SecretKey secretKey =  new SecretKeySpec("1234567890123456".getBytes(Charset.defaultCharset()), "AES");
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    public String encrypt(String data) {
        try {
            var cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().encodeToString(cipherText);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Error on encrypt token:  " + e.getMessage(), e);
        }
    }

    public String decrypt(String encrypted) {
        try {
            var cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] plainText = cipher.doFinal(Base64.getUrlDecoder().decode(encrypted));
            return new String(plainText, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                 | BadPaddingException | IllegalArgumentException e) {
            throw new RuntimeException("Error on decrypt token:  " + e.getMessage(), e);
        }
    }

    public String encrypt(Token data) {
        try {
            String token = (
                    data.getUserName() + ":"
                    + data.getUserName() + ":"
                    + data.getPassword() + ":"
                    + data.getNumberCorp() + ":"
                    + data.getEmailCorp() + ":");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secret.getBytes(Charset.defaultCharset()), "AES"));
            byte[] cipherText = cipher.doFinal(token.getBytes(StandardCharsets.UTF_8));

            return Base64.getUrlEncoder().encodeToString(cipherText);
//            return Hex.encodeHexString(cipherText);

        } catch (Exception e) {
            log.error("ERROR: " + e);
            throw new RuntimeException(e);
        }
    }

}
