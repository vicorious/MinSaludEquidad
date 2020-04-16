package com.co.persistence;

import com.co.entities.InicioLaboral;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


@Transactional
@Repository
public interface InicioLaboralRepository extends CrudRepository<InicioLaboral, Long>
{
    @Query("SELECT a FROM InicioLaboral a WHERE a.estadoMin IN :estados")
    List<InicioLaboral> getIniciosLaborales(@Param("estados") BigDecimal... estado);
}
