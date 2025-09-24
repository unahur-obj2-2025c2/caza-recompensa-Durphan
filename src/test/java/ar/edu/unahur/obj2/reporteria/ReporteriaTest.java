package ar.edu.unahur.obj2.reporteria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.Reporteria;
import ar.edu.unahur.obj2.cazadores.Cazador;
import ar.edu.unahur.obj2.cazadores.ICazador;
import ar.edu.unahur.obj2.cazadores.tiposcazadores.CazadorRural;
import ar.edu.unahur.obj2.profugos.IProfugo;
import ar.edu.unahur.obj2.profugos.Profugo;
import ar.edu.unahur.obj2.zonas.Zona;

class ReporteriaTest {

    @BeforeEach
    void init() {
        Reporteria.getCazadores().clear();
    }

    Zona zona = new Zona("Zona 51");

    @Test
    void cuandoSeCreaUnCazadorSeRegistraEnLaReporteria() {
        ICazador cazador = new Cazador(CazadorRural.getInstancia());
        assertTrue(Reporteria.getCazadores().contains(cazador));
    }

    @Test
    void siDosCazadoresCapturanUnProfugoCadaUnoAmbosAparecenEnLaListaDeCapturados() {
        ICazador cazador1 = new Cazador(CazadorRural.getInstancia(), zona, 100);
        ICazador cazador2 = new Cazador(CazadorRural.getInstancia(), zona, 100);
        IProfugo profugo = new Profugo(true, 0);
        IProfugo profugo2 = new Profugo(false, 0);
        zona.agregarProfugo(profugo);
        zona.agregarProfugo(profugo2);
        profugo.reducirInocencia(70);
        profugo2.reducirInocencia(70);
        cazador1.hacerPatrullaje();
        cazador2.hacerPatrullaje();
        assertEquals(1, cazador1.getProfugosCapturados().size());
        assertEquals(1, cazador2.getProfugosCapturados().size());
        assertEquals(2, Reporteria.profugosCapturados().size());
    }

    @Test
    void noSePuedenObtenerProfugosCapturadosSiCazadores() {
        assertEquals(0, Reporteria.profugosCapturados().size());
    }

    @Test
    void siUnCazadorCapturaMasQueOtroEsElCazadorConMasCapturas() {
        ICazador cazador1 = new Cazador(CazadorRural.getInstancia(), zona, 100);
        ICazador cazador2 = new Cazador(CazadorRural.getInstancia(), zona, 100);
        IProfugo profugo = new Profugo(true, 0);
        IProfugo profugo2 = new Profugo(false, 0);
        IProfugo profugo3 = new Profugo(true, 0);
        zona.agregarProfugo(profugo);
        zona.agregarProfugo(profugo2);
        zona.agregarProfugo(profugo3);
        profugo.reducirInocencia(70);
        profugo2.reducirInocencia(70);
        profugo3.reducirInocencia(70);
        cazador1.hacerPatrullaje();
        cazador2.hacerPatrullaje();
        assertEquals(2, cazador1.getProfugosCapturados().size());
        assertEquals(1, cazador2.getProfugosCapturados().size());
        assertEquals(cazador1, Reporteria.cazadorConMasCapturas());
    }

    @Test
    void siNoHayCazadoresNoSePuedeSaberCualTieneMasCapturas() {
        assertThrows(IllegalStateException.class, Reporteria::cazadorConMasCapturas);
    }

    @Test
    void siUnCazadorTieneUnProfugoMasHabilidosoQueOtroCapturadoElMasHabilidosoEsElQueCuenta() {
        ICazador cazador1 = new Cazador(CazadorRural.getInstancia(), zona, 100);
        ICazador cazador2 = new Cazador(CazadorRural.getInstancia(), zona, 100);
        IProfugo profugo = new Profugo(true, 50);
        IProfugo profugo2 = new Profugo(false, 70);
        IProfugo profugo3 = new Profugo(true, 30);
        zona.agregarProfugo(profugo);
        zona.agregarProfugo(profugo2);
        zona.agregarProfugo(profugo3);
        profugo.reducirInocencia(70);
        profugo2.reducirInocencia(70);
        profugo3.reducirInocencia(70);
        cazador1.hacerPatrullaje();
        cazador2.hacerPatrullaje();
        assertEquals(2, cazador1.getProfugosCapturados().size());
        assertEquals(1, cazador2.getProfugosCapturados().size());
        assertEquals(profugo2, Reporteria.profugoMasHabilCapturado());
    }

    @Test
    void siNingunCazadorCapturoNingunProfugoNoSePuedeSaberCualEsElMasHabilidoso() {
        @SuppressWarnings("unused")
        ICazador cazador1 = new Cazador(CazadorRural.getInstancia(), zona, 100);
        @SuppressWarnings("unused")
        ICazador cazador2 = new Cazador(CazadorRural.getInstancia(), zona, 100);
        assertThrows(IllegalArgumentException.class, Reporteria::profugoMasHabilCapturado);
    }

}
