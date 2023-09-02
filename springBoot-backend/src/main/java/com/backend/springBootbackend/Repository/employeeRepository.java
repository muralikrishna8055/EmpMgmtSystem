package com.backend.springBootbackend.Repository;

import com.backend.springBootbackend.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employeeRepository extends JpaRepository<Employee,Long> {
}
