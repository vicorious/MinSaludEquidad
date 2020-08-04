package com.co.service;

import com.co.entities.InicioLaboral;
import com.co.entities.NovedadesSede;
import com.co.entities.RetiroDefinitivoSGRL;
import com.co.exception.MinSaludBusinessException;
import com.co.persistence.InicioLaboralRepository;
import com.co.persistence.NovedaSedeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class NovedadesSedesService
{
    @Autowired
    private NovedaSedeRepository novedaSedeRepository;

    public List<NovedadesSede> getNovedadesSedes(BigDecimal... estados) throws MinSaludBusinessException {
        List<NovedadesSede> result = this.novedaSedeRepository.getNovedadesSedes(estados);
        if (result.size() == 0) {
            throw new MinSaludBusinessException("No existen NovedadesSedes en estado: ".concat(Arrays.toString(estados)));
        }

        return result;
    }

    public void add(NovedadesSede NovedadesSede)
    {
        this.novedaSedeRepository.save(NovedadesSede);
    }

}
