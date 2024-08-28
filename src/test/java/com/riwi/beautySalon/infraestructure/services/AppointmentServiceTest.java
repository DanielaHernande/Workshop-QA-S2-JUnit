package com.riwi.beautySalon.infraestructure.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.riwi.beautySalon.api.dto.request.AppointmentReq;
import com.riwi.beautySalon.api.dto.response.AppointmentResp;
import com.riwi.beautySalon.api.dto.response.ClientBasicResp;
import com.riwi.beautySalon.domain.entities.Appointment;
import com.riwi.beautySalon.domain.entities.ClientEntity;
import com.riwi.beautySalon.domain.entities.Employee;
import com.riwi.beautySalon.domain.entities.ServiceEntity;
import com.riwi.beautySalon.domain.repositories.AppointmentRepository;
import com.riwi.beautySalon.utils.DataProviderAppointment;
import com.riwi.beautySalon.domain.repositories.ClientRepository;
import com.riwi.beautySalon.domain.repositories.EmployeeRepository;
import com.riwi.beautySalon.domain.repositories.ServiceRepository;
import com.riwi.beautySalon.infraestructure.helpers.EmailHelper;
import com.riwi.beautySalon.utils.enums.SortType;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository; 

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private EmailHelper emailHelper;

    @InjectMocks
    private  AppointmentService appointmentService;
    
    // FindAll
    @Test
    public void testGetAll() {

        // Given
        int page = 0; // Home page
        int size = 10; // Page size
        SortType sortType = SortType.NONE; // Type of order

        // Paging configuration
        Pageable pageable = PageRequest.of(page, size);
        // Simulated data
        List<Appointment> appointments = DataProviderAppointment.sampleQuotesMock();

        // Simulated dating page
        Page<Appointment> appointmentPage = new PageImpl<>(appointments, pageable, appointments.size());

        // We simulate that the repository returns the page of quotations
        when(appointmentRepository.findAll(any(Pageable.class))).thenReturn(appointmentPage);

        // When
        // We execute the method to test
        Page<AppointmentResp> result = appointmentService.getAll(page, size, sortType);

        // Then
        // Verify that the number of appointments matches the number of appointments.
        assertEquals(appointments.size(), result.getTotalElements());
        // Validamos que no sea nulo
        assertNotNull(result);
    };

    // Create
    @Test
    public void testCreate() {

        // Given
        Long clientId = 1L; // Customer ID
        Long employeeId = 2L; // ID del empleado
        Long serviceId = 1L; // Service ID

        // A request object is created with the necessary data.
        AppointmentReq request = new AppointmentReq();
        request.setClientId(clientId);
        request.setEmployeeId(employeeId);
        request.setServiceId(serviceId);
        request.setDateTime(LocalDateTime.now());

        // Objetos para simular una entodad
        ClientEntity clien = DataProviderAppointment.clientById(clientId);
        Employee employee = DataProviderAppointment.employeeById(employeeId);
        ServiceEntity serviceEntity = DataProviderAppointment.serviceEntityById(serviceId);

        // Simulate the appointment to be saved
        Appointment appointmentSave = new Appointment();
        appointmentSave.setId(1L);
        appointmentSave.setComments("Muy bien");
        appointmentSave.setClient(clien);
        appointmentSave.setEmployee(employee);
        appointmentSave.setService(serviceEntity);
        appointmentSave.setDateTime(request.getDateTime());
        appointmentSave.setDuration(request.getDuration());

        // When
        // We simulate that the repositories return the correct data
        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(clien));
        when(this.employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(this.serviceRepository.findById(serviceId)).thenReturn(Optional.of(serviceEntity));
        when(this.appointmentRepository.save(any(Appointment.class))).thenReturn(appointmentSave);

        // We simulate the sending of an email
        doNothing().when(emailHelper).sendEmail(anyString(), anyString(), anyString(), any(LocalDateTime.class));

        // The method to be tested is executed
        AppointmentResp response = appointmentService.create(request);

        // Then
        // Verifica que no sea nulo
        assertNotNull(response);
        // Verifica que tenga los datos correctos
        verify(clientRepository).findById(clientId);
        verify(employeeRepository).findById(employeeId);
        verify(serviceRepository).findById(serviceId);
    };

    // Get
    @Test
    public void testGet() {

        // Given
        Long appointmentId = 2L; // Appointment ID

        // Simulate an appointment with the given ID
        Appointment appointment = DataProviderAppointment.appointmentById(appointmentId);

        // When
        // We simulate that the repository returns the correct citation
        when(this.appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

        // We execute the method to test
        AppointmentResp result = appointmentService.get(appointmentId);

        // Cliente basico
        ClientBasicResp clientResp = new ClientBasicResp();
        clientResp.setId(appointment.getClient().getId());
        clientResp.setFirstName(appointment.getClient().getFirstName());
        clientResp.setEmail(appointment.getClient().getEmail());
        clientResp.setLastName(appointment.getClient().getLastName());
        clientResp.setPhone(appointment.getClient().getPhone());

        // Then
        assertNotNull(result);
        assertEquals(appointmentId, result.getId());

        assertEquals(clientResp.getId(), result.getClient().getId());
        assertEquals(clientResp.getPhone(), result.getClient().getPhone());
        assertEquals(clientResp.getEmail(), result.getClient().getEmail());
        assertEquals(clientResp.getLastName(), result.getClient().getLastName());
        assertEquals(clientResp.getFirstName(), result.getClient().getFirstName());

        assertEquals(appointment.getEmployee().getId(), result.getEmployee().getId());
        assertEquals(appointment.getEmployee().getEmail(), result.getEmployee().getEmail());
        assertEquals(appointment.getEmployee().getPhone(), result.getEmployee().getPhone());
        assertEquals(appointment.getEmployee().getLastName(), result.getEmployee().getLastName());
        assertEquals(appointment.getEmployee().getFirstName(), result.getEmployee().getFirstName());

        assertEquals(appointment.getService().getId(), result.getService().getId());
        assertEquals(appointment.getService().getName(), result.getService().getName());
        assertEquals(appointment.getService().getPrice(), result.getService().getPrice());
        assertEquals(appointment.getService().getDescription(), result.getService().getDescription());

        verify(appointmentRepository).findById(appointmentId);

        System.out.println("Hola");
    };

    // Actualizar
    @Test
    public void testUpdate() {
        
        // Given
        Long clientId = 1L;  // Customer ID
        Long appointmentId = 1L; // appointment ID
        Long employeeId = 2L; // employee ID
        Long serviceId = 1L; // Service ID

        // A request object is created with the necessary data for updating
        AppointmentReq request = new AppointmentReq();
        request.setClientId(clientId);
        request.setEmployeeId(employeeId);
        request.setServiceId(serviceId);
        request.setDateTime(LocalDateTime.now());

        // Objetos para simular una entodad
        ClientEntity clien = DataProviderAppointment.clientById(clientId);
        Employee employee = DataProviderAppointment.employeeById(employeeId);
        ServiceEntity serviceEntity = DataProviderAppointment.serviceEntityById(serviceId);

        // We simulate the existing quote and the updated quote
        Appointment appointmentSave = new Appointment();
        appointmentSave.setId(1L);
        appointmentSave.setComments("Muy bien");
        appointmentSave.setClient(clien);
        appointmentSave.setEmployee(employee);
        appointmentSave.setService(serviceEntity);
        appointmentSave.setDateTime(request.getDateTime());
        appointmentSave.setDuration(request.getDuration());

        // Objeto Actualizado
        Appointment appointmentUpdate = new Appointment();
        appointmentUpdate.setId(1L);
        appointmentUpdate.setComments("Actualizado");
        appointmentUpdate.setClient(clien);
        appointmentUpdate.setEmployee(employee);
        appointmentUpdate.setService(serviceEntity);
        appointmentUpdate.setDateTime(request.getDateTime());
        appointmentUpdate.setDuration(request.getDuration());

        // When
        // Simulate that the repositories return the correct data
        when(this.clientRepository.findById(clientId)).thenReturn(Optional.of(clien));
        when(this.employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(this.serviceRepository.findById(serviceId)).thenReturn(Optional.of(serviceEntity));
        when(this.appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointmentSave));
        when(this.appointmentRepository.save(any(Appointment.class))).thenReturn(appointmentUpdate);

        // We execute the method to test
        AppointmentResp response = appointmentService.update(request, appointmentId);

        // Then
        // Verifica que no sea nulo
        assertNotNull(response);
        // Verifica que tenga los datos correctos
        verify(clientRepository).findById(clientId);
        verify(employeeRepository).findById(employeeId);
        verify(serviceRepository).findById(serviceId);
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    };

    // Eliminar
    @Test
    public void testDelete() {

        // Given
        Long appointmentDeleteId = 1L; // Id of the appointment to be deleted

        // We create a new appointment
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDeleteId);

        // Simulate that the repositories return the correct data
        when(appointmentRepository.findById(appointmentDeleteId)).thenReturn(Optional.of(appointment));

        // When
        // We execute the method to test
        appointmentService.delete(appointmentDeleteId);

        // Then
        // we verify that it was deleted correctly
        verify(appointmentRepository).delete(appointment);
    };
};