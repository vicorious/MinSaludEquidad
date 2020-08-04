package com.co.persistence;

import com.co.entities.ReclasificacionCentroTrabajo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public interface ReclasificacionCentroTrabajoRepository extends CrudRepository<ReclasificacionCentroTrabajo, Long> {

    @Query("SELECT a FROM ReclasificacionCentroTrabajo a WHERE a.estadoMin IN :estados")
    List<ReclasificacionCentroTrabajo> getRelasificacionCentroTrabajo(@Param("estados") BigDecimal... estado);
}
