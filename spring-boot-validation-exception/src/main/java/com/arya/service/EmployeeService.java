package com.arya.service;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.arya.dto.EmployeeRequestDto;

//controller just routes things, service handle business logic
@Service
public class EmployeeService {

    private final HashMap<String, EmployeeRequestDto> dummyDb = new HashMap<>();

    public String createEmployee(EmployeeRequestDto dto){
        //in production, here i'll convert dto->entity(db)
        String id = UUID.randomUUID().toString();
        dummyDb.put(id, dto);
        return id;
    }

    public EmployeeRequestDto getEmployee(String id){
        if(!dummyDb.containsKey(id)){
            // throw new EmployeeNotFound
        }
        return dummyDb.get(id);
    }
}
