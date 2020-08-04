package com.co.persistence;

import com.co.entities.NovedadIBCTipsal;
import com.co.entities.NovedadesTransitorias;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IBCRepository extends CrudRepository<NovedadIBCTipsal, Long>
{
    @Query("SELECT a FROM NovedadIBCTipsal a WHERE a.estadoMin IN :estados")
    List<NovedadIBCTipsal> getNovedadIBCTipsal(@Param("estados") BigDecimal... estado);
}