package ro.ase.grupa1095;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nume;
    private String email;
    private String parolaInitiala;

    public User(String nume, String email, String parolaInitiala) {
        this.nume = nume;
        this.email = email;
        this.parolaInitiala = parolaInitiala;
    }

    public String getParolaInitiala() {
        return parolaInitiala;
    }

    public void setParolaInitiala(String parolaInitiala) {
        this.parolaInitiala = parolaInitiala;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", parolaInitiala='" + parolaInitiala + '\'' +
                '}';
    }
}
