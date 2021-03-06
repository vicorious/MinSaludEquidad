package com.co.service;

import com.co.entities.NovedadIBCTipsal;
import com.co.exception.MinSaludBusinessException;
import com.co.persistence.IBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@Service
public class IBService
{
    @Autowired
    IBCRepository ibcRepository;

    public List<NovedadIBCTipsal> getIBC(BigDecimal... estados) throws MinSaludBusinessException {
        List<NovedadIBCTipsal> result = this.ibcRepository.getNovedadIBCTipsal(estados);
        if (result.size() == 0) {
            throw new MinSaludBusinessException("No existen novedadIBCTipsal en estado: ".concat(Arrays.toString(estados)));
        }

        return result;
    }

    public void add(NovedadIBCTipsal novedadIBCTipsal)
    {
        this.ibcRepository.save(novedadIBCTipsal);
    }
}
