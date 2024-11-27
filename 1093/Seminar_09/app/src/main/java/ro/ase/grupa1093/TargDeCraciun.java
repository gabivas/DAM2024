package ro.ase.grupa1093;

import java.io.Serializable;

public class TargDeCraciun implements Serializable {
    private String locatie;
    private int numarCasute;
    private boolean arePatinoar;

    public TargDeCraciun(String locatie, int numarCasute, boolean arePatinoar) {
        this.locatie = locatie;
        this.numarCasute = numarCasute;
        this.arePatinoar = arePatinoar;
    }

    @Override
    public String toString() {
        return "TargDeCraciun{" +
                "locatie='" + locatie + '\'' +
                ", numarCasute=" + numarCasute +
                ", arePatinoar=" + arePatinoar +
                '}';
    }
}
