
package ar.edu.unahur.obj2.profugos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.profugos.entrenamientos.ArtesMarciales;
import ar.edu.unahur.obj2.profugos.entrenamientos.BaseEntrenamiento;
import ar.edu.unahur.obj2.profugos.entrenamientos.Elite;
import ar.edu.unahur.obj2.profugos.entrenamientos.ProteccionLegal;

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
        IProfugo profugo = new Profugo(false, 150);
        assertEquals(100, profugo.getHabilidad());
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

    @Test
    void profugoEliteNoPuedePonerseNervioso() {
        IProfugo profugo = new Profugo(false, 70);
        profugo = new Elite(profugo);

        assertFalse(profugo.estaNervioso());

        assertThrows(IllegalStateException.class, profugo::ponerNervioso);

        assertFalse(profugo.estaNervioso());
    }

    @Test
    void profugoEliteSeCalmaAlEntrenar() {
        IProfugo profugo = new Profugo(true, 70);
        assertTrue(profugo.estaNervioso());
        profugo = new Elite(profugo);
        assertFalse(profugo.estaNervioso());
    }

    @Test
    void profugoEliteNoPuedeEntrenarOtraVezComoElite() {
        IProfugo profugo = new Elite(new Profugo(false, 70));
        assertThrows(IllegalArgumentException.class, () -> {
            new Elite(profugo);
        });
    }

    @Test
    void profugoDeArtesMarcialesTieneHabilidadDuplicada() {
        IProfugo profugo = new ArtesMarciales(new Profugo(false, 40));
        assertEquals(80, profugo.getHabilidad());
    }

    @Test
    void profugoDeArtesMarcialesNoPuedeTenerHabilidadMayorACien() {
        IProfugo profugo = new Profugo(false, 60);
        profugo = new ArtesMarciales(profugo);
        assertEquals(100, profugo.getHabilidad());
    }

    @Test
    void unProfugoDeArtesMarcialesNoPuedeEntrenarOtraVezArtesMarciales() {
        IProfugo profugo = new ArtesMarciales(new Profugo(false, 70));
        assertThrows(IllegalArgumentException.class, () -> {
            new ArtesMarciales(profugo);
        });
    }

    @Test
    void profugoConProteccionLegalNoPuedeTenerInocenciaMenorA40() {
        IProfugo profugo = new Profugo(false, 70);
        profugo = new ProteccionLegal(profugo);
        assertEquals(100, profugo.getInocencia());
        profugo.reducirInocencia(70);
        assertEquals(40, profugo.getInocencia());
        profugo.aumentarInocencia(10);
        profugo.reducirInocencia(5);
        assertEquals(45, profugo.getInocencia());
    }

    @Test
    void profugoConProteccionLegalNoPuedeTenerInocenciaMenorA40ConReduccionGrande() {
        IProfugo profugo = new Profugo(false, 70);
        profugo = new ProteccionLegal(profugo);
        profugo.reducirInocencia(200);
        assertEquals(40, profugo.getInocencia());
    }

    @Test
    void profugoConProteccionLegalNoPuedeEntrenarOtraVezProteccionLegal() {
        IProfugo profugo = new ProteccionLegal(new Profugo(false, 70));
        assertThrows(IllegalArgumentException.class, () -> {
            new ProteccionLegal(profugo);
        });
    }

    @Test
    void profugoConProteccionLegalEInocenciaMenorA40TieneInocencia40() {
        IProfugo profugo = new Profugo(false, 70);
        profugo.reducirInocencia(70);
        assertEquals(30, profugo.getInocencia());
        profugo = new ProteccionLegal(profugo);
        assertEquals(40, profugo.getInocencia());
    }

    @Test
    void calmarNoHaceNadaEnProfugoElite() {
        IProfugo profugo = new Profugo(true, 70);
        profugo = new Elite(profugo);
        assertFalse(profugo.estaNervioso());
        profugo.calmar();
        assertFalse(profugo.estaNervioso());
    }

    @Test
    void baseEntrenamientoPuedeGestionarNerviosismoYHabilidad() {
        IProfugo profugo = new Profugo(true, 50);
        IProfugo base = new BaseEntrenamiento(profugo);
        assertTrue(base.estaNervioso());
        base.calmar();
        assertFalse(base.estaNervioso());
        base.ponerNervioso();
        assertTrue(base.estaNervioso());
        assertEquals(50, base.getHabilidad());
        base.aumentarHabilidad(30);
        assertEquals(80, base.getHabilidad());
        base.disminuirHabilidad(90);
        assertEquals(0, base.getHabilidad());
    }
}