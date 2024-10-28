package uva.tds;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class FacturaTest {

    @Test
    void testFacturaCreacionFacturaValida(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 5.5);

        assertEquals(f.getAsunto(), "Asunto");
        assertEquals(f.getFecha(), LocalDate.of(2025, 1, 22));
        assertEquals(f.getImporte(), 5.5);
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
            Factura f = new Factura("Argumento", null, 5.5);
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
            Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), -0.9);
        });
    }

    @Test
    void testFacturaSetFechaConFechaValida(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 5.5);

        f.setFecha(LocalDate.of(2025, 2, 22));

        assertEquals(f.getFecha(), LocalDate.of(2025, 2, 22));
    }

}
