/**
 * @author Fernando Carmona Palacio
 */

package uva.tds;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GestorFacturas {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String nombre;
    private boolean estado;
    private ArrayList<Factura> facturas = new ArrayList<Factura>();

    /**
     * Contruye un nuevo objeto GestorFacturas
     * El estado del gestor tras su creación siempre será activo
     * @param inicio un LocalDate con la fecha de inicio de facturación
     * @param fin un LocalDate con la fecha de fin de facturación
     * @param nombre un String con el nombre del gestor
     * @throws IllegalArgumentException si inicio es null
     * @throws IllegalArgumentException si fin es null
     * @throws IllegalArgumentException si nombre es null
     * @throws IllegalArgumentException si nombre es una cadena vacía
     * @throws IllegalArgumentException si nombre tiene más de 10 caracteres
     * @throws IllegalArgumentException si fin es una fecha anterior a inicio
     */
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

    /**
     * Consulta la fecha de inicio de facturación del gestor
     * @return un LocalDate con dicha fecha
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Consulta la fecha de fin de facturación del gestor
     * @return un LocalDate con dicha fecha
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Consulta el nombre del gestor
     * @return un String con dicho nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Consulta el estado del gestor
     * @return true si está activo y false en caso contrario
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * Consulta las facturas que tiene el gestor
     * @return un ArrayList de facturas con dichas facturas
     */
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    /**
     * Consulta las facturas que tiene el gestor por fecha de más antiguo a moderno
     * @return un array de facturas ordenadas por fecha
     */
    public ArrayList<Factura> getFacturasPorFecha() {
        Factura[] aux = this.getFacturas().toArray(new Factura[0]);

        Arrays.sort(aux, Comparator.comparing(Factura::getFecha));

        ArrayList<Factura> fs = new ArrayList<>(Arrays.asList(aux));

        return fs;
    }

    /**
     * Consulta las facturas que tiene el gestor por importe de mayor a menor cantidad
     * @return un array de facturas ordenadas por importe
     */
    public ArrayList<Factura> getFacturasPorImporte() {
        Factura[] aux = this.getFacturas().toArray(new Factura[0]);

        Arrays.sort(aux, Comparator.comparing(Factura::getImporte).reversed());

        ArrayList<Factura> fs = new ArrayList<>(Arrays.asList(aux));

        return fs;
    }   

    /**
     * Establece el estado del gestor
     * @param estado true para activar el gestor y false para desactivarlo
     */
    public void setEstado(boolean estado){
        this.estado = estado;
    }
    
    /**
     * Añade una factura al gestor
     * @param factura la factura a añadir
     * @throws IllegalArgumentException si la factura es null
     * @throws IllegalStateException si el gestor está desactivado
     * @throws IllegalArgumentException si la fecha de la factura es anterior a la de inicio de facturación del gestor
     * @throws IllegalArgumentException si la fecha de la factura es posterior a la de fin de facturación del gestor
     * @throws IllegalArgumentException si la factura dada ya se encuentra en el gestor
     */
    public void agregar(Factura factura){
        if(factura == null)
            throw new IllegalArgumentException();

        if(!getEstado())
            throw new IllegalStateException();

        if(factura.getFecha().isBefore(fechaInicio) || factura.getFecha().isAfter(fechaFin))
            throw new IllegalArgumentException();

        if(facturas.contains(factura))
            throw new IllegalArgumentException();

        facturas.add(factura);
    }

    /**
     * Añade un grupo de facturas al gestor
     * @param facturas un ArrayList de facturas a agregar
     * @throws IllegalArgumentException si alguna factura es null
     * @throws IllegalStateException si el gestor está desactivado
     * @throws IllegalArgumentException si alguna fecha de factura es anterior a la de inicio de facturación del gestor
     * @throws IllegalArgumentException si alguna fecha de factura es posterior a la de fin de facturación del gestor
     * @throws IllegalArgumentException si alguna factura dada ya se encuentra en el gestor
     */
    public void agregar(ArrayList<Factura> facturas){
        for(Factura f : facturas)
            agregar(f);
    }
 
    /**
     * Actualiza la fecha de una factura del gestor
     * @param asunto el asunto de la factura a modificar
     * @param fecha la nueva fecha de la factura
     * @throws IllegalArgumentException si el asunto es null
     * @throws IllegalArgumentException si la fecha es null
     * @throws IllegalArgumentException si no existe una factura con dicho asunto en el gestor
     * @throws IllegalArgumentException si la fecha es anterior a la de inicio de facturación del gestor
     * @throws IllegalArgumentException si la fecha es posterior a la de fin de facturación del gestor
     */
    public void setFecha(String asunto, LocalDate fecha){
        if(asunto == null || fecha == null)
            throw new IllegalArgumentException();

        if(!existeFactura(asunto))
            throw new IllegalArgumentException();

        if(fecha.isBefore(fechaInicio) || fecha.isAfter(fechaFin))
            throw new IllegalArgumentException();

        getFactura(asunto).setFecha(fecha);
    }

    /**
     * Actualiza el importe de una factura del gestor
     * @param asunto el asunto de la factura a modificar
     * @param importe el nuevo importe de la factura
     * @throws IllegalArgumentException si el asunto es null
     * @throws IllegalArgumentException si no existe una factura con dicho asunto en el gestor
     * @throws IllegalArgumentException si el importe es menor que cero
     */
    public void setImporte(String asunto, double importe){
        if(asunto == null)
            throw new IllegalArgumentException();
        
        if(!existeFactura(asunto))
            throw new IllegalArgumentException();
        
        getFactura(asunto).setImporte(importe);
    }


    //Métodos privados
    private Factura getFactura(String asunto) {
        for(Factura e : facturas)
            if(e.getAsunto().equals(asunto))
                return e;

        return null;
    }

    private boolean existeFactura(String asunto){
        for(Factura f : facturas)
            if(f.getAsunto().equals(asunto))
                return true;
        
        return false;
    }
}
