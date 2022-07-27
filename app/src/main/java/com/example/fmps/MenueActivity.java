package com.example.fmps;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenueActivity extends AppCompatActivity {
    private Button voir,deconnecter;
    private TextView count;
    private CardView cardinscripton;
    public  static boolean etape;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);
        voir = (Button) findViewById(R.id.voir);
        deconnecter = (Button) findViewById(R.id.deconnecter);
        count = (TextView) findViewById(R.id.count);
        cardinscripton = (CardView) findViewById(R.id.cardinscripton);
        AlertDialog.Builder message = new AlertDialog.Builder(MenueActivity.this);
        Dbconn conn = new Dbconn(MenueActivity.this);

        conn.hideSystemBars(MenueActivity.this);
        count.setText(String.valueOf(conn.getAllcount().getCount()));
        voir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenueActivity.this ,ListeActivity.class);
                startActivity(i);

            }
        });
        cardinscripton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenueActivity.this ,EnfanActivity.class);
                startActivity(i);
                finish();
                etape = true;
            }
        });
        deconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setTitle("Confirmation");
                message.setMessage("Voulez Vraiment Déconnecter");
                message.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(MenueActivity.this ,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
                message.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                message.show();
            }
        });
    }

}