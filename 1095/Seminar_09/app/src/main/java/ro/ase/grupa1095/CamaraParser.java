package ro.ase.grupa1095;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CamaraParser {
    private static final String SUPRAFATA = "suprafata";
    private static final String TIP = "tip";
    private static final String CULOARE = "culoare";

    public static List<Camera> parsareJson(String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareCamere(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Camera> parsareCamere(JSONArray jsonArray) throws JSONException {
        List<Camera> camere = new ArrayList<>();
        for(int i=0; i< jsonArray.length(); i++){
            camere.add(parsareCamera(jsonArray.getJSONObject(i)));
        }
        return camere;
    }

    private static Camera parsareCamera(JSONObject jsonObject) throws JSONException {
        String tip = jsonObject.getString(TIP);
        String culoare = jsonObject.getString(CULOARE);
        float suprafata = (float) jsonObject.getDouble(SUPRAFATA);
        return new Camera(suprafata, tip, culoare);
    }
}
