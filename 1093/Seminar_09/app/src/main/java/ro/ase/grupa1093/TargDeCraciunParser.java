package ro.ase.grupa1093;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TargDeCraciunParser {
    private static final String LOCATIE = "locatie";
    private static final String NUMAR_CASUTE = "numarCasute";
    private static final String ARE_PATINOAR = "arePatinoar";

    public static List<TargDeCraciun> parsareJson(String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareTarguri(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    private static List<TargDeCraciun> parsareTarguri(JSONArray jsonArray) throws JSONException {
        List<TargDeCraciun> targuri = new ArrayList<>();
        for(int i=0; i< jsonArray.length(); i++){
            targuri.add(parsareTarg(jsonArray.getJSONObject(i)));
        }

        return targuri;
    }
    private static TargDeCraciun parsareTarg(JSONObject jsonObject) throws JSONException {
        String locatie = jsonObject.getString(LOCATIE);
        int numarCasute = jsonObject.getInt(NUMAR_CASUTE);
        boolean arePatinoar = jsonObject.getBoolean(ARE_PATINOAR);
        return new TargDeCraciun(locatie, numarCasute, arePatinoar);
    }
}
