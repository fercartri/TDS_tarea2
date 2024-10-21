package uva.tds;

import java.time.LocalDate;

public class GestorFacturas {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String nombre;
    private boolean estado;

    public GestorFacturas(LocalDate inicio, LocalDate fin, String nombre){
        if(inicio == null || fin == null || nombre == null)
            throw new IllegalArgumentException();

        if(nombre.length() < 1 || nombre.length() > 10)
            throw new IllegalArgumentException();

        if(fin.isBefore(inicio))
            throw new IllegalArgumentException();
        




        this.fechaInicio = inicio;
        this.fechaFin = fin;
        this.nombre = nombre;
        this.estado = true;
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
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

}
