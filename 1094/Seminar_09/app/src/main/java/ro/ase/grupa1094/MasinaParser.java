package ro.ase.grupa1094;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MasinaParser {
    private static final String SERIE = "serie";
    private static final String MARCA = "marca";
    private static final String CP = "cp";

    public static List<Masina> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareMasini(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Masina> parsareMasini(JSONArray jsonArray) throws JSONException {
        List<Masina> masini = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            masini.add(parsareMasina(jsonArray.getJSONObject(i)));
        }
        return masini;
    }

    private static Masina parsareMasina(JSONObject jsonObject) throws JSONException {
        String serie = jsonObject.getString(SERIE);
        String marca = jsonObject.getString(MARCA);
        int cp = jsonObject.getInt(CP);
        return new Masina(serie, cp, marca);
    }
}
