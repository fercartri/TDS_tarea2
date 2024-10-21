package uva.tds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class GestorFacturasTest {

    @Test
    void testGestorFacturasValido(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), "Nombre");
        assertEquals(g.getFechaInicio(), LocalDate.of(2024, 12, 22));
        assertEquals(g.getFechaFin(), LocalDate.of(2025, 4, 1));
        assertEquals(g.getNombre(), "Nombre");
    }

    @Test
    void testGestorFacturaNoValidoFechaInicioNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(null, LocalDate.of(2025, 4, 1), "Nombre");
        });
    }

    @Test
    void testGestorFacturaNoValidoFechaFinNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), null, "Nombre");
        });
    }


}
