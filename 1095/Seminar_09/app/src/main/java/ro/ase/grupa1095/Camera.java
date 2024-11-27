package ro.ase.grupa1095;

import java.io.Serializable;

public class Camera implements Serializable {
    private float suprafata;
    private String tip;
    private String culoare;

    public float getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(float suprafata) {
        this.suprafata = suprafata;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public Camera(float suprafata, String tip, String culoare) {
        this.suprafata = suprafata;
        this.tip = tip;
        this.culoare = culoare;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "suprafata=" + suprafata +
                ", tip='" + tip + '\'' +
                ", culoare='" + culoare + '\'' +
                '}';
    }
}
