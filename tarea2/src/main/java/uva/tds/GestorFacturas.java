package uva.tds;

import java.time.LocalDate;

public class GestorFacturas {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String nombre;


    public GestorFacturas(LocalDate inicio, LocalDate fin, String nombre){
        if(inicio == null || fin == null)
            throw new IllegalArgumentException();
        
        this.fechaInicio = inicio;
        this.fechaFin = fin;
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public String getNombre() {
        return nombre;
    }

}
