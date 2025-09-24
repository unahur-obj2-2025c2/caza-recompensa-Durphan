package ar.edu.unahur.obj2.profugos.entrenamientos;

import ar.edu.unahur.obj2.profugos.IProfugo;

public abstract class BaseEntrenamiento implements IProfugo {
    private IProfugo base;

    public BaseEntrenamiento(IProfugo base) {
        this.base = base;
    }

    @Override
    public int getInocencia() {
        return base.getInocencia();
    }

    @Override
    public void reducirInocencia(int reduccion) {
        base.reducirInocencia(reduccion);
    }

    @Override
    public void aumentarInocencia(int aumento) {
        base.aumentarInocencia(aumento);
    }

    @Override
    public int getHabilidad() {
        return base.getHabilidad();
    }

    @Override
    public void disminuirHabilidad(int disminucion) {
        base.disminuirHabilidad(disminucion);
    }

    @Override
    public void aumentarHabilidad(int aumento) {
        base.aumentarHabilidad(aumento);
    }

    @Override
    public boolean estaNervioso() {
        return base.estaNervioso();
    }

    @Override
    public void ponerNervioso() {
        base.ponerNervioso();
    }

    @Override
    public void calmar() {
        base.calmar();
    }

}
