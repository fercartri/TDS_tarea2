package uva.tds;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class FacturaTest {

    //Constructor
    @Test
    void testFacturaCreacionFacturaValida(){
        Factura f = new Factura("A", LocalDate.of(2025, 1, 22), 0);

        assertEquals(f.getAsunto(), "A");
        assertEquals(f.getFecha(), LocalDate.of(2025, 1, 22));
        assertEquals(f.getImporte(), 0);
    }

    @Test
    void testFacturaCreacionFacturaNoValidaAsuntoNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura(null, LocalDate.of(2025, 1, 22), 5.5);
        });
    }

    @Test
    void testFacturaCreacionFacturaNoValidaFechaNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura("Asunto", null, 5.5);
        });
    }

    @Test
    void testFacturaCreacionFacturaNoValidaAsuntoMenorLimite(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura("", LocalDate.of(2025, 1, 22), 5.5);
        });
    }

    @Test
    void testFacturaCreacionFacturaNoValidaImporteMenorLimite(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), -0.01);
        });
    }

    
    //setFecha
    @Test
    void testFacturaSetFechaConFechaValida(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 5.5);

        f.setFecha(LocalDate.of(2025, 2, 22));

        assertEquals(f.getFecha(), LocalDate.of(2025, 2, 22));
    }

    @Test
    void testFacturaSetFechaConFechaNull(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 5.5);

        assertThrows(IllegalArgumentException.class, () -> {
            f.setFecha(null);
        });
    }


    //setImporte
    @Test
    void testFacturaSetImporteConImporteValido(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 5.5);

        f.setImporte(0);

        assertEquals(f.getImporte(), 0);
    }

    @Test
    void testFacturaSetImporteConImporteMenorACero(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 5.5);

        assertThrows(IllegalArgumentException.class, () -> {
            f.setImporte(-0.1);
        });
    }
}
