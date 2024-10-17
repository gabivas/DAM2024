package ro.ase.grupa1094;

import java.util.Date;

enum Produse {APA, CARTOFI, SUC}

enum Valuta {RON, EUR}

public class Food {
    private String nume;
    private float pret;
    private int cantitate;
    private String adresa;
    private Date dataLivrare;
    private Produse produse;
    private Valuta valuta;

    public Food(String specie, float greutate, int varsta, String taraOrigine, Date dataNastere, Produse produse, Valuta valuta) {
        this.nume = specie;
        this.pret = greutate;
        this.cantitate = varsta;
        this.adresa = taraOrigine;
        this.dataLivrare = dataNastere;
        this.produse = produse;
        this.valuta = valuta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDataLivrare() {
        return dataLivrare;
    }

    public void setDataLivrare(Date dataLivrare) {
        this.dataLivrare = dataLivrare;
    }

    public Produse getProduse() {
        return produse;
    }

    public void setProduse(Produse produse) {
        this.produse = produse;
    }

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    @Override
    public String toString() {
        return "Food{" +
                "nume='" + nume + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                ", adresa='" + adresa + '\'' +
                ", dataLivrare=" + dataLivrare +
                ", produse=" + produse +
                ", valuta=" + valuta +
                '}';
    }
}
