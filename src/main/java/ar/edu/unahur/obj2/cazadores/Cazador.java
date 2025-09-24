package ar.edu.unahur.obj2.cazadores;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.Reporteria;
import ar.edu.unahur.obj2.cazadores.tiposcazadores.ITipoCazador;
import ar.edu.unahur.obj2.profugos.IProfugo;
import ar.edu.unahur.obj2.zonas.IZona;

public class Cazador implements ICazador {
    private IZona zona;
    private int experiencia = 0;
    private List<IProfugo> profugosCapturados = new ArrayList<>();
    private final ITipoCazador tipoCazador;
    private List<IProfugo> profugosIntimidados = new ArrayList<>();

    public Cazador(ITipoCazador tipoCazador, IZona zona) {
        this.tipoCazador = tipoCazador;
        this.zona = zona;
        Reporteria.agregarCazador(this);
    }

    public Cazador(ITipoCazador tipoCazador) {
        this.tipoCazador = tipoCazador;
        Reporteria.agregarCazador(this);
    }

    public Cazador(ITipoCazador tipoCazador, IZona zona, int experiencia) {
        this.tipoCazador = tipoCazador;
        this.zona = zona;
        this.experiencia = experiencia;
        Reporteria.agregarCazador(this);
    }

    public List<IProfugo> getProfugosCapturados() {
        return profugosCapturados;
    }

    public List<IProfugo> getProfugosIntimidados() {
        return profugosIntimidados;
    }

    public ITipoCazador getTipoCazador() {
        return tipoCazador;
    }

    public void aumentarExperiencia(int aumento) {
        this.experiencia += aumento;
    }

    public boolean puedeCapturar(IProfugo profugo) {
        return this.condicionDeCapturaGeneral(profugo) && this.tipoCazador.condicionDeCapturaEspecifica(profugo);
    }

    public boolean condicionDeCapturaGeneral(IProfugo profugo) {
        return this.experiencia > profugo.getInocencia();
    }

    private void intimidar(IProfugo profugo) {
        profugosIntimidados.add(profugo);
        this.realizarIntimidacionGeneral(profugo);
        tipoCazador.realizarIntimidacionEspecifica(profugo);
    }

    public void intentarCapturar(IProfugo profugo) {
        if (zona == null)
            throw new IllegalStateException("El cazador no tiene asignada una zona");
        if (puedeCapturar(profugo)) {
            capturarProfugo(profugo);
            return;
        }
        intimidar(profugo);
    }

    public void capturarProfugo(IProfugo profugo) {
        profugosCapturados.add(profugo);
        zona.eliminarProfugo(profugo);
    }

    private void realizarIntimidacionGeneral(IProfugo profugo) {
        profugo.reducirInocencia(2);
    }

    public IZona getZona() {
        return zona;
    }

    public void asignarZona(IZona zona) {
        this.zona = zona;
    }

    public void hacerPatrullaje() {
        List<IProfugo> profugosEnZona = new ArrayList<>(zona.getProfugos());
        for (IProfugo profugo : profugosEnZona) {
            this.intentarCapturar(profugo);
        }
        this.aumentarExperiencia();
    }

    public int getExperiencia() {
        return experiencia;
    }

    private void aumentarExperiencia() {
        experiencia += (profugosIntimidados.stream()
                .mapToInt(IProfugo::getInocencia)
                .min()
                .orElse(0) + (2 * profugosCapturados.size()));
    }
}
