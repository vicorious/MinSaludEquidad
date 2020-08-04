package com.co.persistence;

import com.co.entities.CentroTrabajo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public interface CentroRepository extends CrudRepository<CentroTrabajo, Long> {
    @Query("SELECT a FROM CentroTrabajo a WHERE a.sede.id IN :id")
    List<CentroTrabajo> getCentroBySedeId(@Param("id") BigDecimal id);
}
