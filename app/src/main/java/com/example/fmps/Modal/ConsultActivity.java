package com.example.fmps.Modal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fmps.Etudiant;
import com.example.fmps.R;

public class ConsultActivity extends AppCompatActivity {
        private ConstraintLayout personell ;
        private TextView Nomcon , Prenomcon,Telcon,Tutuercon,datecon,cincon,typecon,genrecon,lieu,massar,section,classe,depart,besoin,motif,douare,annescolaire,entre ;
        private ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        personell =(ConstraintLayout) findViewById(R.id.personnel);
        ImageView img= (ImageView) findViewById(R.id.left);
        Nomcon = (TextView) findViewById(R.id.nomconsult);
        Prenomcon = (TextView) findViewById(R.id.prenomconsult);
        Telcon = (TextView) findViewById(R.id.telcon);
        datecon = (TextView) findViewById(R.id.dateconsult);
        cincon = (TextView) findViewById(R.id.cincon);
        typecon = (TextView) findViewById(R.id.typecon);
        Tutuercon = (TextView) findViewById(R.id.tutrconsult);
        genrecon = (TextView) findViewById(R.id.genreconsult) ;
        lieu = (TextView) findViewById(R.id.lieuconsult) ;
        massar = (TextView) findViewById(R.id.masarconsult) ;
        section = (TextView) findViewById(R.id.sectionconsult) ;
        classe = (TextView) findViewById(R.id.classeconsult) ;
        depart = (TextView) findViewById(R.id.departcosult) ;
        besoin = (TextView) findViewById(R.id.besoinconsult) ;
        motif = (TextView) findViewById(R.id.motficonsul) ;
        entre = (TextView) findViewById(R.id.entreconsult) ;
        douare = (TextView) findViewById(R.id.douarconsult) ;
        annescolaire = (TextView) findViewById(R.id.anneconsult) ;
        close = (ImageView) findViewById(R.id.close);
        Etudiant p = (Etudiant)getIntent().getSerializableExtra("etud");
        if(p != null) {
            Nomcon.setText(p.getNom());
            Prenomcon.setText(p.getPrenom());
            datecon.setText(p.getNaissence());
            cincon.setText(p.getCin());
            Telcon.setText(p.getTelephone());
            typecon.setText(p.getTypetutur());
            Tutuercon.setText(p.getNomtuteur() +" "+p.getPretuteur());
            lieu.setText(p.getLieu());
            massar.setText(p.getCodemassar());
            section.setText(p.getSection());
            classe.setText(p.getClasse());
            int i = p.getDouar();
            douare.setText(String.valueOf(i));
            depart.setText(p.getDepart());
            besoin.setText(p.getBesoin());
            annescolaire.setText(p.getAnnerscolaire());
            genrecon.setText(p.getGenre());
            entre.setText(p.getDateentre());
            motif.setText(p.getMotif_depart());
        }
        personell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = personell.getLayoutParams();
                if(params.height != 2000) {
                    params.height = 2000;
                    img.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);

                }else{
                    params.height = 80;
                    img.setImageResource(R.drawable.ic_baseline_keyboard_arrow_left_24);
                }
                personell.setLayoutParams(params);

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConsultActivity.this , ListeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}