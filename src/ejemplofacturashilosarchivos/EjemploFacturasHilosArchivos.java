/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplofacturashilosarchivos;

import ejemplofacturashilosarchivos.datos.Detalle;
import ejemplofacturashilosarchivos.datos.Factura;
import ejemplofacturashilosarchivos.datos.InfoFactura;
import ejemplofacturashilosarchivos.utilidades.EntradaSalida;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EjemploFacturasHilosArchivos {

    private List<Factura> facturas;
    private File file;

    private Boolean leerArchivoImprimirResultados() {
        if (file.exists() && file.canRead() && (file.length() > 0)) {
            try {
                facturas = EntradaSalida.<Factura>leerArchivoObjeto(file);
                if (!facturas.isEmpty()) {
                    Collections.sort(facturas);
                    System.out.println("Resultado: No."+facturas.get(0).getNumero()+" Valor:"+
                                       facturas.get(0).getTotal());

                }
                file.delete();
                System.out.println("Finaliza hilo");
                return true;
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
            }
        }
        return false;
    }

    public void escribirArchivo() throws FileNotFoundException, IOException {
        EntradaSalida.<Factura>escribirArchivoObjeto(file, facturas);
    }

    class Hilo implements Runnable {

        @Override
        public void run() {
            while (!leerArchivoImprimirResultados()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    
                }
            }
        }
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static void main(String[] args) {
        List<Factura> facturas = new ArrayList<>();
        List<Detalle> detalles;
        Factura factura;

        
        EjemploFacturasHilosArchivos facturaManejo = new EjemploFacturasHilosArchivos();
        facturaManejo.setFile(new File("facturas.bin"));

        System.out.println("Inicio Hilo");
        new Thread(facturaManejo.new Hilo()).start();

        System.out.println("Lleno coleccion");
        factura = new Factura("0002", new Date(), new InfoFactura("Cliente 1", "2424124213"));
        detalles = new ArrayList<>();
        detalles.add(new Detalle("Producto 1",
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2.45567),
                BigDecimal.valueOf(12)));
        detalles.add(new Detalle("Producto 2",
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(0.55345),
                BigDecimal.valueOf(12)));
        detalles.add(new Detalle("Producto 3",
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(15.3234),
                BigDecimal.ZERO));
        factura.setDetalles(detalles);
        facturas.add(factura);

        factura = new Factura("0003", new Date(), new InfoFactura("Cliente 2", "6464356436"));
        detalles = new ArrayList<>();
        detalles.add(new Detalle("Producto 1",
                BigDecimal.valueOf(12),
                BigDecimal.valueOf(12.45567),
                BigDecimal.valueOf(12)));
        detalles.add(new Detalle("Producto 2",
                BigDecimal.valueOf(8),
                BigDecimal.valueOf(0.346446),
                BigDecimal.valueOf(12)));

        factura.setDetalles(detalles);
        facturas.add(factura);

        facturaManejo.setFacturas(facturas);
        
        System.out.println("Escribo archivo");
        try {
            facturaManejo.escribirArchivo();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.toString());
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }


        System.out.println("Termina main");

    }
}
