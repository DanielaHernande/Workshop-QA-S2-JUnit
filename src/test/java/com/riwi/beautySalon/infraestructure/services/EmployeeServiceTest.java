package com.riwi.beautySalon.infraestructure.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.riwi.beautySalon.api.dto.request.EmployeeReq;
import com.riwi.beautySalon.api.dto.response.EmployeeResp;
import com.riwi.beautySalon.domain.entities.Appointment;
import com.riwi.beautySalon.domain.entities.Employee;
import com.riwi.beautySalon.domain.repositories.EmployeeRepository;
import com.riwi.beautySalon.utils.DataProviderClient;
import com.riwi.beautySalon.utils.enums.SortType;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    // None
    @Test
    public void testGetAllNONE() {

        // Given
        int page = 0;
        int size = 10;
        SortType sortType = SortType.NONE;

        // Create a dummy list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "Pepe", "Perez", "pepe@gmail.com", "123456789", null, null, null));

        // Create a dummy page with the list of employees
        Page<Employee> emplPage = new PageImpl<>(employees);

        // Simulate repository behavior
        when(this.employeeRepository.findAll(PageRequest.of(page, size))).thenReturn(emplPage);

        // When
        // Call to the method under test
        Page<EmployeeResp> result = employeeService.getAll(page, size, sortType);

        // Then
        // Verifications
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(emplPage.getContent().get(0).getFirstName(), result.getContent().get(0).getFirstName());
        assertEquals(emplPage.getContent().get(0).getLastName(), result.getContent().get(0).getLastName());
        assertEquals(emplPage.getContent().get(0).getEmail(), result.getContent().get(0).getEmail());
        assertEquals(emplPage.getContent().get(0).getPhone(), result.getContent().get(0).getPhone());

        System.out.println("Metodo para obtener la lista por deafult");
    };

    // Get
    @Test
    public void testGet() {

        // Given
        Long employeeId = 1L;

        // Create a simulated employee
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Pepe");
        employee.setLastName("Perez");
        employee.setEmail("pepe@gmail.com");
        employee.setAppointments(DataProviderClient.appointmentsEntity());
        employee.setPhone("1234858754");
        employee.setRole(null);
        employee.setUser(null);

        // Simulate repository behavior
        when(this.employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        // When
        // Call to the method under test
        EmployeeResp employeeResp = employeeService.get(employeeId);

        // Them
        // Verificaciones
        assertNotNull(employeeResp);
        assertEquals(employee.getId(), employeeResp.getId());
        assertEquals(employee.getEmail(), employeeResp.getEmail());
        assertEquals(employee.getFirstName(), employeeResp.getFirstName());
        assertEquals(employee.getLastName(), employeeResp.getLastName());
        assertEquals(employee.getPhone(), employeeResp.getPhone());

        System.out.println("Metodo Get del Employee");
    };

    // Crear
    @Test
    public void testCreate() {

        // Given
        // Initial configuration
        List<Appointment> appointments = DataProviderClient.appointmentsEntity();

        // Create a simulated employee request
        EmployeeReq employeeReq = new EmployeeReq();
        employeeReq.setFirstName("Pepe");
        employeeReq.setLastName("Perez");
        employeeReq.setEmail("pepe@gmail.com");
        employeeReq.setPhone("1234858754");
        employeeReq.setRole(null);

        // Create a simulated employee
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Pepe");
        employee.setLastName("Perez");
        employee.setEmail("pepe@gmail.com");
        employee.setAppointments(appointments);
        employee.setPhone("1234858754");
        employee.setRole(null);
        employee.setUser(null);

        // Simulate repository behavior
        when(this.employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // When
        // Call to the method under test
        EmployeeResp result = employeeService.create(employeeReq);

        // Then
        // Verificaciones
        assertNotNull(result);
        assertEquals(employee.getEmail(), result.getEmail());
        assertEquals(employee.getFirstName(), result.getFirstName());
        assertEquals(employee.getLastName(), result.getLastName());

        System.out.println("Metodo create de Employee");
    };

    // Actualizar
    @Test
    public void testUpdate() {

        // Given
        // Initial configuration
        Long employeeId = 1L;

        // Create a simulated employee with old data
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setUser(null);
        employee.setRole(null);
        employee.setPhone("12345866");
        employee.setLastName("Perez");
        employee.setFirstName("Pepe");
        employee.setEmail("pepe@gmail.com");
        employee.setAppointments(DataProviderClient.appointmentsEntity());

        // Create a simulated request with the new data
        EmployeeReq employeeReq = new EmployeeReq();
        employeeReq.setFirstName("Cindy");
        employeeReq.setLastName("Nero");
        employeeReq.setEmail("cindy@gmail.com");
        employeeReq.setPhone("145789862");
        employeeReq.setRole(null);

        // Create an updated simulated employee
        Employee employeeUpdate = new Employee();
        employeeUpdate.setId(employeeId);
        employeeUpdate.setUser(null);
        employeeUpdate.setRole(null);
        employeeUpdate.setPhone("145789862");
        employeeUpdate.setLastName("Nero");
        employeeUpdate.setFirstName("Cindy");
        employeeUpdate.setEmail("cindy@gmail.com");
        employeeUpdate.setAppointments(DataProviderClient.appointmentsEntity());

        // Simulate repository behavior
        when(this.employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(this.employeeRepository.save(any(Employee.class))).thenReturn(employeeUpdate);

        // wHEN
        // Call to the method under test
        EmployeeResp response = employeeService.update(employeeReq, employeeId);

        // Then
        // Verifications
        assertNotNull(response);
        assertEquals(employeeId, response.getId());
        assertEquals(employeeUpdate.getFirstName(), response.getFirstName());
        assertEquals(employeeUpdate.getLastName(), response.getLastName());
        assertEquals(employeeUpdate.getEmail(), response.getEmail());
        assertEquals(employeeUpdate.getPhone(), response.getPhone());

        System.out.println("metodo updated de employe");
    };

    // Eliminar
    @Test
    public void testDelete() {

        // Given
        Long employeeDelete = 1L;

        // Create a simulated employee
        Employee employee = new Employee();
        employee.setId(employeeDelete);

        // Simulate repository behavior
        when(this.employeeRepository.findById(employeeDelete)).thenReturn(Optional.of(employee));

        // When
        // Llamada al método bajo prueba
        employeeService.delete(employeeDelete);

        // Then
         // Verifications
        verify(employeeRepository).delete(employee);

        System.out.println("Metodo delete de employee");
    };
};