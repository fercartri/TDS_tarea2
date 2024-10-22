package uva.tds;

import java.time.LocalDate;

public class Factura {

    private String asunto;
    private LocalDate fecha;
    private double importe;


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

    public String getAsunto() {
        return asunto;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public double getImporte() {
        return importe;
    }

    public void setFecha(LocalDate f){

    }
    public void setImporte(double i){
        
    }

    @Override
    public boolean equals(Object o){
        return this.asunto.equals(((Factura) o).getAsunto());
    }
}
