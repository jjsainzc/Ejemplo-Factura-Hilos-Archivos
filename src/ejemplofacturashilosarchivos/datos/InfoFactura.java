/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplofacturashilosarchivos.datos;

import java.io.Serializable;

/**
 *
 * @author alienware
 */
public class InfoFactura implements Serializable {
    private String nombreCliente;
    private String ruc;

    public InfoFactura() {
    }

    public InfoFactura(String nombreCliente, String ruc) {
        this.nombreCliente = nombreCliente;
        this.ruc = ruc;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    
}
