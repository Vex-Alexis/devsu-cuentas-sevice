package com.devsu.cuentas.domain.model.cuenta.gateways;

import com.devsu.cuentas.domain.model.cuenta.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaPostgreSQLGateway {
    Cuenta crearCuenta(Cuenta cuenta);
    Optional<Cuenta> obtenerCuentaPorId(Long cuentaId);
    List<Cuenta> obtenerTodasLasCuentas();
    Cuenta actualizarCuenta(Cuenta cuenta);
}
