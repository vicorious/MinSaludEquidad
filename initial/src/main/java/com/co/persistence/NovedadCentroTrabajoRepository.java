package com.co.persistence;

import com.co.entities.NovedadesCentro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface NovedadCentroTrabajoRepository extends CrudRepository<NovedadesCentro, Long>
{
    @Query("SELECT a FROM NovedadesCentro a WHERE a.estadoMin IN :estados")
    List<NovedadesCentro> getCentros(@Param("estados") BigDecimal... estado);
}