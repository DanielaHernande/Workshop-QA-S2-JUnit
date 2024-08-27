package com.riwi.beautySalon.infraestructure.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.riwi.beautySalon.api.dto.request.ClientReq;
//import com.riwi.beautySalon.api.dto.response.AppointmentBasicResp;
//import com.riwi.beautySalon.api.dto.response.AppointmentResp;
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

    // Obtener por Id
    /*@Test
    public void testGet() {

        // gIVEN
        Long clientId = 1L;

        ClientEntity clientEntity = DataProviderClient.clientById(clientId);

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(clientEntity));


        List<AppointmentResp> appointmentRespList = clientEntity.getAppointments().stream()

            .map(appointment -> {

                AppointmentResp appointmentResp = new AppointmentResp();
                appointmentResp.setId(appointment.getId());
                appointmentResp.setDateTime(appointment.getDateTime());
                appointmentResp.setClient(null);
                appointmentResp.setDuration(appointment.getDuration());
                appointmentResp.setComments(appointment.getComments());
                appointmentResp.setEmployee(null);
                appointmentResp.setService(null);

                return appointmentResp;

            }).collect(Collectors.toList());

        // When
        ClientResp result = clientService.get(clientId); 

        // Theen
        assertNotNull(result);

        System.out.println("Metodo Get de client");
    }*/

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

    // Update
    /*@Test
    public void testUpdate() {

        // Given
        Long clientId = 1L;

        // de entrada 
        ClientReq clientReq = new ClientReq();
        clientReq.setFirstName("Pepe");
        clientReq.setLastName("Perez");
        clientReq.setEmail("pepe@gmail.com");
        clientReq.setPhone("123484844");
        
        // Entidad que se buca parta actualizar
        ClientEntity clientEntityOriginal = DataProviderClient.clientById(clientId);

        // Actualizar entidad
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName(clientReq.getFirstName());
        clientEntity.setLastName(clientReq.getLastName());
        clientEntity.setEmail(clientReq.getEmail());
        clientEntity.setPhone(clientReq.getPhone());
        clientEntity.setAppointments(clientEntityOriginal.getAppointments());

        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(clientEntityOriginal));
        when(this.clientRepository.save(any(ClientEntity.class))).thenReturn(clientEntity);

        // When
        ClientResp response = clientService.update(clientReq, clientId);

        // Then
        assertNotNull(response);
        assertEquals(clientId, response.getId());
        assertEquals(clientId, response.getFirstName());
        assertEquals(clientId, response.getLastName());
        assertEquals(clientId, response.getPhone());
        assertEquals(clientId, response.getEmail());
        assertEquals(clientEntityOriginal.getAppointments(), response.getAppointments());

        System.out.println("Metodo Update Client");
    }*/

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

};
