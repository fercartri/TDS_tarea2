package uva.tds;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestorFacturas {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String nombre;
    private boolean estado;
    private ArrayList<Factura> facturas = new ArrayList<Factura>();

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
    public ArrayList<Factura> getFacturas() {
        ArrayList<Factura> f = new ArrayList<Factura>();

        for(Factura e : facturas)
            f.add(e);

        return f;
    }

    public void setEstado(boolean estado){
        this.estado = estado;
    }

    public void agregar(Factura f){
        if(f == null)
            throw new IllegalArgumentException();

        if(f.getFecha().isBefore(fechaInicio) || f.getFecha().isAfter(fechaFin))
            throw new IllegalArgumentException();

        facturas.add(f);
    }

    public void agregar(ArrayList<Factura> fs){
        for(Factura f : fs)
            agregar(f);
    }

}
