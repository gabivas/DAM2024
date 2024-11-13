package ro.ase.grupa1093;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shaormas")
public class Shaorma {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private float gramaj;
    private float pret;
    private int nrCalorii;

    public Shaorma(int nrCalorii, float pret, float gramaj) {
        this.nrCalorii = nrCalorii;
        this.pret = pret;
        this.gramaj = gramaj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getGramaj() {
        return gramaj;
    }

    public void setGramaj(float gramaj) {
        this.gramaj = gramaj;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getNrCalorii() {
        return nrCalorii;
    }

    public void setNrCalorii(int nrCalorii) {
        this.nrCalorii = nrCalorii;
    }

    @Override
    public String toString() {
        return "Shaorma{" +
                "id=" + id +
                ", gramaj=" + gramaj +
                ", pret=" + pret +
                ", nrCalorii=" + nrCalorii +
                '}';
    }
}
