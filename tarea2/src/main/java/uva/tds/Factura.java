/**
 * @author Fernando Carmona Palacio
 */

package uva.tds;

import java.time.LocalDate;

public class Factura {

    private String asunto;
    private LocalDate fecha;
    private double importe;

    /**
     * Construye un nuevo objeto factura
     * @param asunto un String con el asunto de la factura
     * @param fecha un LocalDate con la fecha de la factura
     * @param importe un double con el importe de la factura
     * @throws IllegalArgumentException si asunto es null
     * @throws IllegalArgumentException si fecha es null
     * @throws IllegalArgumentException si asunto es una cadena vacía
     * @throws IllegalArgumentException si importe menor que cero
     */
    public Factura(String asunto, LocalDate fecha, double importe){
        if(asunto == null || fecha == null)
            throw new IllegalArgumentException();
        
        if(asunto.length() < 1)
            throw new IllegalArgumentException();

        if(importe < 0)
            throw new IllegalArgumentException();

        this.asunto = asunto;
        this.fecha = fecha;
        this.importe = importe;
    }

    /**
     * Consulta el asunto de la factura
     * @return un String con dicho asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Consulta la fecha de la factura
     * @return un LocalDate con dicha fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Consulta el importe de la factura
     * @return un double con dicho importe
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Establece una nueva fecha para la factura
     * @param fecha un LocalDate con la nueva fecha
     * @throws IllegalArgumentException si fecha es null
     */
    public void setFecha(LocalDate fecha){
        if(fecha == null)
            throw new IllegalArgumentException();

        this.fecha = fecha;
    }

    /**
     * Establece un nuevo importe para la factura
     * @param importe un double con el nuevo importe
     * @throws IllegalArgumentException si el importe es menor que cero
     */
    public void setImporte(double importe){
        if(importe < 0)
            throw new IllegalArgumentException();

        this.importe = importe;
    }

    @Override
    public boolean equals(Object o){
        return this.asunto.equals(((Factura) o).getAsunto());
    }
}