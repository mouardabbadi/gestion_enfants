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

public class ModifierActivity extends AppCompatActivity {
    Spinner spingenre;
    private Button Modifier;
    private EditText nom,prenom,datetime,dateentre,lieu,nomtutur,prenomtutur,telephone,cin,motif,depart,massar;
    private Spinner genre,type,besoin,classe,section;
    private ImageView close;
    private int ideleve;
    DatePickerDialog.OnDateSetListener onDatadate;
    DatePickerDialog.OnDateSetListener onDateentre;
    DatePickerDialog.OnDateSetListener onDatadepart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        Dbconn conn = new Dbconn(ModifierActivity.this);
        spingenre= (Spinner) findViewById(R.id.downgenre);//fetch the spinner from layout file
        conn.rempliredown(spingenre,ModifierActivity.this,getResources().getStringArray(R.array.country_array));
        Modifier = findViewById(R.id.add);
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
        depart = (EditText) findViewById(R.id.depart);
        Calendar calendar =Calendar.getInstance();
        final int annescolaire=calendar.get(Calendar.YEAR);
        datetime = (EditText) findViewById(R.id.datetime);
        Etudiant p = (Etudiant)getIntent().getSerializableExtra("etud");
        AlertDialog.Builder r = new AlertDialog.Builder(ModifierActivity.this);
        if(p != null) {
            ideleve = p.getId();
            nom.setText(p.getNom());
            prenom.setText(p.getPrenom());
            datetime.setText(p.getNaissence());
            cin.setText(p.getCin());
            telephone.setText(p.getTelephone());
            nomtutur.setText(p.getNomtuteur());
            prenomtutur.setText(p.getPretuteur());
            massar.setText(p.getCodemassar());
            depart.setText(p.getDepart());
            dateentre.setText(p.getDateentre());
            lieu.setText(p.getLieu());
            motif.setText(p.getMotif_depart());
            conn.selectSpinnerValue(classe,p.getClasse().toString());
            conn.selectSpinnerValue(besoin,p.getBesoin().toString());
            conn.selectSpinnerValue(section,p.getSection().toString());
            conn.selectSpinnerValue(genre,p.getGenre().toString());
            conn.selectSpinnerValue(section,p.getSection().toString());
        }
        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int year=calendar.get(Calendar.YEAR);
                final int month=calendar.get(Calendar.MONTH);
                final int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        ModifierActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                        ModifierActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateentre,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
                datetime.setText(day+"/"+month+"/"+year);
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
                        ModifierActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
        Modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nom.getText().equals("") && !prenom.getText().equals("") && !datetime.getText().equals("") && !dateentre.getText().equals("") && !genre.getSelectedItem().equals("") && !lieu.getText().equals("") && !depart.getText().equals("")) {
                    r.setTitle("Completer");
                    r.setMessage("Veuillez Entre tous les champs Obligatoire");
                    r.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    r.show();
                } else {
                    Etudiant p = new Etudiant(nom.getText().toString(), prenom.getText().toString(), genre.getSelectedItem().toString(), datetime.getText().toString(), dateentre.getText().toString(), lieu.getText().toString(), nomtutur.getText().toString(), prenomtutur.getText().toString(), type.getSelectedItem().toString(), telephone.getText().toString(), cin.getText().toString(), massar.getText().toString(), classe.getSelectedItem().toString(), section.getSelectedItem().toString(), depart.getText().toString(), 1, String.valueOf(annescolaire), motif.getText().toString(), besoin.getSelectedItem().toString());
                    r.setTitle("Confirmer La modification");
                    r.setMessage("Veuillez Vraiment Modifier Cette Enfant");
                    r.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            conn.updateetudiant(p, ideleve);
                            Intent i = new Intent(ModifierActivity.this, ListeActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });
                    r.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    r.show();

                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModifierActivity.this , ListeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}