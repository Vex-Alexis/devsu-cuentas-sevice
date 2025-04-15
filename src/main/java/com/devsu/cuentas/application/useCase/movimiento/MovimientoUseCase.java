package com.devsu.cuentas.application.useCase.movimiento;

import com.devsu.cuentas.domain.model.movimiento.Movimiento;
import com.devsu.cuentas.domain.model.movimiento.gateways.MovimientoPostgreSQLGateway;
import com.devsu.cuentas.domain.useCase.movimiento.MovimientoCRUDUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MovimientoUseCase implements MovimientoCRUDUseCase {

    private final MovimientoPostgreSQLGateway movimientoPostgreSQLGateway;

    public MovimientoUseCase(MovimientoPostgreSQLGateway movimientoPostgreSQLGateway) {
        this.movimientoPostgreSQLGateway = movimientoPostgreSQLGateway;
    }

    @Override
    public Movimiento crearMovimiento(Movimiento movimiento) {
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
