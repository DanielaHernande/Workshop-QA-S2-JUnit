package com.riwi.beautySalon.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.riwi.beautySalon.api.dto.response.AppointmentBasicResp;
import com.riwi.beautySalon.domain.entities.Appointment;
import com.riwi.beautySalon.domain.entities.ClientEntity;
import com.riwi.beautySalon.domain.entities.Employee;
import com.riwi.beautySalon.domain.entities.ServiceEntity;
import com.riwi.beautySalon.domain.entities.User;

public class DataProviderClient {

    
    @SuppressWarnings("unchecked")
    public static List<AppointmentBasicResp> sampleQuotesMock() {
        
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
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 14, 30, 0);
        appointment1.setDateTime(dateTime);
        appointment1.setComments("Muy buen Servicio");


        return (List<AppointmentBasicResp>) appointment1;
    };

    // List
    public static List<ClientEntity> clientList() {
        System.out.println(" --> Obteniendo listado Mock");

        // Crear una lista de ClientEntity
        List<ClientEntity> clients = new ArrayList<>();

        // Crear y configurar el primer ClientEntity
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1L);
        clientEntity.setUser(null); // Ajusta si es necesario
        clientEntity.setPhone("32145697");
        clientEntity.setLastName("Nero");
        clientEntity.setFirstName("Cindy");
        clientEntity.setEmail("cindy@gmail.com");

        // Crear y configurar el segundo ClientEntity
        ClientEntity clientEntity1 = new ClientEntity();
        clientEntity1.setId(2L); // Asegúrate de que los IDs sean únicos
        clientEntity1.setUser(null); // Ajusta si es necesario
        clientEntity1.setPhone("98765432");
        clientEntity1.setLastName("Smith");
        clientEntity1.setFirstName("John");
        clientEntity1.setEmail("john.smith@example.com");

        // Añadir los ClientEntity a la lista
        clients.add(clientEntity);
        clients.add(clientEntity1);

        // Devolver la lista de ClientEntity
        return clients;
    };


    public static List<Appointment> appointmentsEntity() {

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
        LocalDateTime dateTime = LocalDateTime.of(2024, 8, 26, 14, 30, 0);
        appointment1.setDateTime(dateTime);
        appointment1.setEmployee(employee);
        appointment1.setComments("Muy buen Servicio");

        return List.of(appointment1);
    };


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
}
