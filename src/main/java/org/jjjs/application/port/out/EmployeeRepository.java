package org.jjjs.application.port.out;

import org.jjjs.domain.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    void saveAll(List<Employee> employees);

    List<Employee> getAll();

    Employee getById(Long id);

    void deleteById(Long id);

    List<Employee> getByName(String name);
}
