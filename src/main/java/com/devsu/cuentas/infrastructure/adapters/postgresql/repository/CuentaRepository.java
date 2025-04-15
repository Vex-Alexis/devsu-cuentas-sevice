package com.devsu.cuentas.infrastructure.adapters.postgresql.repository;

import com.devsu.cuentas.infrastructure.adapters.postgresql.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {
}
