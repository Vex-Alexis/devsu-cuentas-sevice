package com.devsu.cuentas.domain.useCase.cuenta;

import com.devsu.cuentas.domain.model.cuenta.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaCRUDUseCase {
    Cuenta crearCuenta(Cuenta cuenta);
    Optional<Cuenta> consultarCuentaPorId(Long cuentaId);
    List<Cuenta> listarCuentas();
    Cuenta actualizarCuenta(Cuenta cuenta);
}
