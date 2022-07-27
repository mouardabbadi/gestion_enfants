package com.example.fmps.Modal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.fmps.Etudiant;
import com.example.fmps.R;
import com.example.fmps.Services.Dbconn;

import java.util.Calendar;

public class EnfanActivity extends AppCompatActivity {
    Spinner spingenre;
    private Button ajouter;
    private EditText nom,prenom,datetime,dateentre,lieu,nomtutur,prenomtutur,telephone,cin,motif,depart,massar;
    private Spinner genre,type,besoin,classe,section;
    private ImageView close;
    DatePickerDialog.OnDateSetListener onDatadate;
    DatePickerDialog.OnDateSetListener onDateentre;
    DatePickerDialog.OnDateSetListener onDatadepart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfan);
        Dbconn conn = new Dbconn(EnfanActivity.this); conn.hideSystemBars(EnfanActivity.this);
        spingenre= (Spinner) findViewById(R.id.downgenre);//fetch the spinner from layout file
        conn.rempliredown(spingenre,EnfanActivity.this,getResources().getStringArray(R.array.country_array));
        ajouter = findViewById(R.id.add);
        nom = (EditText) findViewById(R.id.nomtxt);
        prenom = (EditText) findViewById(R.id.prenom);
        genre = (Spinner) findViewById(R.id.downgenre) ;
        type = (Spinner) findViewById(R.id.type) ;
        classe = (Spinner) findViewById(R.id.classe) ;
        besoin = (Spinner) findViewById(R.id.besoin) ;
        section = (Spinner) findViewById(R.id.section) ;
        dateentre = (EditText) findViewById(R.id.dateentre);
        lieu = (EditText) findViewById(R.id.lieu);
        nomtutur = (EditText) findViewById(R.id.nomtuteur);
        prenomtutur = (EditText) findViewById(R.id.prenomtuter);
        telephone = (EditText) findViewById(R.id.telephone);
        close = (ImageView) findViewById(R.id.closeenfant);
        cin = (EditText) findViewById(R.id.cin);
        motif = (EditText) findViewById(R.id.motfi);
        depart = (EditText) findViewById(R.id.depart);
        massar = (EditText) findViewById(R.id.masar);
        Calendar calendar =Calendar.getInstance();
        final int annescolaire=calendar.get(Calendar.YEAR);
        datetime = (EditText) findViewById(R.id.datetime);
        AlertDialog.Builder r = new AlertDialog.Builder(EnfanActivity.this);
        /*datepciker---------------------------------------------*/
        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int year=calendar.get(Calendar.YEAR);
                final int month=calendar.get(Calendar.MONTH);
                final int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        EnfanActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDatadate,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });
        onDatadate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String data = dayOfMonth+"/"+month+"/"+year;
                datetime.setText(data.toString());
            }
        };
        dateentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int year=calendar.get(Calendar.YEAR);
                final int month=calendar.get(Calendar.MONTH);
                final int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        EnfanActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateentre,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
                dateentre.setText(day+"/"+month+"/"+year);
            }
        });
        onDateentre = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String data = dayOfMonth+"/"+month+"/"+year;
                dateentre.setText(data.toString());
            }
        };
        depart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int year=calendar.get(Calendar.YEAR);
                final int month=calendar.get(Calendar.MONTH);
                final int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        EnfanActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDatadepart,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });
        onDatadepart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String data = dayOfMonth+"/"+month+"/"+year;
                depart.setText(data.toString());
            }
        };
        /*datepciker---------------------------------------------*/
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    r.setTitle("Confirmation");
                    r.setMessage("Veuillez Vraiment Ajouter  Cette Enfant");

                    r.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    r.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Etudiant p = new Etudiant(nom.getText().toString(), prenom.getText().toString(), genre.getSelectedItem().toString(), datetime.getText().toString(), dateentre.getText().toString(), lieu.getText().toString(), nomtutur.getText().toString(), prenomtutur.getText().toString(), type.getSelectedItem().toString(), telephone.getText().toString(), cin.getText().toString(), massar.getText().toString(), classe.getSelectedItem().toString(), section.getSelectedItem().toString(), depart.getText().toString(), 1, String.valueOf(annescolaire), motif.getText().toString(), besoin.getSelectedItem().toString());
                            conn.insert(p, EnfanActivity.this);
                            Intent i = new Intent(EnfanActivity.this, ListeActivity.class);
                            startActivity(i);
                            finish();
                        }


                    });

                    r.show();
                }

        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MenueActivity.etape == true){
                    EnfanActivity.this.finish();
                }else{
                    EnfanActivity.this.finish();
                }
            }
        });
    }
}