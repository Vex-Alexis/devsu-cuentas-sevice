package com.devsu.cuentas.infrastructure.entryPoints.rest.controller;

import com.devsu.cuentas.application.useCase.cuenta.CuentaUseCase;
import com.devsu.cuentas.domain.model.cuenta.Cuenta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaUseCase cuentaUseCase;

    public CuentaController(CuentaUseCase cuentaUseCase) {
        this.cuentaUseCase = cuentaUseCase;
    }

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaUseCase.crearCuenta(cuenta);
        return ResponseEntity.ok(nuevaCuenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> consultarCuentaPorId(@PathVariable Long id) {
        Optional<Cuenta> cuenta = cuentaUseCase.consultarCuentaPorId(id);
        return cuenta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentas() {
        return ResponseEntity.ok(cuentaUseCase.listarCuentas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        cuenta.setCuentaId(id);
        Cuenta cuentaActualizada = cuentaUseCase.actualizarCuenta(cuenta);
        return ResponseEntity.ok(cuentaActualizada);
    }
}
