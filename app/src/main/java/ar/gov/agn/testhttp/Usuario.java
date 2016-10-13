package ar.gov.agn.testhttp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lkdml on 13/10/2016.
 */
public class Usuario {
    private String nombre;
    private String email;
    private String apiKey;


    public void setFromJson(String str) {
        try {

            JSONObject jsObject = new JSONObject(str);
            Log.d("setFromJson:", jsObject.toString());
                //Integer id = Integer.parseInt(item.getString("apiKey"));
                String error = jsObject.getString("error");
                String name = jsObject.getString("name");
                String email = jsObject.getString("email");
                String apiKey = jsObject.getString("apiKey");
                String createdAt = jsObject.getString("createdAt");
                //Log.d("parserJson:", error + " | " + name + " | " + email + "|" + apiKey + "|" + createdAt);
                this.nombre = name;
                this.email = email;
                this.apiKey = apiKey;

        } catch (JSONException e) {
            Log.d("ERROR setFromJson:", e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getApiKey() {
        return apiKey;
    }
}
