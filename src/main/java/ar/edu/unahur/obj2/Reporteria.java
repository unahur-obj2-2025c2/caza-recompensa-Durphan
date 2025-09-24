package ar.edu.unahur.obj2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ar.edu.unahur.obj2.cazadores.ICazador;
import ar.edu.unahur.obj2.profugos.IProfugo;

public class Reporteria {
    private static List<ICazador> cazadores = new ArrayList<>();

    public static void agregarCazador(ICazador cazador) {
        cazadores.add(cazador);
    }

    public static List<IProfugo> profugosCapturados() {
        List<IProfugo> profugos = new ArrayList<>();
        for (ICazador cazador : cazadores) {
            profugos.addAll(cazador.getProfugosCapturados());
        }
        return profugos;
    }

    public static ICazador cazadorConMasCapturas() {
        return cazadores.stream()
                .max(Comparator.comparingInt(cazador -> cazador.getProfugosCapturados().size()))
                .orElseThrow(() -> new IllegalStateException("No hay cazadores en el sistema"));
    }

    public static IProfugo profugoMasHabilCapturado() {
        return cazadores.stream().flatMap(cazador -> cazador.getProfugosCapturados().stream())
                .max(Comparator.comparingInt(IProfugo::getHabilidad))
                .orElseThrow(() -> new IllegalArgumentException("Ningun cazador tiene capturado un fugitivo"));
    }

    public static List<ICazador> getCazadores() {
        return cazadores;
    }

    private Reporteria() {
    }
}
