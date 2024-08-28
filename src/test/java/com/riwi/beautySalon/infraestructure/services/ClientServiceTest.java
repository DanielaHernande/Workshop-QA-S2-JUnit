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

// Extend the test with MockitoExtension to enable Mockito features.
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    
    // Mocks to simulate the dependencies of the ClientService class.
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    // InjectMocks for Mockito to inject the mocks in ClientService.
    @InjectMocks
    private ClientService clientService;

    // get
    @Test
    public void testGet() {

        // Given
        // The necessary data for the test are prepared.
        Long clientId = 1L;

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setFirstName("Pepe");
        clientEntity.setLastName("Perez");
        clientEntity.setEmail("pepe@gmail.com");
        clientEntity.setPhone("123484844");
        clientEntity.setAppointments(DataProviderClient.appointmentsEntity());

        // We simulate the search for the client by ID.
        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(clientEntity));

        // When
        // The get method is called with the customer ID.
        ClientResp result = clientService.get(clientId);

        // Then
        // Results are verified
        assertNotNull(result);
        // Verify that the ID is as expected.
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
        // The necessary data for the test are prepared.

        // Citas simuladas
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

        // We simulate the saving of the client in the repository.
        when(this.clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

        // When
        // The create method is called with the customer data.
        ClientResp response = clientService.create(clientReq);

        // Then
        //  The results are verified.
        // Verify that the answer is not null.
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
        // The necessary data for the test are prepared.

        Long clientDelete = 1L;

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(clientDelete);

        // We simulate the search for the client by ID.
        when(this.clientRepository.findById(clientDelete)).thenReturn(Optional.of(clientEntity));

        // When
        // The delete method is called with the client ID.
        clientService.delete(clientDelete);

        // Then
        // It is verified that the delete method of the repository has been called.
        verify(clientRepository).delete(clientEntity);
    };

    // Actualizar
    @Test
    public void testUpdate() {
        
        // Given
        // The necessary data for the test are prepared.
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
        // Keeps the same appointments
        updatedClientEntity.setAppointments(existingClient.getAppointments());

        // We simulate the search of the client by ID and the update in the repository.
        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(this.clientRepository.save(any(ClientEntity.class))).thenReturn(updatedClientEntity);

        // When
        //  The update method is called with the updated customer data.
        ClientResp response = clientService.update(clientReq, clientId);

        // Then
        // Results are verified
        assertNotNull(response);
        assertEquals(clientId, response.getId());
        assertEquals("Pepe", response.getFirstName());
        assertEquals("Perez", response.getLastName());
        assertEquals("123484844", response.getPhone());
        assertEquals("pepe@gmail.com", response.getEmail());
        assertEquals(existingClient.getAppointments().size(), response.getAppointments().size());

        // Verify that the save and findById methods of the repository have been called.
        verify(clientRepository).save(any(ClientEntity.class));
        verify(clientRepository).findById(clientId);

        System.out.println("Metodo update client");
    };
};