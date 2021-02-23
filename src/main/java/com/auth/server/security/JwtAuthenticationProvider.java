package com.auth.server.security;

import com.auth.server.exception.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private final JwtService jwtService;
    
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			Jws<Claims> claim = jwtService.verify((String) authentication.getCredentials());
			String currentUser = claim.getBody().getSubject();
			String role = claim.getBody().get("role",String.class);
			
            JwtAuthenticatedProfile profile = new JwtAuthenticatedProfile(currentUser);
            profile.setUserRole(role);
            return profile;
        } catch (Exception e) {
            throw new JwtAuthenticationException("Failed to verify token",e);
        }
	}

	public boolean supports(Class<?> authentication) {
		return JwtAuthToken.class.equals(authentication);
	}

}
