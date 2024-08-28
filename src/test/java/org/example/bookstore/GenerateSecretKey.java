package org.example.bookstore;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Base64;
@Slf4j
public class GenerateSecretKey {
    @Test
    public void generateKey(){
        SecretKey key= Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String string = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println(string);

    }
}
