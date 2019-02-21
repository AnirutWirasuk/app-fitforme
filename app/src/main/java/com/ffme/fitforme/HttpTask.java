package com.ffme.fitforme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.webkit.WebView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

public class HttpTask extends AsyncTask<String, Void, String> {
    WebView mWebView;
    private Context context;

    public HttpTask(Context context){
        this.context=context;
    }

    String[] itemname = {
            "LISTS",
            "CARDS",
            "PARALLAX",
            "TABS",
            "ONBOARDING / WIZARDS",
            "LOGIN / REGISTER",
            "SEARCH",
            "IMAGE GALLERY",
            "DIALOGS",
            "SMALL COMPONENTS",
            "SPLASH"

    };

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        try {

            HttpGet httpGet = new HttpGet(urls[0]);
            HttpClient client = new DefaultHttpClient();

            HttpResponse response = client.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 200) {
                InputStream inputStream = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
            }

        } catch (ClientProtocolException e) {

        } catch (IOException e) {

        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        String fileNameString = "dataSystem";
        String loginStatus = "login";

        SharedPreferences sharedPref = context.getSharedPreferences(fileNameString, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(loginStatus, 1);
        editor.commit();

        Intent i = new Intent(context, MainActivity.class);
        i.putExtra("name", result);
        context.startActivity(i);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
