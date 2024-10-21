package uva.tds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class GestorFacturasTest {

    @Test
    void testGestorFacturasValido(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), "Nombre");
        assertEquals(g.getFechaInicio(), LocalDate.of(2024, 12, 22));
        assertEquals(g.getFechaFin(), LocalDate.of(2025, 4, 1));
        assertEquals(g.getNombre(), "Nombre");
        assertEquals(g.getEstado(), true);
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

    @Test
    void testGestorFacturaNoValidoNombreNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), null);
        });
    }

    @Test
    void testGestorFacturaNoValidoNombreMenorLimite(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), "");
        });
    }

    @Test
    void testGestorFacturaNoValidoNombreSuperiorLimite(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), "Aaaaaaaaaaa");
        });
    }

    @Test
    void testGestorFacturaNoValidoFechaFinMenorQueFechaInicio(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2025, 4, 1), LocalDate.of(2024, 12, 22), "Nombre");
        });
    }

    @Test
    void testGestorFacturaCambioEstadoDeTrueAFalse(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 1, 22), "Nombre");
        g.setEstado(false);
        assertEquals(g.getEstado(), false);
    }

    @Test
    void testGestorFacturaCambioEstadoDeFalseATrue(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 1, 22), "Nombre");
        g.setEstado(false);
        g.setEstado(true);
        assertEquals(g.getEstado(), true);
    }

    @Test
    void testGestorFacturaCambioEstadoMantenerEstado(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 1, 22), "Nombre");
        g.setEstado(true);
        assertEquals(g.getEstado(), true);
        g.setEstado(false);
        assertEquals(g.getEstado(), false);
        g.setEstado(false);
        assertEquals(g.getEstado(), false);
    }

    @Test
    void testGestorFacturaAgregarFacturaValida(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 10.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        g.agregar(f);
        assertTrue(g.getFacturas().contains(f));
    }

    @Test
    void testGestorFacturaAgregarFacturaNoValidaFacturaNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
            g.agregar(null);
        });
    }

    @Test
    void testGestorFacturaAgregarFacturaNoValidaFechaAnteriorALimiteGestor(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura("Asunto", LocalDate.of(2022, 1, 22), 10.15);
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
            g.agregar(f);
        });
    }

    @Test
    void testGestorFacturaAgregarFacturaNoValidaFechaPosteriorALimiteGestor(){
        assertThrows(IllegalArgumentException.class, () -> {
            Factura f = new Factura("Asunto", LocalDate.of(2027, 1, 22), 10.15);
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
            g.agregar(f);
        });
    }

}
