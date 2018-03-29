/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplofacturashilosarchivos.datos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alienware
 */
public class Factura implements Serializable, Comparable<Factura> {
    private String numero;
    private Date fecha;
    private InfoFactura infoFactura;
    private List<Detalle> detalles;

    public Factura() {
        inicializarDetalles();
    }

    public Factura(String numero, Date fecha) {
        this.numero = numero;
        this.fecha = fecha;
        inicializarDetalles();
    }

    public Factura(String numero, Date fecha, InfoFactura infoFactura) {
        this.numero = numero;
        this.fecha = fecha;
        this.infoFactura = infoFactura;
        inicializarDetalles();
    }

    public Factura(String numero, Date fecha, InfoFactura infoFactura, List<Detalle> detalles) {
        this.numero = numero;
        this.fecha = fecha;
        this.infoFactura = infoFactura;
        inicializarDetalles();
        this.detalles = detalles;
    }
    
    private void inicializarDetalles() {
        detalles = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public InfoFactura getInfoFactura() {
        return infoFactura;
    }

    public void setInfoFactura(InfoFactura infoFactura) {
        this.infoFactura = infoFactura;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }
    
    public BigDecimal getTotal() {
        BigDecimal resultado;
        BigDecimal itemTotal;
        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal iva = BigDecimal.ZERO;
        
        for (Detalle d: detalles) {
            itemTotal = d.getCantidad().multiply(d.getValorUnitario());
            if (d.getIvaPorciento().compareTo(BigDecimal.valueOf(12.00)) > 0) {
                iva = iva.add(itemTotal.multiply(d.getIvaPorciento().divide(BigDecimal.valueOf(100.00))));
            }
            subTotal = subTotal.add(itemTotal);
        }
        resultado = subTotal.add(iva);
        
        return resultado.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public int compareTo(Factura o) {
        return getTotal().compareTo(o.getTotal()) * -1;
    }
    
    
    
}
