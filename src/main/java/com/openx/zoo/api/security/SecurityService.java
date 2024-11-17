package com.openx.zoo.api.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.openx.zoo.api.entities.User;
import com.openx.zoo.api.exceptions.UnauthorizedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class SecurityService extends AbstractSecurity {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public SecurityService(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    protected String signToken(User user, List<String> permissions) throws JOSEException {
        JWSSigner signer = new RSASSASigner(privateKey);

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.RS256),
                buildClaims(user, permissions));

        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    @Override
    protected JWTClaimsSet verifyToken(String token) throws ParseException, JOSEException {
        SignedJWT signedToken = SignedJWT.parse(token);
        JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);

        if(!signedToken.verify(verifier))
            throw new UnauthorizedException("Invalid token");

        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        JWTClaimsSet claimsSet = signedToken.getJWTClaimsSet();
        if(claimsSet.getExpirationTime().before(date))
            throw new UnauthorizedException("Token has expired");

        return claimsSet;
    }

    @Override
    protected String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    protected boolean matchPasswords(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    @Override
    protected boolean validPassword(String rawPassword) {
        return Pattern.compile(SecurityEnvs.PASSWORD_PATTERN)
                .matcher(rawPassword)
                .matches();
    }
}
