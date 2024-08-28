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

    // Mock of the repository to simulate access to the database
    @Mock
    private ServiceRepository serviceRepository;

    // Injects the mock from the repository into the service
    @InjectMocks
    private ServiceService serviceService;

    // Get
    @Test
    public void testGet() {

        // Given
        // configure the ID of the service to be obtained.
        Long serviceId = 1L;

        // Create a service entity with fictitious data
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(serviceId);
        serviceEntity.setName("Peluqeria");
        serviceEntity.setDescription("Cortar cabello");
        serviceEntity.setPrice(new BigDecimal("20.000"));
        serviceEntity.setAppointments(DataProviderClient.appointmentsEntity());

        // We configure the behavior of the repository to return this entity when asked for the ID
        when(this.serviceRepository.findById(serviceId)).thenReturn(Optional.of(serviceEntity));

        // When
        // The service is called to get the service by ID
        ServiceResp result = serviceService.get(serviceId);

        // Then
        // we verify that the result is not null and that the data returned match the original entity
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
        // we set up the service request with fictitious data
        List<Appointment> appointments = DataProviderClient.appointmentsEntity();

        ServiceReq serviceReq = new ServiceReq();
        serviceReq.setName("Peluqeria");
        serviceReq.setDescription("Cortar cabello");
        BigDecimal price = new BigDecimal(10.22);
        serviceReq.setPrice(price);

        // Create a service entity that simulates the saved entity
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(1L);
        serviceEntity.setName("Peluqeria");
        serviceEntity.setDescription("Cortar cabello");
        serviceEntity.setPrice(price);
        serviceEntity.setAppointments(appointments);

        // We configure the behavior of the repository to return the saved entity when `save` is called.
        when(this.serviceRepository.save(any(ServiceEntity.class))).thenReturn(serviceEntity);

        // When
        // the service is called to create the new service
        ServiceResp result = serviceService.create(serviceReq);

        // Then
        // we verify that the result is not null and that the data returned match those of the original request
        assertNotNull(result);
        assertEquals(serviceEntity.getPrice(), result.getPrice());
        assertEquals("Peluqeria", result.getName());
        assertEquals("Cortar cabello", result.getDescription());

        System.out.println("Metodo crear de services");
    };

    // Actualizar
    @Test
    public void testUpdate() {

        // Given
        // configure the ID of the service to be updated
        Long serviceId = 1L;

        // Create a service entity with the original data
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
        // the service is called to update the service
        ServiceResp result = serviceService.update(serviceReq, serviceId);

        // Then
        // we verify that the result is not null and that the data returned match those of the updated entity
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
        //  set the ID of the service to be deleted
        Long serviceDelete = 1L;

        // Create a service entity with the ID of the service to be deleted
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(serviceDelete);

        // We configure the behavior of the repository to return the entity when the ID is requested.
        when(this.serviceRepository.findById(serviceDelete)).thenReturn(Optional.of(serviceEntity));

        // When
        // the service is called to delete the service
        serviceService.delete(serviceDelete);

        // Then
        // we verify that the repository deleted the corresponding entity
        verify(serviceRepository).delete(serviceEntity);

        System.out.println("Metodo delete de services");
    };
};