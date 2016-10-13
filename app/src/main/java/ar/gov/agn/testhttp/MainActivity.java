package ar.gov.agn.testhttp;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private Usuario u = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.u = new Usuario();

       // String apiUrl = "http://fake-api-android-lkdml.c9users.io/categorias?id=1";
        String apiUrl2 = "https://fake-api-android-lkdml.c9users.io/login";
        String parametros2 = "email=lazaro2002@hotmail.com&password=123";

        



        Handler h = new Handler(this);
        HttpManager httpManager = new HttpManager(h,apiUrl2);
        httpManager.setParametros(parametros2);
        httpManager.start();


    }

    @Override
    public boolean handleMessage(Message msg) {
        String texto =(String) msg.obj;
        TextView tv = (TextView) findViewById(R.id.Texto);
        tv.setText(texto);
        this.u.setFromJson(texto);
        TextView nombre = (TextView) findViewById(R.id.JSON_1);
        TextView email = (TextView) findViewById(R.id.JSON_2);
        TextView apiKey = (TextView) findViewById(R.id.JSON_3);

        nombre.setText(u.getNombre().toString());
        email.setText(u.getEmail());
        apiKey.setText(u.getApiKey());
        return true;
    }
}
