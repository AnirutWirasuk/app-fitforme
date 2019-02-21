package com.ffme.fitforme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class UpdateProfile extends BaseActivity {
    private Spinner spinner2;
    HashMap<Integer, Integer> listMap = new HashMap<>();
    private static final String IMGUR_CLIENT_ID = "9199fdef135c1225544";
    private static final MediaType MEDIA_TYPE = MediaType.parse("image/*");

    String img_profile;
    private File fileProfile;

    private EditText enteredFirstname;
    private TextInputLayout editFirstname;
    private String textFirstname;

    private EditText enteredLastname;
    private TextInputLayout editLastname;
    private String textLastname;

    private EditText enteredPhonenumber;
    private TextInputLayout editPhonenumber;
    private String textPhonenumber;

    private Integer intDisease;
    private TextInputLayout editDisease;

    private EditText enteredEmail;
    private TextInputLayout editEmail;
    private static String textEmail;

    private EditText enteredPassword;
    private TextInputLayout editPassword;
    private String textPassword;

    private EditText enteredConPassword;
    private TextInputLayout editConPassword;
    private String textConPassword;

    private Integer valiDation = 0;

    private AVLoadingIndicatorView avi;

    String textFname = "";
    String textLname = "";
    String textEmails = "";
    String textTel = "";
    String textImg = "";
    Integer textId = 0;
    Integer textDis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        String fileNameString = "dataSystem";
        String loginStatus = "login";

        SharedPreferences sharedPref = getSharedPreferences(fileNameString, MODE_PRIVATE);
        int login = sharedPref.getInt(loginStatus, 0);
        textFname = sharedPref.getString("Fname","Fname");
        textLname = sharedPref.getString("Lname","Lname");
        textEmails = sharedPref.getString("Email","Email");
        textImg = sharedPref.getString("Img","");
        textId = sharedPref.getInt("Id",0);
        textTel = sharedPref.getString("Tel","Tel");
        textDis = sharedPref.getInt("Dis",0);
        String textFullname = textFname+" "+textLname;
        if(login == 1){

            toolbartitle = (TextView) findViewById(R.id.toolbar_title);

            FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
            getLayoutInflater().inflate(R.layout.updateprofile, contentFrameLayout);
            TextView toolbartitle = (TextView) findViewById(R.id.toolbar_title);
            toolbartitle.setText(R.string.validation_register_title);

            Bundle bundle = getIntent().getExtras();
            String title = bundle.getString("title");
            toolbartitle.setText(title);

            final ImageView iv = (ImageView) findViewById(R.id.profile_image);

            TextView textShowFname = (TextView)findViewById(R.id.textShowFname);
            textShowFname.setText(textFullname);

            TextView textShowEmail = (TextView)findViewById(R.id.textShowEmail);
            textShowEmail.setText(textEmails);


            EditText enteredFirstnameEdit = (EditText)findViewById(R.id.validation_first_name_edit);
            enteredFirstnameEdit.setText(textFname);

            EditText enteredLastnameEdit = (EditText)findViewById(R.id.validation_last_name_edit);
            enteredLastnameEdit.setText(textLname);

            EditText enteredPhonenumberEdit = (EditText)findViewById(R.id.validation_phone_number_edit);
            enteredPhonenumberEdit.setText(textTel);

            EditText enteredEmailEdit = (EditText)findViewById(R.id.validation_email_edit);
            enteredEmailEdit.setText(textEmails);
            enteredEmailEdit.setEnabled(false);

            spinner2 = (Spinner) findViewById(R.id.spinner2);

//            Toast.makeText(UpdateProfile.this, textImg, Toast.LENGTH_LONG).show();
            if(textImg != ""){
                final String imgURL  = "http://fitme.pe.hu/uploads/profile/"+textImg;

                new DownLoadImageTask(iv).execute(imgURL);
            }

            new UpdateProfile.OkhttpTask().execute("http://fitme.pe.hu/main/jsondisease");




        }else{

            Intent i = new Intent(UpdateProfile.this, ValidationLogin.class);
            startActivity(i);

        }
        bindWidget();
        setWidgetListener();

    }

    private void bindWidget() {

        enteredFirstname = findViewById(R.id.validation_first_name_edit);
        editFirstname = findViewById(R.id.validation_first_name);

        enteredLastname = findViewById(R.id.validation_last_name_edit);
        editLastname = findViewById(R.id.validation_last_name);

        enteredPhonenumber = findViewById(R.id.validation_phone_number_edit);
        editPhonenumber = findViewById(R.id.validation_phone_number);

        editDisease = findViewById(R.id.validation_Disease);

        enteredEmail = findViewById(R.id.validation_email_edit);
        editEmail = findViewById(R.id.validation_email);

        enteredPassword = findViewById(R.id.validation_password_edit);
        editPassword = findViewById(R.id.validation_password);

        enteredConPassword = findViewById(R.id.validation_conpassword_edit);
        editConPassword = findViewById(R.id.validation_conpassword);

    }

    private void setWidgetListener() {

        // Select profile image
        findViewById(R.id.profile_image_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EasyImage.openChooserWithGallery(UpdateProfile.this, "Pick source", 0);
            }
        });

        // Check form and submit
        TextView subscribe = (TextView) findViewById(R.id.login_validation);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valiDation = 0;
                funValidation();
            }
        });

