package com.co.persistence;

import com.co.entities.InicioLaboral;
import com.co.entities.NovedadesTransitorias;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface NovedadesTransitoriasRepository extends CrudRepository<NovedadesTransitorias, Long>
{
    @Query("SELECT a FROM NovedadesTransitorias a WHERE a.estadoMin IN :estados")
    List<NovedadesTransitorias> getNovedadesTransitorias(@Param("estados") BigDecimal... estado);
}