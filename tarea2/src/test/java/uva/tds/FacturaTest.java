package uva.tds;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class FacturaTest {

    @Test
    void testFacturaValida(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 5.5);

        assertEquals(f.getAsunto(), "Asunto");
        assertEquals(f.getFecha(), LocalDate.of(2025, 1, 22));
        assertEquals(f.getImporte(), 5.5);
    }

    @Test
    void testFacturaNoValidaAsuntoNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura(null, LocalDate.of(2025, 1, 22), 5.5);
        });
    }

    @Test
    void testFacturaNoValidaFechaNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura("Argumento", null, 5.5);
        });
    }

    @Test
    void testFacturaNoValidaAsuntoMenorLimite(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura("", LocalDate.of(2025, 1, 22), 5.5);
        });
    }

    @Test
    void testFacturaNoValidaImporteMenorLimite(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), -0.9);
        });
    }

}
