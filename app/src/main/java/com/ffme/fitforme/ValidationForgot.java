package com.ffme.fitforme;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;


public class ValidationForgot extends BaseActivityTransparentToolbar {
    private AppBarLayout appbar;
    private ConstraintLayout main;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        orientation = 1;
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.validation_forgot_content, contentFrameLayout);
        TextView toolbartitle = (TextView) findViewById(R.id.toolbar_title);
        toolbartitle.setText(R.string.validation_forgot_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
        toolbar.getBackground().setAlpha(200);
        //drawerLayout.setAlpha(1);
        appbar = (AppBarLayout) findViewById(R.id.app_bar_transparent);
        appbar.getBackground().setAlpha(0);

        main = (ConstraintLayout) findViewById(R.id.content_top);
       /* main.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);*/

        final TextView subscribe = (TextView) findViewById(R.id.login_validation);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputLayout editEmail = (TextInputLayout) findViewById(R.id.validation_email);
                EditText enteredEmail = (EditText) findViewById(R.id.validation_email_edit);
                String email = enteredEmail.getText().toString();

                if (isValidEmail(email)) {

                    // finish();
                } else {

                    editEmail.setError(getString(R.string.dialog_subscribe_error));
                }


                //finish();

            }
        });
        final TextView login = (TextView) findViewById(R.id.forget_loginsubmit);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ValidationLogin.class);
                startActivity(intent);

                //finish();

            }
        });
        final TextView register = (TextView) findViewById(R.id.forget_registersubmit);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ValidationRegister.class);
                startActivity(intent);

                //finish();

            }
        });

    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
