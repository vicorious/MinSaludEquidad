package com.co.persistence;

import com.co.entities.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
    @Query("SELECT a FROM Empleado a WHERE a.centro.id IN :id")
    List<Empleado> getCentroBySedeId(@Param("id") BigDecimal id);
}
