package ro.ase.grupa109;

import java.io.Serializable;

public class Produs implements Serializable {
    private String id;
    private String denumire;
    private float pret;

    public Produs() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produsul " + denumire + " costa " + pret +
                " lei!";
    }
}
