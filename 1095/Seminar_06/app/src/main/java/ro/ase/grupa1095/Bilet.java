package ro.ase.grupa1095;

import java.io.Serializable;
import java.util.Date;

enum Categorie {CAT1, CAT2, VIP}

enum MetodaPlata {CARD, CASH}

public class Bilet implements Serializable {
    private String nume;
    private String locatie;
    private int nrBilete;
    private Date dataConcert;
    private MetodaPlata metodaPlata;
    private Categorie categorie;

    public Bilet(String nume, String locatie, int nrBilete, Date dataConcert, MetodaPlata metodaPlata, Categorie categorie) {
        this.nume = nume;
        this.locatie = locatie;
        this.nrBilete = nrBilete;
        this.dataConcert = dataConcert;
        this.metodaPlata = metodaPlata;
        this.categorie = categorie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public int getNrBilete() {
        return nrBilete;
    }

    public void setNrBilete(int nrBilete) {
        this.nrBilete = nrBilete;
    }

    public Date getDataConcert() {
        return dataConcert;
    }

    public void setDataConcert(Date dataConcert) {
        this.dataConcert = dataConcert;
    }

    public MetodaPlata getMetodaPlata() {
        return metodaPlata;
    }

    public void setMetodaPlata(MetodaPlata metodaPlata) {
        this.metodaPlata = metodaPlata;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "nume='" + nume + '\'' +
                ", locatie='" + locatie + '\'' +
                ", nrBilete=" + nrBilete +
                ", dataConcert=" + dataConcert +
                ", metodaPlata=" + metodaPlata +
                ", categorie=" + categorie +
                '}';
    }
}
