package com.riwi.beautySalon.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.riwi.beautySalon.domain.entities.Appointment;
import com.riwi.beautySalon.domain.entities.ClientEntity;
import com.riwi.beautySalon.domain.entities.Employee;
import com.riwi.beautySalon.domain.entities.ServiceEntity;
import com.riwi.beautySalon.domain.entities.User;


public class DataProviderAppointment {
    
    public static List<Appointment> sampleQuotesMock() {
        
        System.out.println(" --> Obteniendo listado Mock");

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setUser(null);
        clientEntity.setPhone("32145697");
        clientEntity.setLastName("Nero");
        clientEntity.setFirstName("Cindy");
        clientEntity.setEmail("cindy@gmail.com");

        User userEntity = new User();
        userEntity.setId("1L");
        userEntity.setRole(null);
        userEntity.setClient(clientEntity);
        userEntity.setPassword("123456789");
        userEntity.setUserName("Casimiro Perez");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Armando");
        employee.setLastName("Casas");
        employee.setEmail("armando@gmail.com");
        employee.setPhone("3004784345");
        employee.setRole(null);
        employee.setUser(userEntity);

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(1L);
        serviceEntity.setName("Peluqeria");
        BigDecimal price = BigDecimal.valueOf(10.00);
        serviceEntity.setPrice(price);
        serviceEntity.setDescription("Motilar");
        serviceEntity.setAppointments(null);

        // Cita #1
        Appointment appointment1 = new Appointment();
        appointment1.setId(1L);
        appointment1.setClient(clientEntity);
        appointment1.setDuration(30);
        appointment1.setService(serviceEntity);
        appointment1.setDateTime(null);
        appointment1.setEmployee(employee);
        appointment1.setComments("Muy buen Servicio");

        // Cita #2
        Appointment appointment2 = new Appointment();
        appointment2.setId(2L);
        appointment2.setClient(clientEntity);
        appointment2.setDuration(30);
        appointment2.setService(serviceEntity);
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 14, 30, 0);
        appointment2.setDateTime(dateTime);
        appointment2.setEmployee(employee);
        appointment2.setComments("Exelente");

        return List.of(appointment1, appointment2);
    };

    public static Appointment appointmentById(Long appointmentId) {

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setUser(null);
        clientEntity.setPhone("32145697");
        clientEntity.setLastName("Nero");
        clientEntity.setFirstName("Cindy");
        clientEntity.setEmail("cindy@gmail.com");

        User userEntity = new User();
        userEntity.setId("1L");
        userEntity.setRole(null);
        userEntity.setClient(clientById(null));
        userEntity.setPassword("123456789");
        userEntity.setUserName("Casimiro Perez");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Armando");
        employee.setLastName("Casas");
        employee.setEmail("armando@gmail.com");
        employee.setPhone("3004784345");
        employee.setRole(null);
        employee.setUser(userEntity);

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(1L);
        serviceEntity.setName("Peluqeria");
        BigDecimal price = BigDecimal.valueOf(10.00);
        serviceEntity.setPrice(price);
        serviceEntity.setDescription("Motilar");
        serviceEntity.setAppointments(null);

        Appointment appointment2 = new Appointment();
        appointment2.setId(2L);
        appointment2.setClient(clientEntity);
        appointment2.setDuration(30);
        appointment2.setService(serviceEntity);
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 14, 30, 0);
        appointment2.setDateTime(dateTime);
        appointment2.setEmployee(employee);
        appointment2.setComments("Exelente");

        return appointment2;
    }

    public static ClientEntity clientById(Long clientId) {

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setUser(null);
        clientEntity.setPhone("32145697");
        clientEntity.setLastName("Nero");
        clientEntity.setFirstName("Cindy");
        clientEntity.setEmail("cindy@gmail.com");

        return clientEntity;
    };

    public static Employee employeeById(Long employeeId) {

        User userEntity = new User();
        userEntity.setId("1L");
        userEntity.setRole(null);
        userEntity.setClient(clientById(null));
        userEntity.setPassword("123456789");
        userEntity.setUserName("Casimiro Perez");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Armando");
        employee.setLastName("Casas");
        employee.setEmail("armando@gmail.com");
        employee.setPhone("3004784345");
        employee.setRole(null);
        employee.setUser(userEntity);

        return employee;
    };

    public static ServiceEntity serviceEntityById(Long serviceId) {

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(1L);
        serviceEntity.setName("Peluqeria");
        BigDecimal price = BigDecimal.valueOf(10.00);
        serviceEntity.setPrice(price);
        serviceEntity.setDescription("Motilar");
        serviceEntity.setAppointments(null);

        return serviceEntity;
    };
}
