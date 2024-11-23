package com.openx.zoo.api.security;

import com.nimbusds.jwt.JWTClaimsSet;
import com.openx.zoo.api.entity.User;
import com.openx.zoo.api.exception.BadRequestException;
import com.openx.zoo.api.exception.InternalServerException;
import com.openx.zoo.api.exception.UnauthorizedException;
import com.openx.zoo.api.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends OncePerRequestFilter {
    private final AbstractSecurity abstractSecurity;
    private final UserRepository userRepository;

    public AuthenticationFilter(UserRepository userRepository, AbstractSecurity abstractSecurity) {
        this.userRepository = userRepository;
        this.abstractSecurity = abstractSecurity;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String headerValue = request.getHeader(SecurityEnvs.HTTP_HEADER_TOKEN);
        if (headerValue == null || !headerValue.startsWith(SecurityEnvs.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = headerValue.substring(SecurityEnvs.TOKEN_PREFIX.length());

        try {
            JWTClaimsSet claims = abstractSecurity.verifyToken(token);
            long userId = Long.parseLong(claims.getSubject());
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UnauthorizedException("Unauthorized"));

            List<SimpleGrantedAuthority> authorities = extractRolesFromToken(claims);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getId(), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (UnauthorizedException | BadRequestException | InternalServerException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
        filterChain.doFilter(request, response);
    }

    private List<SimpleGrantedAuthority> extractRolesFromToken(JWTClaimsSet claims) throws ParseException {
        String[] permissions = claims.getStringArrayClaim(SecurityEnvs.JWT_PERMISSIONS_NAME);
        return Arrays.stream(permissions).parallel()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
