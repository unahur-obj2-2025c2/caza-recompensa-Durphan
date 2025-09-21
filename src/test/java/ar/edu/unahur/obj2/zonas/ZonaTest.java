package ar.edu.unahur.obj2.zonas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.profugos.Profugo;

class ZonaTest {
    Profugo bill = new Profugo(false, 70);
    Profugo dutch = new Profugo(true, 40);
    Profugo micah = new Profugo(false, 90);

    @Test
    void testConstructor() {
        Zona zona = new Zona("Zona 1");
        assertEquals("Zona 1", zona.getNombre());
        assertTrue(zona.getProfugos().isEmpty());
        Zona zonaConProfugos = new Zona("Zona 2", List.of(new Profugo(false, 50), new Profugo(true, 30)));
        assertEquals("Zona 2", zonaConProfugos.getNombre());
        assertEquals(2, zonaConProfugos.getProfugos().size());
    }

    @Test
    void testAgregarProfugo() {
        Zona zona = new Zona("Zona 1");
        zona.agregarProfugo(bill);
        assertEquals(1, zona.getProfugos().size());
        assertTrue(zona.getProfugos().contains(bill));
    }

    @Test
    void testEliminarProfugo() {
        Zona zona = new Zona("Zona 1");
        zona.agregarProfugo(bill);
        zona.eliminarProfugo(bill);
        assertEquals(0, zona.getProfugos().size());
        assertFalse(zona.getProfugos().contains(bill));
    }

    @Test
    void testGetNombre() {
        Zona zona = new Zona("Zona 1");
        assertEquals("Zona 1", zona.getNombre());

    }

    @Test
    void testGetProfugos() {
        Zona zona = new Zona("Zona 1");
        zona.agregarProfugo(bill);
        zona.agregarProfugo(dutch);
        assertEquals(2, zona.getProfugos().size());
        assertTrue(zona.getProfugos().contains(bill));
        assertTrue(zona.getProfugos().contains(dutch));
        assertFalse(zona.getProfugos().contains(micah));

    }

    @Test
    void testLimpiarProfugos() {
        Zona zona = new Zona("Zona 1");
        zona.agregarProfugo(bill);
        zona.agregarProfugo(dutch);
        zona.limpiarProfugos();
        assertTrue(zona.getProfugos().isEmpty());

    }
}
