package org.jjjs.application.port.out;

import org.jjjs.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    void saveAll(List<Employee> employees);

    List<Employee> getAll();

    Optional<Employee> getById(Long id);

    void deleteById(Long id);

    List<Employee> getByName(String name);

    void updateById(Employee employee);
}
