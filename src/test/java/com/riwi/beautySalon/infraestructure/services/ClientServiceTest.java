package com.riwi.beautySalon.infraestructure.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.riwi.beautySalon.api.dto.request.ClientReq;
import com.riwi.beautySalon.api.dto.response.ClientResp;
import com.riwi.beautySalon.domain.entities.Appointment;
import com.riwi.beautySalon.domain.entities.ClientEntity;
import com.riwi.beautySalon.domain.repositories.AppointmentRepository;
import com.riwi.beautySalon.domain.repositories.ClientRepository;
import com.riwi.beautySalon.utils.DataProviderClient;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private ClientService clientService;

    // get
    @Test
    public void testGet() {

        // Given
        Long clientId = 1L;

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setFirstName("Pepe");
        clientEntity.setLastName("Perez");
        clientEntity.setEmail("pepe@gmail.com");
        clientEntity.setPhone("123484844");
        clientEntity.setAppointments(DataProviderClient.appointmentsEntity());

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(clientEntity));

        // When
        ClientResp result = clientService.get(clientId);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Pepe", result.getFirstName());
        assertEquals(clientEntity.getPhone(), result.getPhone());
        assertEquals(clientEntity.getEmail(), result.getEmail());

        System.out.println("Metodo get Client");
    };

    // Create
    @Test
    public void testCreate() {

        // Given
        List<Appointment> appointments = DataProviderClient.appointmentsEntity();

        ClientReq clientReq = new ClientReq();
        clientReq.setFirstName("Pepe");
        clientReq.setLastName("Perez");
        clientReq.setEmail("pepe@gmail.com");
        clientReq.setPhone("123484844");

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setFirstName("Pepe");
        clientEntity.setLastName("Perez");
        clientEntity.setEmail("pepe@gmail.com");
        clientEntity.setPhone("123484844");
        clientEntity.setAppointments(appointments);

        when(this.clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

        // When
        ClientResp response = clientService.create(clientReq);

        // Then
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Pepe", response.getFirstName());
        assertEquals("Perez", response.getLastName());
        assertEquals("123484844", response.getPhone());
        assertEquals("pepe@gmail.com", response.getEmail());
        assertEquals(clientEntity.getAppointments().size(), response.getAppointments().size());

        System.out.println("Metodo Crear Client");
    };

    // Eliminar 
    @Test
    public void testDelete() {

        // Given
        Long clientDelete = 1L;

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(clientDelete);

        when(this.clientRepository.findById(clientDelete)).thenReturn(Optional.of(clientEntity));

        // When
        clientService.delete(clientDelete);

        // Then
        verify(clientRepository).delete(clientEntity);
    };

    // Actualizar
    @Test
    public void testUpdate() {
        
        // Given
        Long clientId = 1L;

        ClientEntity existingClient = new ClientEntity();
        existingClient.setId(clientId);
        existingClient.setFirstName("Juan");
        existingClient.setLastName("Gomez");
        existingClient.setEmail("juan@gmail.com");
        existingClient.setPhone("987654321");
        existingClient.setAppointments(DataProviderClient.appointmentsEntity());

        ClientReq clientReq = new ClientReq();
        clientReq.setFirstName("Pepe");
        clientReq.setLastName("Perez");
        clientReq.setEmail("pepe@gmail.com");
        clientReq.setPhone("123484844");

        ClientEntity updatedClientEntity = new ClientEntity();
        updatedClientEntity.setId(clientId);
        updatedClientEntity.setFirstName("Pepe");
        updatedClientEntity.setLastName("Perez");
        updatedClientEntity.setEmail("pepe@gmail.com");
        updatedClientEntity.setPhone("123484844");
        updatedClientEntity.setAppointments(existingClient.getAppointments());

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(this.clientRepository.save(any(ClientEntity.class))).thenReturn(updatedClientEntity);

        // When
        ClientResp response = clientService.update(clientReq, clientId);

        // Then
        assertNotNull(response);
        assertEquals(clientId, response.getId());
        assertEquals("Pepe", response.getFirstName());
        assertEquals("Perez", response.getLastName());
        assertEquals("123484844", response.getPhone());
        assertEquals("pepe@gmail.com", response.getEmail());
        assertEquals(existingClient.getAppointments().size(), response.getAppointments().size());

        verify(clientRepository).save(any(ClientEntity.class));
        verify(clientRepository).findById(clientId);
    };

};
