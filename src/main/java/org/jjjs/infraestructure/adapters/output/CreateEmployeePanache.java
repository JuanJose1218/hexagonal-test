package org.jjjs.infraestructure.adapters.output;

import jakarta.enterprise.context.ApplicationScoped;
import org.jjjs.domain.model.Employee;
import org.jjjs.domain.port.EmployeeRepository;

@ApplicationScoped
public class CreateEmployeePanache implements EmployeeRepository {

    private final EmployeeRepositoryPanacheAdapter productPancheRepository;

    public CreateEmployeePanache(EmployeeRepositoryPanacheAdapter productPancheRepository) {
        this.productPancheRepository = productPancheRepository;
    }

    @Override
    public void saveProduct(Employee producto) {

        //productPancheRepository.save(entity);


    }
}
