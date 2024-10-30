package ro.ase.grupa1093;

import java.io.Serializable;
import java.util.Date;

enum StilAlimentar {CARNIVOR, IERBIVOR, OMNIVOR}

enum Sex {MASCUL, FEMELA}

public class AnimalZoo implements Serializable {
    private String specie;
    private float greutate;
    private int varsta;
    private String taraOrigine;
    private Date dataNastere;
    private StilAlimentar stilAlimentar;
    private Sex sex;

    public AnimalZoo(String specie, float greutate, int varsta, String taraOrigine, Date dataNastere, StilAlimentar stilAlimentar, Sex sex) {
        this.specie = specie;
        this.greutate = greutate;
        this.varsta = varsta;
        this.taraOrigine = taraOrigine;
        this.dataNastere = dataNastere;
        this.stilAlimentar = stilAlimentar;
        this.sex = sex;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public float getGreutate() {
        return greutate;
    }

    public void setGreutate(float greutate) {
        this.greutate = greutate;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getTaraOrigine() {
        return taraOrigine;
    }

    public void setTaraOrigine(String taraOrigine) {
        this.taraOrigine = taraOrigine;
    }

    public Date getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(Date dataNastere) {
        this.dataNastere = dataNastere;
    }

    public StilAlimentar getStilAlimentar() {
        return stilAlimentar;
    }

    public void setStilAlimentar(StilAlimentar stilAlimentar) {
        this.stilAlimentar = stilAlimentar;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "AnimalZoo{" +
                "specie='" + specie + '\'' +
                ", greutate=" + greutate +
                ", varsta=" + varsta +
                ", taraOrigine='" + taraOrigine + '\'' +
                ", dataNastere=" + dataNastere +
                ", stilAlimentar=" + stilAlimentar +
                ", sex=" + sex +
                '}';
    }
}
