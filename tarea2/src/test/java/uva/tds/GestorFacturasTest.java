package uva.tds;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class GestorFacturasTest {

    @Test
    void testGestorFacturasValido(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), "Nombre");
        assertEquals(g.getFechaInicio(), LocalDate.of(2024, 12, 22));
        assertEquals(g.getFechaFin(), LocalDate.of(2025, 4, 1));
        assertEquals(g.getNombre(), "Nombre");
        assertTrue(g.getEstado());
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
        assertFalse(g.getEstado());
    }

    @Test
    void testGestorFacturaCambioEstadoDeFalseATrue(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 1, 22), "Nombre");
        g.setEstado(false);
        g.setEstado(true);
        assertTrue(g.getEstado());
    }

    @Test
    void testGestorFacturaCambioEstadoMantenerEstado(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 1, 22), "Nombre");
        g.setEstado(true);
        assertTrue(g.getEstado());
        g.setEstado(false);
        assertFalse(g.getEstado());
        g.setEstado(false);
        assertFalse(g.getEstado());
    }

    @Test
    void testGestorFacturaAgregarFacturaValida(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 10.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        g.agregar(f);
        assertTrue(g.getFacturas().contains(f));
    }

    @Test
    void testGestorFacturaAgregarFacturaNoValidaFechaAnteriorALimiteGestor(){
        Factura f = new Factura("Asunto", LocalDate.of(2022, 1, 22), 10.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        assertThrows(IllegalArgumentException.class, () -> {
            g.agregar(f);
        });
    }

    @Test
    void testGestorFacturaAgregarFacturaNoValidaFechaPosteriorALimiteGestor(){
        Factura f = new Factura("Asunto", LocalDate.of(2027, 1, 22), 10.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        assertThrows(IllegalArgumentException.class, () -> {
            g.agregar(f);
        });
    }

    @Test
    void testGestorFacturaAgregarMultiplesFacturasValidas(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 1, 22), 10.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 10.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f3);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");

        g.agregar(fs);
        assertTrue(g.getFacturas().contains(f1));
        assertTrue(g.getFacturas().contains(f2));
        assertTrue(g.getFacturas().contains(f3));
    }

    @Test
    void testGestorFacturaAgregarMultiplesFacturasNoValidaFacturaNull(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 1, 22), 10.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(null);
        fs.add(f2);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");

        assertThrows(IllegalArgumentException.class, () -> {
            g.agregar(fs);
        });
    }

    @Test
    void testGestorFacturaAgregarFacturaNoValidaFacturaRepetida(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 1, 22), 10.15);
        Factura f3 = new Factura("Asunto2", LocalDate.of(2025, 1, 23), 11.15);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");

        g.agregar(f1);
        g.agregar(f2);

        assertThrows(IllegalArgumentException.class, () -> {
            g.agregar(f3);
        });
    }

    @Test
    void testGestorFacturaAgregarMultiplesFacturasNoValidaFacturaRepetidaEnArray(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 1, 22), 10.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f2);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");

        assertThrows(IllegalArgumentException.class, () -> {
            g.agregar(fs);
        });
    }

    @Test
    void testGestorFacturaAgregarFacturaNoValidoGestorCerrado(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        g.setEstado(false);

        assertThrows(IllegalStateException.class, () -> {
            g.agregar(f1);
        });
    }

    @Test
    void testGestorFacturaAgregarMultiplesFacturasNoValidoGestorCerrado(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 1, 22), 10.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        g.setEstado(false);

        assertThrows(IllegalStateException.class, () -> {
            g.agregar(fs);
        });
    }

    @Test
    void testGestorFacturaListaDeFacturasPorFecha(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 2, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 3, 22), 10.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 10.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f3);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(fs);

        Factura[] ordenado = g.getFacturasPorFecha();

        assertEquals(ordenado[0], f3);
        assertEquals(ordenado[1], f1);
        assertEquals(ordenado[2], f2);
    }

    @Test
    void testGestorFacturaListaDeFacturasPorImporte(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 2, 22), 2.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 3, 22), 0.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 1.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f3);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(fs);

        Factura[] ordenado = g.getFacturasPorImporte();

        assertEquals(ordenado[0], f1);
        assertEquals(ordenado[1], f3);
        assertEquals(ordenado[2], f2);
    }


    @Test
    void testGestorFacturaActualizarFechaEImporteValidosEnGestorAbiertoYCerrado(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        g.setFecha("Asunto", LocalDate.of(2025, 1, 22));
        g.setImporte("Asunto", 0);

        assertEquals(g.getFacturas().get(g.getFacturas().indexOf(f)).getFecha(), LocalDate.of(2025, 1, 22));
        assertEquals(g.getFacturas().get(g.getFacturas().indexOf(f)).getImporte(), 0);

        g.setEstado(false);

        g.setFecha("Asunto", LocalDate.of(2025, 2, 22));
        g.setImporte("Asunto", 10.50);

        assertEquals(g.getFacturas().get(g.getFacturas().indexOf(f)).getFecha(), LocalDate.of(2025, 2, 22));
        assertEquals(g.getFacturas().get(g.getFacturas().indexOf(f)).getImporte(), 10.50);
    }

    @Test
    void testGestorFacturaActualizarFechaNoValidaMenorLimiteGestor(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setFecha("Asunto", LocalDate.of(2023, 1, 1));
        });
    }

    @Test
    void testGestorFacturaActualizarFechaNoValidaMayorLimiteGestor(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setFecha("Asunto", LocalDate.of(2026, 1, 1));
        });
    }

    @Test
    void testGestorFacturaActualizarFechaNoValidaNull(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setFecha("Asunto", null);
        });
    }

    //Falta comprobra que no exista factura con asunto dado ademas de las comprobaciones de fecha e importe
}
