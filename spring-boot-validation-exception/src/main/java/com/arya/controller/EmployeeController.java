package com.arya.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arya.dto.EmployeeRequestDto;
import com.arya.service.EmployeeService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController //means controller as well as Response body , i.e., response will be in json/xml dirrectly not views
@RequestMapping("/api/employees") //base url
public class EmployeeController {

    private final EmployeeService service;

    //constructor injection, better than autowired fireld injection, as testing problem , isolation things etc, better testabiliy
    //dependencies can be final, immutability
    //fail-fast: app wont start if missing
    //clear dependencies, can be easily seen
    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping
    //constraints dont execute themselves, @valid will
    //req. body is for json req and uses httpmessageconverter, modelAttribute for form
    public ResponseEntity<String> create(@RequestBody @Valid EmployeeRequestDto dto){
        String id = service.createEmployee(dto);
        return new ResponseEntity<>("created id: "+id, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRequestDto> get(@PathVariable String id) {
        return ResponseEntity.ok(service.getEmployee(id));
        // ResponseEntity<>(service.getEmployee(id), HttpStatus.OK);
    }
    
}
