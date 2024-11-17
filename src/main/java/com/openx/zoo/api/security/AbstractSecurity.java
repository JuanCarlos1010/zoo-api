package com.openx.zoo.api.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.openx.zoo.api.entities.User;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public abstract class AbstractSecurity {

    abstract protected String signToken(User user, List<String> permissions) throws JOSEException;

    abstract protected JWTClaimsSet verifyToken(String token) throws ParseException, JOSEException;

    abstract protected String encryptPassword(String password);

    abstract protected boolean matchPasswords(String rawPassword, String encodedPassword);

    abstract protected boolean validPassword(String rawPassword);

    protected JWTClaimsSet buildClaims(User user, List<String> permissions) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.HOUR_OF_DAY, 24);

        return new JWTClaimsSet.Builder()
                .audience("UTP")
                .issueTime(currentDate)
                .issuer("Openx Stark Inc")
                .expirationTime(calendar.getTime())
                .jwtID(UUID.randomUUID().toString())
                .claim(SecurityEnvs.JWT_PERMISSIONS_NAME, permissions)
                .subject(String.valueOf(user.getId()))
                .build();
    }
}
