package org.jjjs.infrastructure.adapters.output;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped

public class EmployeePanacheRepository implements PanacheRepository<EmployeeEntity> {

    public List<EmployeeEntity> findByName(String name) {

        return list("jobPosition LIKE ?1", "%" + name + "%");
    }


}
