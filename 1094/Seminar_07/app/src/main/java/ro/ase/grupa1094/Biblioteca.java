package ro.ase.grupa1094;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Biblioteci")
public class Biblioteca {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String denumire;
    private String adresa;
    private int capacitate;

    public Biblioteca(int capacitate, String adresa, String denumire) {
        this.capacitate = capacitate;
        this.adresa = adresa;
        this.denumire = denumire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", adresa='" + adresa + '\'' +
                ", capacitate=" + capacitate +
                '}';
    }
}
