package com.riwi.beautySalon.infraestructure.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.riwi.beautySalon.api.dto.response.AppointmentResp;
import com.riwi.beautySalon.domain.entities.Appointment;
import com.riwi.beautySalon.domain.repositories.AppointmentRepository;
import com.riwi.beautySalon.utils.DataProviderAppointment;
//import com.riwi.beautySalon.domain.repositories.ClientRepository;
//import com.riwi.beautySalon.domain.repositories.EmployeeRepository;
//import com.riwi.beautySalon.domain.repositories.ServiceRepository;
import com.riwi.beautySalon.utils.enums.SortType;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository; 
    //private EmployeeRepository employeeRepository;
    //private ClientRepository clientRepository;
    //private ServiceRepository serviceRepository;

    @InjectMocks
    private  AppointmentService appointmentService;
    
    @Test
    public void testGetAll() {

        // Given
        int page = 0;
        int size = 10;
        SortType sortType = SortType.NONE;

        Pageable pageable = PageRequest.of(page, size);
        List<Appointment> appointments = DataProviderAppointment.sampleQuotesMock();

        Page<Appointment> appointmentPage = new PageImpl<>(appointments, pageable, appointments.size());

        when(appointmentRepository.findAll(any(Pageable.class))).thenReturn(appointmentPage);

        // When
        Page<AppointmentResp> result = appointmentService.getAll(page, size, sortType);

        // Then
        assertEquals(appointments.size(), result.getTotalElements());
    }
}