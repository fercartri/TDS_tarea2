package uva.tds;

import java.time.LocalDate;

public class Factura {

    private String asunto;
    private LocalDate fecha;
    private double importe;


    public Factura(String asunto, LocalDate fecha, double importe){
        if(asunto == null)
            throw new IllegalArgumentException();

        this.asunto = asunto;
        this.fecha = fecha;
        this.importe = importe;

    }

    public String getAsunto() {
        return asunto;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public double getImporte() {
        return importe;
    }
}
