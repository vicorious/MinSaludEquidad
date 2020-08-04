package com.co.persistence;

import com.co.entities.NovedadesSede;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public interface NovedaSedeRepository extends CrudRepository<NovedadesSede, Long>
{
    @Query("SELECT a FROM NovedadesSede a WHERE a.estadoMin IN :estados")
    List<NovedadesSede> getNovedadesSedes(@Param("estados") BigDecimal... estado);
}
