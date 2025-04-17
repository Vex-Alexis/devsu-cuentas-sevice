package com.devsu.cuentas.domain.model.reporte;

import com.devsu.cuentas.domain.model.movimiento.Movimiento;
import lombok.AllArgsConstructor;

import java.util.List;

public class CuentaReporte {
    private Long cuentaId;
    private String tipoCuenta;
    private Double saldoActual;
    private List<Movimiento> movimientos;

    public CuentaReporte(Long cuentaId, String tipoCuenta, Double saldoActual, List<Movimiento> movimientos) {
        this.cuentaId = cuentaId;
        this.tipoCuenta = tipoCuenta;
        this.saldoActual = saldoActual;
        this.movimientos = movimientos;
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
