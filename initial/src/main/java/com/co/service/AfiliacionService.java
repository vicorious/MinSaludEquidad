package com.co.service;

import com.co.entities.AfiliacionEmpresa;
import com.co.exception.MinSaludBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.co.persistence.AfiliacionRepository;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

@Component
@Service
public class AfiliacionService
{
    @Autowired
    AfiliacionRepository repository;

    public void add(AfiliacionEmpresa afiliacionEmpresa)
    {
        this.repository.save(afiliacionEmpresa);
    }

    public List<AfiliacionEmpresa> afiliaciones()
    {
        return (List<AfiliacionEmpresa>) this.repository.findAll();
    }

    public List<AfiliacionEmpresa> afiliacionPorEstado(BigDecimal... estados) throws MinSaludBusinessException {
        List<AfiliacionEmpresa> result = this.repository.afiliacionesPorProcesarQuery(estados);
        if (result.size() == 0) {
            throw new MinSaludBusinessException("No existen afiliaciones en estado: ".concat(Arrays.toString(estados)));
        }
        else
        {
            result.forEach(p ->
            {
                p.activiEconimi = Integer.parseInt(p.getActividadEconomica() != null ? p.getActividadEconomica().trim() : "0");
                p.tipAportante = Integer.parseInt(p.getTipoAportante() != null ? p.getTipoAportante().trim() : "0");
                p.natuJuridica = Integer.parseInt(p.getNaturalezaJuridica() != null ?  p.getNaturalezaJuridica().trim() : "0");
                p.setRazonSocialEmpleador(eliminaAcentos(p.getRazonSocialEmpleador()));

            });
            return result;
        }
    }

    public String eliminaAcentos(String s) {
        if(s == null || s.trim().length() == 0) {
            return "";
        }
        s = s.replace('Ñ', '\001');
        s = s.replace('Ð', '\001');
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        s = s.replace('\001', 'Ñ');
        return s;
    }

}
