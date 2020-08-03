package com.co.persistence;

import com.co.entities.ReclasificacionCentroTrabajo;
import com.co.entities.RespuestaSATARL;
import com.co.entities.RetiroDefinitivoSGRL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public interface RetiroDefinitivoRepository extends CrudRepository<RetiroDefinitivoSGRL, Long> {

    @Query("SELECT a FROM RetiroDefinitivoSGRL a WHERE a.estadoMin IN :estados")
    List<RetiroDefinitivoSGRL> getRetiroDefinitivoSGRL(@Param("estados") BigDecimal... estado);
}
