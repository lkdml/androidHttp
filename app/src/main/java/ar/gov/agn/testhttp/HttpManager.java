package ar.gov.agn.testhttp;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lkdml on 12/10/2016.
 */
public class HttpManager extends Thread{
    private Handler handler;
    private static final String DEBUG_TAG = "HttpExample";
    private String url;

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    private String parametros;



    public HttpManager(Handler handler, String url) {
        this.handler = handler;
        this.url = url;
    }


    @Override
    public void run() {
        super.run();
        //HttpManager httpM = new HttpManager(url);
        Message msg= new Message();
        msg.arg1=0;

        String responce= new String ("No se pudo alcanzar el destino :" + url);
        try {
            //responce = this.httpGet();
            responce = this.httpPost();
            Log.d("Ver:",responce.toString());
            msg.arg1=1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        msg.obj = responce;
        this.handler.sendMessage(msg);
    }

    private void sendMsg(String msg){
        Message message = new Message();
        message.obj=msg;
        handler.sendMessage(message);
    }
    // Given a URL, establishes an HttpUrlConnection and retrieves
    // the web page content as a InputStream, which it returns as
    // a string.
    private String httpGet() throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("AUTHORIZATION","08f88e9f7bd717eeab1d17db7a1620d0");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            conn.setDoInput(true);
            // Starts the query
            conn.connect();




            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is_: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            Log.d(DEBUG_TAG, "The response is: " + contentAsString);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private String httpPost() throws IOException {
        InputStream is = null;
        OutputStreamWriter os = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            //conn.setRequestProperty("AUTHORIZATION","08f88e9f7bd717eeab1d17db7a1620d0");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            conn.setDoInput(true);

            os = new OutputStreamWriter(conn.getOutputStream());
            os.write(this.parametros);
            os.flush();
            os.close();



            // Starts the query
            conn.connect();




            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is_: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            Log.d(DEBUG_TAG, "The response is: " + contentAsString);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }



}
