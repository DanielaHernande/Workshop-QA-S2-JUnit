package com.riwi.beautySalon.infraestructure.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    // Get
    @Test
    public void testGet() {

        // Given
        Long serviceId = 1L;

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(serviceId);
        serviceEntity.setName("Peluqeria");
        serviceEntity.setDescription("Cortar cabello");
        serviceEntity.setPrice(new BigDecimal("20.000"));
        serviceEntity.setAppointments(DataProviderClient.appointmentsEntity());

        when(this.serviceRepository.findById(serviceId)).thenReturn(Optional.of(serviceEntity));

        // When
        ServiceResp result = serviceService.get(serviceId);

        // Then
        assertNotNull(result);
        assertEquals(serviceEntity.getId(), result.getId());
        assertEquals(serviceEntity.getName(), result.getName());
        assertEquals(serviceEntity.getDescription(), result.getDescription());
        assertEquals(serviceEntity.getDescription(), result.getDescription());

        System.out.println("Metodo get services");
    }
        
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

    // Actualizar
    @Test
    public void testIpdate() {

        // Given
        Long serviceId = 1L;

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(serviceId);
        serviceEntity.setName("Peluqeria");
        serviceEntity.setDescription("Cortar cabello");
        serviceEntity.setPrice(new BigDecimal("20.000"));
        serviceEntity.setAppointments(DataProviderClient.appointmentsEntity());

        // Datos actualizacion
        ServiceReq serviceReq = new ServiceReq();
        serviceReq.setName("Corte");
        serviceReq.setPrice(new BigDecimal("20.000"));
        serviceReq.setDescription("Corte y peinado");

        // Entidad actualizada
        ServiceEntity serviceEntityUpdate = new ServiceEntity();
        serviceEntityUpdate.setId(serviceId);
        serviceEntityUpdate.setName("Corte");
        serviceEntityUpdate.setDescription("Corte y peinado");
        serviceEntityUpdate.setPrice(new BigDecimal("30.000"));
        serviceEntityUpdate.setAppointments(serviceEntity.getAppointments());

        // Configurar comportamiento del repositorio
        when(this.serviceRepository.findById(serviceId)).thenReturn(Optional.of(serviceEntity));
        when(this.serviceRepository.save(any(ServiceEntity.class))).thenReturn(serviceEntityUpdate);

        // When
        ServiceResp result = serviceService.update(serviceReq, serviceId);

        // Then
        assertNotNull(result);
        assertEquals(serviceEntityUpdate.getId(), result.getId());
        assertEquals(serviceEntityUpdate.getName(), result.getName());
        assertEquals(serviceEntityUpdate.getDescription(), result.getDescription());
        assertEquals(serviceEntityUpdate.getDescription(), result.getDescription());

        verify(serviceRepository).save(any(ServiceEntity.class));

        System.out.println("Metodo updatye de services");
    };

    // Delete
    @Test
    public void testDelete() {

        // Given
        Long serviceDelete = 1L;

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(serviceDelete);

        when(this.serviceRepository.findById(serviceDelete)).thenReturn(Optional.of(serviceEntity));

        // When
        serviceService.delete(serviceDelete);

        // Then
        verify(serviceRepository).delete(serviceEntity);
    };
};
