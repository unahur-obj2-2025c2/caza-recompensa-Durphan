package ar.edu.unahur.obj2.zonas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.profugos.IProfugo;

public class Zona implements IZona {
    private final String nombre;
    private List<IProfugo> profugos = new ArrayList<>();

    public List<IProfugo> getProfugos() {
        return profugos;
    }

    public Zona(String nombre) {
        this.nombre = nombre;
    }

    public Zona(String nombre, List<IProfugo> profugos) {
        this.nombre = nombre;
        this.profugos = profugos;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarProfugo(IProfugo profugo) {
        profugos.add(profugo);
    }

    public void eliminarProfugo(IProfugo profugo) {
        profugos.remove(profugo);
    }

    public void limpiarProfugos() {
        profugos.clear();
    }
}
