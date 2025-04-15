package com.devsu.cuentas.infrastructure.entryPoints.rest.controller;

import com.devsu.cuentas.domain.model.movimiento.Movimiento;
import com.devsu.cuentas.domain.useCase.movimiento.MovimientoCRUDUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoCRUDUseCase movimientoUseCase;

    public MovimientoController(MovimientoCRUDUseCase movimientoUseCase) {
        this.movimientoUseCase = movimientoUseCase;
    }

    @PostMapping
    public ResponseEntity<Movimiento> crearMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento creado = movimientoUseCase.crearMovimiento(movimiento);
        return ResponseEntity.created(URI.create("/movimientos/" + creado.getMovimientoId())).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> obtenerTodosLosMovimientos() {
        return ResponseEntity.ok(movimientoUseCase.obtenerTodosLosMovimientos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable Long id) {
        return movimientoUseCase.obtenerMovimientoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorCuenta(@PathVariable Long cuentaId) {
        List<Movimiento> movimientos = movimientoUseCase.obtenerMovimientosPorCuentaId(cuentaId);
        return ResponseEntity.ok(movimientos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> actualizarMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        Movimiento actualizado = movimientoUseCase.actualizarMovimiento(id, movimiento);
        return ResponseEntity.ok(actualizado);
    }
}
