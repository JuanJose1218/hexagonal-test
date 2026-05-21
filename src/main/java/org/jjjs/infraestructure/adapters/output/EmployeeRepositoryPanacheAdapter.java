package org.jjjs.infraestructure.adapters.output;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class EmployeeRepositoryPanacheAdapter implements PanacheRepository<Employee> {

    @Transactional
    public void save(Employee productEntity) {
        log.info("capa de persistencia");
        persist(productEntity);

    }
}
