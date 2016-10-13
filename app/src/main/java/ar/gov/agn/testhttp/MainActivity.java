package ar.gov.agn.testhttp;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiUrl = "http://fake-api-android-lkdml.c9users.io/categorias?id=1";


        Uri.Builder parametros = new Uri.Builder();
        parametros.appendQueryParameter("email", "lazaro2002@hotmail.com");
        parametros.appendQueryParameter("password", "123");



        Handler h = new Handler(this);
        HttpManager httpManager = new HttpManager(h,apiUrl);
        httpManager.setParametros(parametros);
        httpManager.start();


    }

    @Override
    public boolean handleMessage(Message msg) {
        String texto =(String) msg.obj;
        TextView tv = (TextView) findViewById(R.id.Texto);
        tv.setText(texto);
        return true;
    }
}
