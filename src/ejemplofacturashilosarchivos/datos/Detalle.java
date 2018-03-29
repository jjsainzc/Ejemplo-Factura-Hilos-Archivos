/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplofacturashilosarchivos.datos;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author alienware
 */
public class Detalle implements Serializable {
    private String descripcion;
    private BigDecimal cantidad;
    private BigDecimal valorUnitario;
    private BigDecimal ivaPorciento;

    public Detalle() {
    }

    public Detalle(String descripcion, BigDecimal cantidad, BigDecimal valorUnitario, BigDecimal ivaPorciento) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.valorUnitario = valorUnitario;
        this.ivaPorciento = ivaPorciento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getIvaPorciento() {
        return ivaPorciento;
    }

    public void setIvaPorciento(BigDecimal ivaPorciento) {
        this.ivaPorciento = ivaPorciento;
    }
    
    
}
