package com.co.service;

import com.co.entities.NovedadesTransitorias;
import com.co.exception.MinSaludBusinessException;
import com.co.persistence.NovedadesTransitoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@Service
public class NovedadesTransitoriasService
{
    @Autowired
    NovedadesTransitoriasRepository novedadesTransitoriasRepository;

    public List<NovedadesTransitorias> getNovedadesTransitorias(BigDecimal... estados) throws MinSaludBusinessException {
        List<NovedadesTransitorias> result = this.novedadesTransitoriasRepository.getNovedadesTransitorias(estados);
        if (result.size() == 0) {
            throw new MinSaludBusinessException("No existen novedades transitorias en estado: ".concat(Arrays.toString(estados)));
        }

        return result;
    }

    public void add(NovedadesTransitorias NovedadesSede)
    {
        this.novedadesTransitoriasRepository.save(NovedadesSede);
    }
}
