package com.devsu.cuentas.application.useCase.cuenta;

import com.devsu.cuentas.domain.model.cuenta.Cuenta;
import com.devsu.cuentas.domain.model.cuenta.gateways.CuentaPostgreSQLGateway;
import com.devsu.cuentas.domain.useCase.cuenta.CuentaCRUDUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CuentaUseCase implements CuentaCRUDUseCase {
    private final CuentaPostgreSQLGateway cuentaPostgreSQLGateway;

    public CuentaUseCase(CuentaPostgreSQLGateway cuentaPostgreSQLGateway) {
        this.cuentaPostgreSQLGateway = cuentaPostgreSQLGateway;
    }

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaPostgreSQLGateway.crearCuenta(cuenta);
    }

    @Override
    public Optional<Cuenta> consultarCuentaPorId(Long cuentaId) {
        return cuentaPostgreSQLGateway.obtenerCuentaPorId(cuentaId);
    }

    @Override
    public List<Cuenta> listarCuentas() {
        return cuentaPostgreSQLGateway.obtenerTodasLasCuentas();
    }

    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        return cuentaPostgreSQLGateway.actualizarCuenta(cuenta);
    }
}
