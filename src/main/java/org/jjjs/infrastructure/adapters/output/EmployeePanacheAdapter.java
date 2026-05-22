package org.jjjs.infrastructure.adapters.output;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.jjjs.application.port.out.EmployeeRepository;
import org.jjjs.domain.exceptions.EntityNotFoundException;
import org.jjjs.domain.model.Employee;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class EmployeePanacheAdapter implements EmployeeRepository {

    private final EmployeePanacheRepository employeePanacheRepository;


    @Override
    @Transactional
    public void saveAll(List<Employee> employees) {
        try {
            var entities = employees.stream()
                    .map(employeeDomain ->
                            new EmployeeEntity(
                                    employeeDomain.getFirstName(),
                                    employeeDomain.getSecondName(),
                                    employeeDomain.getLastName(),
                                    employeeDomain.getSecondLastName(),
                                    employeeDomain.getAge(),
                                    employeeDomain.getGender(),
                                    employeeDomain.getBirthDate(),
                                    employeeDomain.getJobPosition(),
                                    employeeDomain.getCreatedAt(),
                                    employeeDomain.getStatus()
                            )).toList();
            employeePanacheRepository.persist(entities);

        } catch (HibernateException e) {
            log.info("no se pudo guardar los empleados");

        }


    }

    @Override
    public List<Employee> getAll() {
        return employeePanacheRepository.findAll()
                .stream().map(Employee::new).toList();

    }

    @Override
    public Employee getById(Long id) {
        var employeeEntity = employeePanacheRepository.findByIdOptional(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));
        return new Employee(employeeEntity);
    }

    @Override
    public void deleteById(Long id) {
        employeePanacheRepository.deleteById(id);

    }

    @Override
    public List<Employee> getByName(String name) {
        return employeePanacheRepository.findByName(name).stream().map(Employee::new).toList();

    }
}
