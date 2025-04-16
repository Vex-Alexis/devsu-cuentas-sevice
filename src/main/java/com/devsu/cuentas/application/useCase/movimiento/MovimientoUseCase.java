package com.devsu.cuentas.application.useCase.movimiento;

import com.devsu.cuentas.domain.model.cuenta.Cuenta;
import com.devsu.cuentas.domain.model.cuenta.gateways.CuentaPostgreSQLGateway;
import com.devsu.cuentas.domain.model.movimiento.Movimiento;
import com.devsu.cuentas.domain.model.movimiento.gateways.MovimientoPostgreSQLGateway;
import com.devsu.cuentas.domain.useCase.movimiento.MovimientoCRUDUseCase;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class MovimientoUseCase implements MovimientoCRUDUseCase {

    private final MovimientoPostgreSQLGateway movimientoPostgreSQLGateway;
    private final CuentaPostgreSQLGateway cuentaPostgreSQLGateway;

    public MovimientoUseCase(MovimientoPostgreSQLGateway movimientoPostgreSQLGateway, CuentaPostgreSQLGateway cuentaPostgreSQLGateway) {
        this.movimientoPostgreSQLGateway = movimientoPostgreSQLGateway;
        this.cuentaPostgreSQLGateway = cuentaPostgreSQLGateway;
    }

    @Override
    public Movimiento crearMovimiento(Movimiento movimiento) {
        Cuenta cuenta = cuentaPostgreSQLGateway.obtenerCuentaPorId(movimiento.getCuentaId())
                .orElseThrow(()-> new RuntimeException("Cuenta no encontrada"));

        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo no disponible");
        }
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaPostgreSQLGateway.actualizarCuenta(cuenta); // actualiza el saldo

        movimiento.setSaldo(nuevoSaldo); // guardamos también el saldo al momento
        movimiento.setFecha(LocalDate.now());

        return movimientoPostgreSQLGateway.crearMovimiento(movimiento);
    }

    @Override
    public List<Movimiento> obtenerTodosLosMovimientos() {
        return movimientoPostgreSQLGateway.obtenerTodosLosMovimientos();
    }

    @Override
    public Optional<Movimiento> obtenerMovimientoPorId(Long movimientoId) {
        return movimientoPostgreSQLGateway.obtenerMovimientoPorId(movimientoId);
    }

    @Override
    public List<Movimiento> obtenerMovimientosPorCuentaId(Long cuentaId) {
        return movimientoPostgreSQLGateway.obtenerMovimientosPorCuentaId(cuentaId);
    }

    @Override
    public Movimiento actualizarMovimiento(Long movimientoId, Movimiento movimiento) {
        return movimientoPostgreSQLGateway.actualizarMovimiento(movimientoId, movimiento);
    }
}
