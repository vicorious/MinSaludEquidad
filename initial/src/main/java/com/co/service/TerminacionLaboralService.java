package com.co.service;

import com.co.entities.TerminacionLaboral;
import com.co.exception.MinSaludBusinessException;
import com.co.persistence.TerminacionLaboralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@Service
public class TerminacionLaboralService
{
    @Autowired
    TerminacionLaboralRepository terminacionLaboralRepository;

    public List<TerminacionLaboral> getTerminacionesLaborales(BigDecimal... estados) throws MinSaludBusinessException {
        List<TerminacionLaboral> result = this.terminacionLaboralRepository.getTerminacionesLaborales(estados);
        if (result.size() == 0) {
            throw new MinSaludBusinessException("No existen terminacionesLaborales en estado: ".concat(Arrays.toString(estados)));
        }

        return result;
    }

    public void add(TerminacionLaboral terminacionLaboral){
        this.terminacionLaboralRepository.save(terminacionLaboral);
    }
}
