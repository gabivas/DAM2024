package ro.ase.grupa1095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsManager {
    private String urlAddress;
    private BufferedReader reader;
    private HttpsURLConnection connection;

    public HttpsManager(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String procesare() {
        try {
            return preluareJsonHttps();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            inchidereConexiuni();
        }
    }

    private void inchidereConexiuni() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connection.disconnect();
    }

    private String preluareJsonHttps() throws IOException {
        connection = (HttpsURLConnection) new URL(urlAddress).openConnection();
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}
