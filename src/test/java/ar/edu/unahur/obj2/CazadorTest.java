package ar.edu.unahur.obj2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.cazadores.Cazador;
import ar.edu.unahur.obj2.cazadores.ICazador;
import ar.edu.unahur.obj2.cazadores.tiposcazadores.CazadorRural;
import ar.edu.unahur.obj2.cazadores.tiposcazadores.CazadorSigiloso;
import ar.edu.unahur.obj2.cazadores.tiposcazadores.CazadorUrbano;
import ar.edu.unahur.obj2.profugos.Profugo;
import ar.edu.unahur.obj2.zonas.Zona;

class CazadorTest {
    Cazador cazadorRamboUrbano = new Cazador(CazadorUrbano.getInstancia());
    Cazador cazadorClintEastwoodSigiloso = new Cazador(CazadorSigiloso.getInstancia());
    Cazador cazadorJohnDoeRural = new Cazador(CazadorRural.getInstancia());
    Profugo arthurMorgan = new Profugo(false, 100);
    Profugo johnMarston = new Profugo(true, 50);
    Zona rioHudson = new Zona("Rio Hudson");

    @Test
    void testConstructor() {
        Cazador cazador = new Cazador(CazadorSigiloso.getInstancia());
        assertTrue(cazador instanceof ICazador);
        assertTrue(cazador.getTipoCazador() instanceof CazadorSigiloso);
        assertNull(cazador.getZona());
        Cazador cazadorConZona = new Cazador(CazadorUrbano.getInstancia(), rioHudson);
        assertEquals(rioHudson, cazadorConZona.getZona());
        assertTrue(cazadorConZona.getTipoCazador() instanceof CazadorUrbano);
    }

    @BeforeEach
    void init() {

        rioHudson.agregarProfugo(arthurMorgan);
        rioHudson.agregarProfugo(johnMarston);
    }

    @Test
    void todoCazadorTieneTipo() {
        assertTrue(cazadorRamboUrbano.getTipoCazador() instanceof CazadorUrbano);
        assertTrue(cazadorClintEastwoodSigiloso.getTipoCazador() instanceof CazadorSigiloso);
        assertTrue(cazadorJohnDoeRural.getTipoCazador() instanceof CazadorRural);
    }

    @Test
    void todoCazadorEmpiezaConExperienciaCero() {
        assertEquals(0, cazadorRamboUrbano.getExperiencia());
        assertEquals(0, cazadorClintEastwoodSigiloso.getExperiencia());
        assertEquals(0, cazadorJohnDoeRural.getExperiencia());
    }

    @Test
    void ningunCazadorTieneZonaAlInicio() {
        assertNull(cazadorRamboUrbano.getZona());
        assertNull(cazadorClintEastwoodSigiloso.getZona());
        assertNull(cazadorJohnDoeRural.getZona());
    }

    @Test
    void ningunCazadorPuedeCapturarSinZona() {
        assertThrows(IllegalStateException.class, () -> cazadorRamboUrbano.intentarCapturar(arthurMorgan));
        assertThrows(IllegalStateException.class, () -> cazadorClintEastwoodSigiloso.intentarCapturar(arthurMorgan));
        assertThrows(IllegalStateException.class, () -> cazadorJohnDoeRural.intentarCapturar(arthurMorgan));
    }

    @Test
    void comisariaAsignaATodosElRioHudson() {
        cazadorRamboUrbano.asignarZona(rioHudson);
        cazadorClintEastwoodSigiloso.asignarZona(rioHudson);
        cazadorJohnDoeRural.asignarZona(rioHudson);
        assertEquals(rioHudson, cazadorRamboUrbano.getZona());
        assertEquals(rioHudson, cazadorClintEastwoodSigiloso.getZona());
        assertEquals(rioHudson, cazadorJohnDoeRural.getZona());
    }

    @Test
    void nadiePuedeCapturarAArthurMorgan() {
        assertFalse(cazadorClintEastwoodSigiloso.puedeCapturar(arthurMorgan));
        assertFalse(cazadorRamboUrbano.puedeCapturar(arthurMorgan));
        assertFalse(cazadorJohnDoeRural.puedeCapturar(arthurMorgan));
    }

    @Test
    void ningunCazadorRuralPodriaCapturarAArthurMorganPeroSiAJohn() {
        assertFalse(CazadorRural.getInstancia().condicionDeCapturaEspecifica(arthurMorgan));
        assertTrue(CazadorRural.getInstancia().condicionDeCapturaEspecifica(johnMarston));
    }

