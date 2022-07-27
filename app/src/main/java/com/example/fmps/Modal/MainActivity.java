package com.example.fmps.Modal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fmps.R;
import com.example.fmps.fullscreen;

public class MainActivity extends AppCompatActivity {
    private String username,password;
    private Button connecter;
        private EditText user,passworde;
        private CheckBox saveLoginCheckBox;
        private SharedPreferences loginPreferences;
        private SharedPreferences.Editor loginPrefsEditor;
        private Boolean saveLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connecter = (Button) findViewById(R.id.cnct);
        user = (EditText) findViewById(R.id.Txtuser);
        passworde = (EditText) findViewById(R.id.password);
        saveLoginCheckBox = (CheckBox)findViewById(R.id.checkBox);
        saveLoginCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (saveLoginCheckBox.isChecked()){
                    user.getBackground().setColorFilter(Color.parseColor("#E8F0FE"), PorterDuff.Mode.SRC_ATOP);
                    passworde.getBackground().setColorFilter(Color.parseColor("#E8F0FE"), PorterDuff.Mode.SRC_ATOP);
                }
                else {

                    user.getBackground().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
                    passworde.getBackground().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
                    user.getBackground().setColorFilter(R.drawable.text, PorterDuff.Mode.ADD);
                    passworde.getBackground().setColorFilter(R.drawable.text, PorterDuff.Mode.ADD);

                }
            }
        });
        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(user.getWindowToken(), 0);

                username = user.getText().toString();
                password = passworde.getText().toString();

                if (saveLoginCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", username);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();

                }
                if(user.getText().toString().equals("SI") && passworde.getText().toString().equals("123456") ){
                    Intent i = new Intent(MainActivity.this ,   MenueActivity.class);
                    startActivity(i);}
                else {
                    Toast.makeText(MainActivity.this, "Username ou Mot de pass Incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fullscreen c = new fullscreen();
        c.full(this);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            user.setText(loginPreferences.getString("username", ""));
            passworde.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
            user.getBackground().setColorFilter(Color.parseColor("#E8F0FE"), PorterDuff.Mode.SRC_ATOP);

        }
    }
  /*/ public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            SQLCONNECTION cdon = new SQLCONNECTION();
          cdon.GetConnection(context);
        }else if (netInfo == null) {
            Dbconn db = new Dbconn(context);
        }
        return true;
    }*/
}