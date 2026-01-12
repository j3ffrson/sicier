package org.fesc.sicier.Filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.fesc.sicier.utils.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenValidator extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AuthEntryPoint authEntryPoint;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);

                DecodedJWT decodedJWT = jwtUtil.veriffyToken(token);
                String username = jwtUtil.getUsernameFromToken(decodedJWT);
                String authorities = jwtUtil.getSpecificClaim(decodedJWT, "permisos").asString();

                Authentication auth = new UsernamePasswordAuthenticationToken(
                        username, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
                );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            filterChain.doFilter(request, response);

        } catch (JWTVerificationException e) {
            ErrorsDto dto = new ErrorsDto(401,"Unauthorized", "Invalid or expired JWT token");
            request.setAttribute("error_dto", dto);

            authEntryPoint.commence(request, response,
                    new AuthenticationServiceException("Invalid JWT token", e));
        }
    }
}
