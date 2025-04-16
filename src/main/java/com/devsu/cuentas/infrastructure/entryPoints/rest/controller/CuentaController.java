package com.devsu.cuentas.infrastructure.entryPoints.rest.controller;

import com.devsu.cuentas.application.useCase.cuenta.CuentaUseCase;
import com.devsu.cuentas.application.useCase.reporte.ReporteUseCase;
import com.devsu.cuentas.domain.model.cuenta.Cuenta;
import com.devsu.cuentas.domain.model.reporte.EstadoDeCuenta;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaUseCase cuentaUseCase;
    private final ReporteUseCase reporteUseCase;

    public CuentaController(CuentaUseCase cuentaUseCase, ReporteUseCase reporteUseCase) {
        this.cuentaUseCase = cuentaUseCase;
        this.reporteUseCase = reporteUseCase;
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

    @GetMapping("/reportes")
    public ResponseEntity<EstadoDeCuenta> generarReporte(
            @RequestParam String identificacionCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta
    ) {
        EstadoDeCuenta reporte = reporteUseCase.generarEstadoDeCuenta(identificacionCliente, desde, hasta);
        return ResponseEntity.ok(reporte);
    }
}
