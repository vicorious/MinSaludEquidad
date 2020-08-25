package com.co.persistence;

import com.co.entities.ReporteMora;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Repository
public interface ReporteMoraRepository extends CrudRepository<ReporteMora, Long>
{
    @Query("SELECT a FROM ReporteMora a WHERE a.estadoMin IN :estados")
    List<ReporteMora> reporteMoraProcesar(@Param("estados") BigDecimal... estados);
}
