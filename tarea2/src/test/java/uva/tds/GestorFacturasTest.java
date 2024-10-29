/**
 * @author Fernando Carmona Palacio
 */

package uva.tds;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class GestorFacturasTest {

    //Constructor
    @Test
    void testGestorFacturasCreacionValida(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), "Nombre");
        
        assertEquals(g.getFechaInicio(), LocalDate.of(2024, 12, 22));
        assertEquals(g.getFechaFin(), LocalDate.of(2025, 4, 1));
        assertEquals(g.getNombre(), "Nombre");
        assertTrue(g.getEstado());
    }

    @Test
    void testGestorFacturaCreacionNoValidaFechaInicioNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(null, LocalDate.of(2025, 4, 1), "Nombre");
        });
    }

    @Test
    void testGestorFacturaCreacionNoValidaFechaFinNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), null, "Nombre");
        });
    }

    @Test
    void testGestorFacturaCreacionNoValidaNombreNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), null);
        });
    }

    @Test
    void testGestorFacturaCreacionNoValidaNombreMenorLimite(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), "");
        });
    }

    @Test
    void testGestorFacturaCreacionNoValidaNombreSuperiorLimite(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 1), "Aaaaaaaaaaa");
        });
    }

    @Test
    void testGestorFacturaCreacionNoValidaFechaFinMenorQueFechaInicio(){
        assertThrows(IllegalArgumentException.class, () -> {
            GestorFacturas g = new GestorFacturas(LocalDate.of(2025, 4, 1), LocalDate.of(2024, 12, 22), "Nombre");
        });
    }


    //getFacturasPorFecha
    @Test
    void testGestorFacturaGetFacturasPorFecha(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 2, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 3, 22), 10.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 10.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f3);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        g.agregar(fs);

        ArrayList<Factura> ordenado = g.getFacturasPorFecha();

        assertEquals(ordenado.get(0), f3);
        assertEquals(ordenado.get(1), f1);
        assertEquals(ordenado.get(2), f2);
    }


    //getFacturasPorImporte
    @Test
    void testGestorFacturaGetFacturasPorImporte(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 2, 22), 2.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 3, 22), 0.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 1.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f3);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        g.agregar(fs);

        ArrayList<Factura> ordenado = g.getFacturasPorImporte();

        assertEquals(ordenado.get(0), f1);
        assertEquals(ordenado.get(1), f3);
        assertEquals(ordenado.get(2), f2);
    }


    //setEstado
    @Test
    void testGestorFacturaSetEstadoDeTrueAFalseYDeFalseATrueYManteniendoEstado(){
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 1, 22), "Nombre");
        
        assertTrue(g.getEstado());
        g.setEstado(false);
        assertFalse(g.getEstado());
        g.setEstado(true);
        assertTrue(g.getEstado());
        g.setEstado(true);
        assertTrue(g.getEstado());
        g.setEstado(false);
        g.setEstado(false);
        assertFalse(g.getEstado());
    }


    //agregar

        //Una factura
    @Test
    void testGestorFacturaAgregarFacturaValida(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 1, 22), 10.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);
        
        assertTrue(g.getFacturas().contains(f));
    }

    @Test
    void testGestorFacturaAgregarFacturaNoValidaGestorCerrado(){
        Factura f = new Factura("Asunto", LocalDate.of(2022, 1, 22), 10.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.setEstado(false);
        
        assertThrows(IllegalStateException.class, () -> {
            g.agregar(f);
        });
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

        //Varias facturas
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
    void testGestorFacturaAgregarMultiplesFacturasNoValidaGestorCerrado(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 1, 22), 10.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 10.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f3);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        g.setEstado(false);

        assertThrows(IllegalStateException.class, () -> {
            g.agregar(fs);
        });
    }

    @Test
    void testGestorFacturaAgregarMultiplesFacturasNoValidaFechaAnteriorALimiteGestor(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2022, 1, 22), 10.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 10.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f3);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");

        assertThrows(IllegalArgumentException.class, () -> {
            g.agregar(fs);
        });
    }

    @Test
    void testGestorFacturaAgregarMultiplesFacturasNoValidaFechaPosteriorALimiteGestor(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2026, 1, 22), 10.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 10.15);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f3);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");

        assertThrows(IllegalArgumentException.class, () -> {
            g.agregar(fs);
        });
    }

    @Test
    void testGestorFacturaAgregarMultiplesFacturasNoValidaFacturaRepetidaEnArray(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 1, 22), 10.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 10.15);
        Factura f4 = new Factura("Asunto2", LocalDate.of(2025, 1, 23), 1);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f2);
        fs.add(f3);
        fs.add(f4);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");

        assertThrows(IllegalArgumentException.class, () -> {
            g.agregar(fs);
        });
    }

    @Test
    void testGestorFacturaAgregarMultiplesFacturasNoValidaFacturaRepetidaYaIncluida(){
        Factura f1 = new Factura("Asunto1", LocalDate.of(2025, 1, 22), 10.15);
        Factura f2 = new Factura("Asunto2", LocalDate.of(2025, 1, 22), 10.15);
        Factura f3 = new Factura("Asunto3", LocalDate.of(2025, 1, 22), 10.15);
        Factura f4 = new Factura("Asunto2", LocalDate.of(2025, 1, 23), 1);

        ArrayList<Factura> fs = new ArrayList<Factura>();
        fs.add(f1);
        fs.add(f3);
        fs.add(f4);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");

        g.agregar(f2);

        assertThrows(IllegalArgumentException.class, () -> {
            g.agregar(fs);
        });
    }


    //setFecha
    @Test
    void testGestorFacturaSetFechaValidoGestorAbiertoYCerrado(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        g.setFecha("Asunto", LocalDate.of(2025, 1, 22));
        assertEquals(g.getFacturas().get(g.getFacturas().indexOf(f)).getFecha(), LocalDate.of(2025, 1, 22));
        g.setEstado(false);
        g.setFecha("Asunto", LocalDate.of(2025, 2, 22));
        assertEquals(g.getFacturas().get(g.getFacturas().indexOf(f)).getFecha(), LocalDate.of(2025, 2, 22));
    }

    @Test
    void testGestorFacturaSetFechaNoValidaAsuntoNull(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setFecha(null, LocalDate.of(2025, 1, 1));
        });
    }

    @Test
    void testGestorFacturaSetFechaNoValidaFechaNull(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setFecha("Asunto", null);
        });
    }

    @Test
    void testGestorFacturaSetFechaNoValidaAsuntoNoExiste(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setFecha("Malo", LocalDate.of(2025, 1, 1));
        });
    }

    @Test
    void testGestorFacturaSetFechaNoValidaMenorLimiteGestor(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setFecha("Asunto", LocalDate.of(2023, 1, 1));
        });
    }

    @Test
    void testGestorFacturaSetFechaNoValidaMayorLimiteGestor(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);

        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setFecha("Asunto", LocalDate.of(2026, 1, 1));
        });
    }


    //setImporte
    @Test
    void testGestorFacturaSetImporteValidoGestorAbiertoYCerrado(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        g.setImporte("Asunto", 0);
        assertEquals(g.getFacturas().get(g.getFacturas().indexOf(f)).getImporte(), 0);
        g.setEstado(false);
        g.setImporte("Asunto", 10.50);
        assertEquals(g.getFacturas().get(g.getFacturas().indexOf(f)).getImporte(), 10.50);
    }

    @Test
    void testGestorFacturaSetImporteNoValidaAsuntoNull(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setImporte(null, 0);
        });
    }

    @Test
    void testGestorFacturaSetImporteNoValidaAsuntoNoExiste(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setImporte("Malo", 0);
        });
    }

    @Test
    void testGestorFacturaSetImporteNoValidaMenorACero(){
        Factura f = new Factura("Asunto", LocalDate.of(2025, 3, 22), 0.15);
        GestorFacturas g = new GestorFacturas(LocalDate.of(2024, 12, 22), LocalDate.of(2025, 4, 22), "Nombre");
        
        g.agregar(f);

        assertThrows(IllegalArgumentException.class, () -> {
            g.setImporte("Asunto", -0.1);
        });
    }
}
