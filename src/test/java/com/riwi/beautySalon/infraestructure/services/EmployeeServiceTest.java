package com.riwi.beautySalon.infraestructure.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.riwi.beautySalon.api.dto.request.EmployeeReq;
import com.riwi.beautySalon.api.dto.response.EmployeeResp;
import com.riwi.beautySalon.domain.entities.Appointment;
import com.riwi.beautySalon.domain.entities.Employee;
import com.riwi.beautySalon.domain.repositories.EmployeeRepository;
import com.riwi.beautySalon.utils.DataProviderClient;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testCreate() {

        // Given
        List<Appointment> appointments = DataProviderClient.appointmentsEntity();

        EmployeeReq employeeReq = new EmployeeReq();
        employeeReq.setFirstName("Pepe");
        employeeReq.setLastName("Perez");
        employeeReq.setEmail("pepe@gmail.com");
        employeeReq.setPhone("1234858754");
        employeeReq.setRole(null);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Pepe");
        employee.setLastName("Perez");
        employee.setEmail("pepe@gmail.com");
        employee.setAppointments(appointments);
        employee.setPhone("1234858754");
        employee.setRole(null);
        employee.setUser(null);

        when(this.employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // When
        EmployeeResp result = employeeService.create(employeeReq);

        // Then
        assertNotNull(result);
    };

    
}
