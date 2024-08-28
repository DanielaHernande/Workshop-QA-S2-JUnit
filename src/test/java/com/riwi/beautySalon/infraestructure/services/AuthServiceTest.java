package com.riwi.beautySalon.infraestructure.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.riwi.beautySalon.api.dto.request.LoginReq;
import com.riwi.beautySalon.api.dto.response.AuthResp;
import com.riwi.beautySalon.domain.entities.User;
import com.riwi.beautySalon.domain.repositories.UserRepository;
import com.riwi.beautySalon.infraestructure.helpers.JwtService;

// Extend the test with MockitoExtension to enable Mockito features.
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    // Mocks to simulate the dependencies of the AuthService class.
    @Mock
    private AuthenticationManager authenticationManager; 

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    // InjectMocks for Mockito to inject the mocks in AuthService.
    @InjectMocks
    private AuthService authService;
    
    // Login
    @Test
    public void testLogin() {

        // Given
        LoginReq loginReq = new LoginReq();
        loginReq.setUserName("Casimiro");
        loginReq.setPassword("Arriba");

        // We create a simulated user.
        User user = new User();
        user.setUserName("Casimiro");

        // We simulate authentication with any authentication token.
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);

        // We simulate searching for the user by user name.
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));

        // We simulate the generation of a JWT token for the authenticated user.
        when(jwtService.getToken(user)).thenReturn("dummy-jwt-yoken");

        // When
        // The login method is called with the login request.
        AuthResp result = authService.login(loginReq);

        // Then
        // Verify that the answer is not null.
        assertNotNull(result);
        // Verifica que el mensaje sea el esperado.
        assertEquals("Autenticado correctamente", result.getMessage());
        // Verifica que el token sea el esperado.
        assertEquals("dummy-jwt-yoken", result.getToken());
    };
};