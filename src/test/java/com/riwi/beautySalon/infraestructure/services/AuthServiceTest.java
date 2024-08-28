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

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager; 

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;
    
    // Login
    @Test
    public void testLogin() {

        // Given
        LoginReq loginReq = new LoginReq();
        loginReq.setUserName("Casimiro");
        loginReq.setPassword("Arriba");

        User user = new User();
        user.setUserName("Casimiro");
        //user.setPassword("Arriba");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);

        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));

        when(jwtService.getToken(user)).thenReturn("dummy-jwt-yoken");

        // When
        AuthResp result = authService.login(loginReq);

        // Then
        assertNotNull(result);
        assertEquals("Autenticado correctamente", result.getMessage());
        assertEquals("dummy-jwt-yoken", result.getToken());
    };


    // Hola como estas, me puedes ayudar a realizar test unitarios en Java con JUnit y Mockito: 
    // este es el servicio:
};
