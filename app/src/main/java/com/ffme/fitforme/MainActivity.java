package com.ffme.fitforme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends BaseActivity {
    WebView mWebView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String fileNameString = "dataSystem";
        String loginStatus = "login";

        SharedPreferences sharedPref = getSharedPreferences(fileNameString, MODE_PRIVATE);
        int login = sharedPref.getInt(loginStatus, 0);
        String textFname = sharedPref.getString("Fname","Fname");
        String textLname = sharedPref.getString("Lname","Lname");
        String textEmail = sharedPref.getString("Email","Email");
        String textImg = sharedPref.getString("Img","");
        String textFullname = textFname+" "+textLname;
        if(login == 1){

            toolbartitle = (TextView) findViewById(R.id.toolbar_title);

            FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
            getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);
            TextView toolbartitle = (TextView) findViewById(R.id.toolbar_title);
            toolbartitle.setText(R.string.validation_register_title);

            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString("title");
            String resultURL = bundle.getString("resultURL");
            toolbartitle.setText(title);

            final ImageView iv = (ImageView) findViewById(R.id.profile_image);

            TextView textShowFname = (TextView)findViewById(R.id.textShowFname);
            textShowFname.setText(textFullname);

            TextView textShowEmail = (TextView)findViewById(R.id.textShowEmail);
            textShowEmail.setText(textEmail);

            if(textImg != ""){
                final String imgURL  = "http://fitme.pe.hu/uploads/profile/"+textImg;

                new DownLoadImageTask(iv).execute(imgURL);
            }


            progressBar = (ProgressBar) findViewById(R.id.progressbar);

            mWebView = (WebView) findViewById(R.id.webView_content);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.setWebViewClient(new Browser_home());
            mWebView.setWebChromeClient(new MyChrome());
            mWebView.loadUrl(resultURL);

//            mWebView.loadData(result, "text/html; charset=utf-8", "utf-8");

        }else{

            Intent i = new Intent(MainActivity.this, ValidationLogin.class);
            startActivity(i);

        }

    }

    class Browser_home extends WebViewClient {

        Browser_home() {
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            setTitle(view.getTitle());
            progressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);

        }
    }

    private class MyChrome extends WebChromeClient {

        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        MyChrome() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846);
        }
    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

}

