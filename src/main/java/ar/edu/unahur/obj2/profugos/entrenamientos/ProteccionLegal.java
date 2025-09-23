package ar.edu.unahur.obj2.profugos.entrenamientos;

import ar.edu.unahur.obj2.profugos.IProfugo;

public class ProteccionLegal extends BaseEntrenamiento {

    public ProteccionLegal(IProfugo base) {
        super(base);
        if (base instanceof ProteccionLegal) {
            throw new IllegalArgumentException("El profugo ya tiene proteccion legal");
        }
        if (base.getInocencia() < 40) {
            base.aumentarInocencia(Math.max(0, 40 - base.getInocencia()));
        }
    }

    @Override
    public void reducirInocencia(int reduccion) {
        if (super.getInocencia() - reduccion < 40) {
            super.reducirInocencia(Math.max(0, super.getInocencia() - 40));
            return;
        }
        super.reducirInocencia(reduccion);
    }

}
