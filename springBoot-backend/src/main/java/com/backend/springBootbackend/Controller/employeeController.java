package com.backend.springBootbackend.Controller;

import com.backend.springBootbackend.Exception.ResourceNotFoundException;
import com.backend.springBootbackend.Model.Employee;
import com.backend.springBootbackend.Repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class employeeController {
    @Autowired
    private employeeRepository repository;

//get all employees
    @GetMapping("/Employee")
    public List<Employee> showEmployee(){
        return repository.findAll() ;
    }

    //to save employee
    @PostMapping("/Employee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return repository.save(employee);

    }

    //get employee by id
    @GetMapping("/Employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
       Employee employee= repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("employee not found with id"+id));
        return ResponseEntity.ok(employee);
    }

    // update employee
    @PutMapping("/Employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
        Employee employee= repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee not found with id"+id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());

       Employee updatedEmployee= repository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    //Delete employee
    @DeleteMapping("/Employee/{id}")
    public ResponseEntity <Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee=repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("employee not exsist  "+id));
        repository.delete(employee);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response) ;

    }

}