    @Test
    void unCazadorUrbanoPodriaCapturarAArthurMorganPeroNoAJohnMarston() {
        assertTrue(CazadorUrbano.getInstancia().condicionDeCapturaEspecifica(arthurMorgan));
        assertFalse(CazadorUrbano.getInstancia().condicionDeCapturaEspecifica(johnMarston));
    }

    @Test
    void ningunCazadorSigilosoPodriaCapturarAArthurMorganNiAJohnMarston() {
        assertFalse(CazadorSigiloso.getInstancia().condicionDeCapturaEspecifica(arthurMorgan));
        assertFalse(CazadorSigiloso.getInstancia().condicionDeCapturaEspecifica(johnMarston));
    }

    @Test
    void cazadorRuralHacePatrullajeAumenta98DeExperienciaYPoneNerviososALosProfugos() {
        cazadorJohnDoeRural.asignarZona(rioHudson);
        cazadorJohnDoeRural.hacerPatrullaje();
        assertEquals(98, cazadorJohnDoeRural.getExperiencia());
        assertTrue(arthurMorgan.estaNervioso());
        assertTrue(johnMarston.estaNervioso());
    }

    @Test
    void cazadorUrbanoHacePatrullajeAumenta98DeExperienciaYDisminuyeHabilidadDeLosProfugos() {
        cazadorRamboUrbano.asignarZona(rioHudson);
        cazadorRamboUrbano.hacerPatrullaje();
        assertEquals(98, cazadorRamboUrbano.getExperiencia());
        assertEquals(95, arthurMorgan.getHabilidad());
        assertEquals(45, johnMarston.getHabilidad());
    }

    @Test
    void cazadorSigilosoHacePatrullajeAumenta98DeExperienciaYCalmaALosProfugos() {
        cazadorClintEastwoodSigiloso.asignarZona(rioHudson);
        cazadorClintEastwoodSigiloso.hacerPatrullaje();
        assertEquals(98, cazadorClintEastwoodSigiloso.getExperiencia());
        assertFalse(arthurMorgan.estaNervioso());
        assertFalse(johnMarston.estaNervioso());
    }

    @Test
    void cazadorSigilosoConMuchaExperienciaCapturaAJohnMarston() {
        cazadorClintEastwoodSigiloso.asignarZona(rioHudson);
        cazadorClintEastwoodSigiloso.aumentarExperiencia(900);
        assertEquals(900, cazadorClintEastwoodSigiloso.getExperiencia());
        johnMarston.reducirInocencia(90);
        assertEquals(10, johnMarston.getInocencia());
        assertTrue(cazadorClintEastwoodSigiloso.puedeCapturar(johnMarston));
        cazadorClintEastwoodSigiloso.hacerPatrullaje();
        assertEquals(1, cazadorClintEastwoodSigiloso.getProfugosCapturados());
        assertEquals(1, rioHudson.getProfugos().size());
        assertEquals(1000, cazadorClintEastwoodSigiloso.getExperiencia());
    }

    @Test
    void cazadorUrbanoConMuchaExperienciaCapturaAArthurMorgan() {
        cazadorRamboUrbano.asignarZona(rioHudson);
        cazadorRamboUrbano.aumentarExperiencia(900);
        assertEquals(900, cazadorRamboUrbano.getExperiencia());
        arthurMorgan.reducirInocencia(90);
        assertEquals(10, arthurMorgan.getInocencia());
        assertTrue(cazadorRamboUrbano.puedeCapturar(arthurMorgan));
        cazadorRamboUrbano.hacerPatrullaje();
        assertEquals(1, cazadorRamboUrbano.getProfugosCapturados());
        assertEquals(1, rioHudson.getProfugos().size());
        assertEquals(1000, cazadorRamboUrbano.getExperiencia());
    }

    @Test
    void cazadorRuralConMuchaExperienciaCapturaAJohnMarston() {
        cazadorJohnDoeRural.asignarZona(rioHudson);
        cazadorJohnDoeRural.aumentarExperiencia(900);
        assertEquals(900, cazadorJohnDoeRural.getExperiencia());
        johnMarston.reducirInocencia(90);
        assertEquals(10, johnMarston.getInocencia());
        assertTrue(cazadorJohnDoeRural.puedeCapturar(johnMarston));
        cazadorJohnDoeRural.hacerPatrullaje();
        assertEquals(1, cazadorJohnDoeRural.getProfugosCapturados());
        assertEquals(1, rioHudson.getProfugos().size());
        assertEquals(1000, cazadorJohnDoeRural.getExperiencia());
    }
}
