package com.arya.service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arya.dto.EmployeeRequestDto;
import com.arya.entity.Employee;
import com.arya.exception.EmployeeNotFoundException;
import com.arya.repo.EmployeeRepository;

//controller just routes things, service handle business logic
@Service
public class EmployeeService {

    // private final HashMap<String, EmployeeRequestDto> dummyDb = new HashMap<>();

    // public String createEmployee(EmployeeRequestDto dto){
    //     //in production, here i'll convert dto->entity(db)
    //     String id = UUID.randomUUID().toString();
    //     dummyDb.put(id, dto);
    //     return id;
    // }

    // public EmployeeRequestDto getEmployee(String id){
    //     if(!dummyDb.containsKey(id)){
    //         // throw new EmployeeNotFound
    //     }
    //     return dummyDb.get(id);
    // }

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository){
        this.repository = repository;
    }

    public Long createEmployee(EmployeeRequestDto dto){
        //map dto -> entity
        Employee employee = new Employee(dto.name(), dto.email(), dto.departrment());
        return repository.save(employee).getId();
    }

    //pagination logic
    public Page<Employee> getEmployees(int page, int size, String sortBy, String departmentFilter){
        //create Pageable object (Page numbers start at 0)
        Pageable pageable = PageRequest.of(page, size);

        //apply filtering if exist
        if(departmentFilter != null && !departmentFilter.isEmpty()){
            return repository.findByDepartment(departmentFilter, pageable);
        }

        //return generic page
        return repository.findAll(pageable);
    }

    public EmployeeRequestDto getEmployee(Long id){
        Optional<Employee> e = repository.findById(id);
        if(!e.isPresent()){
            return new EmployeeRequestDto("na", "", "");
        }
        Employee emp = e.get();
        return new EmployeeRequestDto(emp.getName(), emp.getEmail(), emp.getDepartment());
    }
}
