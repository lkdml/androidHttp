package ar.gov.agn.testhttp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lkdml on 13/10/2016.
 */
public class Categoria {
    private Integer id;
    private String nombre;
    private String descripcion;
    private int favorita;

    public void setCategoriaFromJSON(JSONArray array) {
        try {
            Log.d("NUCLEO Estados:",array.toString());
            for (int i=0; i<array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                Integer id = Integer.parseInt(item.getString("id"));
                String nombre = item.getString("nombre");
                String descripcion = item.getString("descripcion");
                Integer favorita = Integer.parseInt(item.getString("favorita"));

                this.id = id;
                this.nombre = nombre;
                this.descripcion = descripcion;
                this.favorita = favorita;

            }
        } catch (JSONException e) {
            Log.d("ERROR NUCLEO Estados:",e.getMessage());
            e.printStackTrace();
        }
    }
}
