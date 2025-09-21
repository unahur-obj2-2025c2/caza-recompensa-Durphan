
package ar.edu.unahur.obj2.profugos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ProfugoTest {

    @Test
    void testConstructorValoresValidos() {
        Profugo profugo = new Profugo(false, 50);
        assertEquals(100, profugo.getInocencia());
        assertEquals(50, profugo.getHabilidad());
        assertFalse(profugo.estaNervioso());
    }

    @Test
    void testConstructorHabilidadMenorACero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Profugo(true, -1);
        });
    }

    @Test
    void testConstructorHabilidadMayorACien() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Profugo(false, 101);
        });
    }

    @Test
    void testReducirInocenciaNormal() {
        Profugo profugo = new Profugo(false, 30);
        profugo.reducirInocencia(20);
        assertEquals(80, profugo.getInocencia());
    }

    @Test
    void testReducirInocenciaNoNegativo() {
        Profugo profugo = new Profugo(false, 30);
        profugo.reducirInocencia(150);
        assertEquals(0, profugo.getInocencia());
    }

    @Test
    void testAumentarInocencia() {
        Profugo profugo = new Profugo(false, 30);
        profugo.aumentarInocencia(15);
        assertEquals(115, profugo.getInocencia());
    }

    @Test
    void testDisminuirHabilidadNormal() {
        Profugo profugo = new Profugo(false, 40);
        profugo.disminuirHabilidad(10);
        assertEquals(30, profugo.getHabilidad());
    }

    @Test
    void testDisminuirHabilidadNoNegativo() {
        Profugo profugo = new Profugo(false, 10);
        profugo.disminuirHabilidad(20);
        assertEquals(0, profugo.getHabilidad());
    }

    @Test
    void testAumentarHabilidadNormal() {
        Profugo profugo = new Profugo(false, 60);
        profugo.aumentarHabilidad(20);
        assertEquals(80, profugo.getHabilidad());
    }

    @Test
    void testAumentarHabilidadNoMayorACien() {
        Profugo profugo = new Profugo(false, 90);
        profugo.aumentarHabilidad(20);
        assertEquals(100, profugo.getHabilidad());
    }

    @Test
    void testPonerNerviosoYCalmar() {
        Profugo profugo = new Profugo(false, 50);
        assertFalse(profugo.estaNervioso());
        profugo.ponerNervioso();
        assertTrue(profugo.estaNervioso());
        profugo.calmar();
        assertFalse(profugo.estaNervioso());
    }
}