//        enteredEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus){
//                    new Checkduplicate().execute();
//                    Toast.makeText(getApplicationContext(), "gggg", 2000).show();
//                }
//            }
//        });

    }

    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() < 10 || phone.length() > 13) {
                // if(phone.length() != 10) {
                check = false;
            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }

    private void funValidation(){
        textFirstname = enteredFirstname.getText().toString();
        textLastname = enteredLastname.getText().toString();
        textPhonenumber = enteredPhonenumber.getText().toString();
        intDisease = listMap.get(spinner2.getSelectedItemPosition());
        textPassword = enteredPassword.getText().toString();
        textConPassword = enteredConPassword.getText().toString();

        if(textFirstname.length() < 1){
            editFirstname.setError("กรุณาระบุชื่อ");
            valiDation += 1;
        }

        if(textLastname.length() < 1){
            editLastname.setError("กรุณาระบุนามสกุล");
            valiDation += 1;
        }

        if(isValidMobile(textPhonenumber) == false){
            editPhonenumber.setError("กรุณาระบุเบอร์โทร");
            valiDation += 1;
        }else{
            editPhonenumber.setError(null);
        }

        if(intDisease == 0){
            editDisease.setError("กรุณาเลือกโรคประจำตัว");
            valiDation += 1;
        }else{
            editDisease.setError(null);
        }

        if(!textPassword.equals(textConPassword)){
            editConPassword.setError("กรุณาระบุรหัสผ่านให้ตรงกัน");
            valiDation += 1;
        }else{
            editConPassword.setError(null);
        }

        if(valiDation == 0){
            new UpdateProfile.sentDataServer().execute();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showData(String jsonString) {
        Gson gson = new Gson();
        JsonDisease blog = gson.fromJson(jsonString, JsonDisease.class);

        StringBuilder builder = new StringBuilder();
        builder.setLength(0);

        List<JsonDisease.DiseaseBean> posts = blog.getData();

        String[] list = new String[posts.size()+1];
        int i = 0;

        for (JsonDisease.DiseaseBean post : posts) {
            if(i == 0){
                listMap.put(0,0);
                list[0] = "กรุณาเลือกโรคประจำตัว";

                listMap.put(1,post.getId());
                list[1] = post.getName();
                i = 2;
            }else{
                listMap.put(i,post.getId());
                list[i] = post.getName();
                i++;
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
        int spinnerPosition = 0;
        for (Map.Entry<Integer, Integer> pair: listMap.entrySet()) {
            int v = pair.getValue();
            if(v == textDis){
                spinnerPosition = pair.getKey();
            }
            Log.v("Updateprofile", "index=" + v + " " +textDis);

        }

        spinner2.setSelection(spinnerPosition);

    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), ValidationLogin.class);
        startActivity(intent);

    }

    public final boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(target);
            return matcher.matches();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
                e.printStackTrace();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                onPhotosReturned(imageFile);
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
//                if (source == EasyImage.ImageSource.CAMERA) {
//                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(ValidationRegister.this);
//                    if (photoFile != null) photoFile.delete();
//                }
            }
        });
    }

    private void onPhotosReturned(File returnedPhotos) {
        Bitmap myBitmap = BitmapFactory.decodeFile(returnedPhotos.getAbsolutePath());

        ImageView myImage = (ImageView) findViewById(R.id.profile_image_edit);

        img_profile = returnedPhotos.getAbsolutePath();

        myImage.setImageBitmap(myBitmap);
    }

    private class OkhttpTask extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... urls) {

            Request.Builder builder = new Request.Builder();
            builder.url(urls[0]);
            Request request = builder.build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            showData(s);

        }
    }

    private class Checkduplicate extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... urls) {

            MultipartBody.Builder buildernew = new MultipartBody.Builder().setType(MultipartBody.FORM);
            buildernew.addFormDataPart("Id", String.valueOf(textId));
            buildernew.addFormDataPart("textEmail", textEmail);
            RequestBody requestBody = buildernew.build();
            Request request = new Request.Builder()
                    .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                    .url("http://fitme.pe.hu/main/checkemail")
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
//                Toast.makeText(UpdateProfile.this, s, Toast.LENGTH_LONG).show();
                editEmail.setError("อีเมล์ไม่ถูกต้องหรือมีผู้ใช้งานแล้ว");
                valiDation += 1;
            }
        }
    }

    private class sentDataServer extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... urls) {

            MultipartBody.Builder buildernew = new MultipartBody.Builder().setType(MultipartBody.FORM);
            buildernew.addFormDataPart("Id", String.valueOf(textId));
            buildernew.addFormDataPart("textFirstname", textFirstname);
            buildernew.addFormDataPart("textLastname", textLastname);
            buildernew.addFormDataPart("textPhonenumber", textPhonenumber);
            buildernew.addFormDataPart("intDisease", String.valueOf(intDisease));
            buildernew.addFormDataPart("textPassword", textPassword);
            if(img_profile != null){
                fileProfile = new File(img_profile);
                buildernew.addFormDataPart("imageProfile", fileProfile.getName(),RequestBody.create(MEDIA_TYPE, fileProfile));
            }
            RequestBody requestBody = buildernew.build();

            Request request = new Request.Builder()
                    .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                    .url("http://fitme.pe.hu/main/updateprofile")
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String fileNameString = "dataSystem";
            String loginStatus = "login";

            SharedPreferences sharedPref = getSharedPreferences(fileNameString, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(loginStatus, 1);
            editor.putInt("Id", textId);
            if(!s.isEmpty()){
                editor.putString("Img", s);
            }
            editor.putString("Fname", textFirstname);
            editor.putString("Lname", textLastname);
            editor.putString("Tel", textPhonenumber);
            editor.putInt("Dis", intDisease);
            editor.commit();

            Toast.makeText(UpdateProfile.this, "แก้ไขข้อมูลเรียบร้อยแล้ว", Toast.LENGTH_LONG).show();
            finish();
            startActivity(getIntent());
//            avi.hide();
//            Intent intent = new Intent(getApplicationContext(), ValidationLogin.class);
//            startActivity(intent);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            TextView login_validation = (TextView) findViewById(R.id.login_validation);
//            login_validation.setVisibility(TextView.INVISIBLE);
//
//            avi.show();
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
            ImageView iv2 = (ImageView) findViewById(R.id.profile_image_edit);
            iv2.setImageBitmap(result);
        }
    }


}

