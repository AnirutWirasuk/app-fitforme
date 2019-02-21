package com.ffme.fitforme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ValidationLogin extends BaseActivityTransparentToolbar {

    private static final String IMGUR_CLIENT_ID = "9199fdef135c122";

    private EditText enteredFirstname;
    private TextInputLayout editFirstname;
    private String textEmail;
    private String textPassword;
    private String fileNameString = "dataSystem";
    private String loginStatus = "login";

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences(fileNameString, MODE_PRIVATE);
        int loginst = sharedPref.getInt(loginStatus, 0);
        if(loginst == 1){

            Intent i = new Intent(ValidationLogin.this, MainActivity.class);
            i.putExtra("resultURL", "http://fitme.pe.hu/main/news/1");
            i.putExtra("title", "ข่าวสาร");
            startActivity(i);

        }else{

            setContentView(R.layout.empty_page_transparent_toolbar);
            FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
            getLayoutInflater().inflate(R.layout.validation_login_content, contentFrameLayout);
            TextView toolbartitle = (TextView) findViewById(R.id.toolbar_title);
            toolbartitle.setText(R.string.validation_register_title);
            toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
            toolbar.getBackground().setAlpha(200);

            final TextView subscribe = (TextView) findViewById(R.id.login_validation);
            subscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextInputLayout editEmail = (TextInputLayout) findViewById(R.id.validation_email);
                    EditText enteredEmail = (EditText) findViewById(R.id.validation_email_edit);

                    TextInputLayout editPassword = (TextInputLayout) findViewById(R.id.validation_password);
                    EditText enteredPassword = (EditText) findViewById(R.id.validation_password_edit);

                    textEmail = enteredEmail.getText().toString();

                    textPassword = enteredPassword.getText().toString();

                    if (isValidEmail(textEmail) != true) {
                        editEmail.setError(getString(R.string.dialog_subscribe_error));
                    } else if(isValidPassword(textPassword) != true){
                        editPassword.setError(getString(R.string.dialog_subscribe_pass));
                    }else{
                        new auThenserver().execute();
//                        new HttpTask(ValidationLogin.this).execute("http://www.suthong.co.th");
//                    Intent i = new Intent(ValidationLogin.this, MainActivity.class);
//                    i.putExtra("name", (Serializable) result);
//                    startActivity(i);
                        // finish();

                    }

                    //finish();

                }
            });

            final TextView login = (TextView) findViewById(R.id.login_forgotsubmit);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), ValidationRegister.class);
                    startActivity(intent);

                    // finish();

                }
            });

        }


    }
    private class auThenserver extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... urls) {

            MultipartBody.Builder buildernew = new MultipartBody.Builder().setType(MultipartBody.FORM);
            buildernew.addFormDataPart("textEmail", textEmail);
            buildernew.addFormDataPart("textPassword", textPassword);
            RequestBody requestBody = buildernew.build();
            Request request = new Request.Builder()
                    .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                    .url("http://fitme.pe.hu/main/authunserver")
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
//                if(s != "ok"){
//                    editEmail.setError("This email is invalid or duplicate");
//                    valiDation = false;
//                }
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.equals("no")){
                Toast.makeText(ValidationLogin.this, "อีเมล์หรือรหัสผ่านไม่ถูกต้อง!", Toast.LENGTH_LONG).show();
            }else{
                Gson gson = new Gson();
                JsonAuthen userData = gson.fromJson(s, JsonAuthen.class);
                SharedPreferences sharedPref = getSharedPreferences(fileNameString, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(loginStatus, 1);
                editor.putInt("Id", userData.getId());
                editor.putString("Img", userData.getImg());
                editor.putString("Fname", userData.getFname());
                editor.putString("Lname", userData.getLname());
                editor.putString("Tel", userData.getTel());
                editor.putString("Email", userData.getEmail());
                editor.putInt("Dis", userData.getDis());
                editor.commit();

                Intent i = new Intent(ValidationLogin.this, MainActivity.class);
                i.putExtra("resultURL", "http://fitme.pe.hu/main/news");
                i.putExtra("title", "ข่าวสาร");
                startActivity(i);
            }


//            Toast.makeText(ValidationLogin.this, s, Toast.LENGTH_LONG).show();
//            if(s.equals("no")){
//                Toast.makeText(ValidationLogin.this, s, Toast.LENGTH_LONG).show();
//            }
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidPassword(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return true;
        }
    }

}
