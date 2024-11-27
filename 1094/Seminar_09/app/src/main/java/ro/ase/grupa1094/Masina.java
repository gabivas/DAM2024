package ro.ase.grupa1094;

import java.io.Serializable;

public class Masina implements Serializable {
    private String serie;
    private String marca;
    private int cp;

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Masina(String serie, int cp, String marca) {
        this.serie = serie;
        this.cp = cp;
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "serie='" + serie + '\'' +
                ", marca='" + marca + '\'' +
                ", cp=" + cp +
                '}';
    }
}
