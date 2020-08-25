package com.co.service;

import com.co.entities.ReporteMora;
import com.co.exception.MinSaludBusinessException;
import com.co.persistence.ReporteMoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@Service
public class ReporteMoraService
{
    @Autowired
    ReporteMoraRepository repository;

    public void add(ReporteMora afiliacionEmpresa) {
        this.repository.save(afiliacionEmpresa);
    }

    public List<ReporteMora> mora()
    {
        return (List<ReporteMora>) this.repository.findAll();
    }

    public List<ReporteMora> reporteMoraPorEstados(BigDecimal... estados) throws MinSaludBusinessException
    {
        List<ReporteMora> result = this.repository.reporteMoraProcesar(estados);
        if (result.size() == 0) {
            throw new MinSaludBusinessException("No existen reportesMora en estado: ".concat(Arrays.toString(estados)));
        }

        return result;
    }
}
