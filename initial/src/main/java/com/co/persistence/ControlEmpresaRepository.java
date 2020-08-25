package com.co.persistence;

import com.co.entities.ControlEstructuraEmpresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ControlEmpresaRepository extends CrudRepository<ControlEstructuraEmpresa, Long> {
}
