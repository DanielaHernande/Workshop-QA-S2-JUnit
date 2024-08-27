package com.riwi.beautySalon.infraestructure.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.riwi.beautySalon.api.dto.request.ServiceReq;
import com.riwi.beautySalon.api.dto.response.ServiceResp;
import com.riwi.beautySalon.domain.entities.Appointment;
import com.riwi.beautySalon.domain.entities.ServiceEntity;
import com.riwi.beautySalon.domain.repositories.ServiceRepository;
import com.riwi.beautySalon.utils.DataProviderClient;

@ExtendWith(MockitoExtension.class)
public class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceService serviceService;
    

    // Crear
    @Test
    public void testCreate() {

        // Given
        List<Appointment> appointments = DataProviderClient.appointmentsEntity();

        ServiceReq serviceReq = new ServiceReq();
        serviceReq.setName("Peluqeria");
        serviceReq.setDescription("Cortar cabello");
        BigDecimal price = new BigDecimal(10.22);
        serviceReq.setPrice(price);

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(1L);
        serviceEntity.setName("Peluqeria");
        serviceEntity.setDescription("Cortar cabello");
        serviceEntity.setPrice(price);
        serviceEntity.setAppointments(appointments);

        when(this.serviceRepository.save(any(ServiceEntity.class))).thenReturn(serviceEntity);

        // When
        ServiceResp result = serviceService.create(serviceReq);

        // Then
        assertNotNull(result);
        assertEquals(serviceEntity.getPrice(), result.getPrice());
        assertEquals("Peluqeria", result.getName());
        assertEquals("Cortar cabello", result.getDescription());
    };
};
