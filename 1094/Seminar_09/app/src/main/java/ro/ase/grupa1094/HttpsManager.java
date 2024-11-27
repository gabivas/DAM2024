package ro.ase.grupa1094;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsManager {
    private HttpsURLConnection httpsURLConnection;
    private String adresaUrl;
    private BufferedReader bufferedReader;

    public HttpsManager(String adresaUrl) {
        this.adresaUrl = adresaUrl;
    }

    public String procesare() {
        try {
            return obtinereRezultatHttps();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            inchidereConexiuni();
        }
    }

    private void inchidereConexiuni() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        httpsURLConnection.disconnect();
    }

    private String obtinereRezultatHttps() throws IOException {
        //apel trustEveryone();
        httpsURLConnection = (HttpsURLConnection) new URL(adresaUrl).openConnection();
        bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
