package com.co.service;

import com.co.entities.NovedadesCentro;
import com.co.exception.MinSaludBusinessException;
import com.co.persistence.NovedadCentroTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@Service
public class NovedadesCentroTrabajoService
{
    @Autowired
    NovedadCentroTrabajoRepository novedadCentroTrabajoRepository;

    public List<NovedadesCentro> getNovedadesSedes(BigDecimal... estados) throws MinSaludBusinessException {
        List<NovedadesCentro> result = this.novedadCentroTrabajoRepository.getCentros(estados);
        if (result.size() == 0) {
            throw new MinSaludBusinessException("No existen novedades transitorias en estado: ".concat(Arrays.toString(estados)));
        }

        return result;
    }

    public void add(NovedadesCentro NovedadesSede)
    {
        this.novedadCentroTrabajoRepository.save(NovedadesSede);
    }
}
