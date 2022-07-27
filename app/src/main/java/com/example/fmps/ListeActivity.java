package com.example.fmps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListeActivity extends AppCompatActivity {
    private TextView nom, date,ide,section,massar,lieu,message,clasitem;
    private Button chercher,addeleve;
    private ImageView toggle,close;
    private ConstraintLayout head;
    private Spinner flitreclass;
    private ListView content;
    private EditText search;
    int id;
    private HashMap<String, String> map;
    private ArrayList<HashMap<String, String>> Liste;
    Dbconn conn = new Dbconn(ListeActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        nom = (TextView) findViewById(R.id.nom);
        date = (TextView) findViewById(R.id.dateitem);
        clasitem = (TextView) findViewById(R.id.classeitem);
        massar = (TextView) findViewById(R.id.code);
        lieu = (TextView) findViewById(R.id.lieu);
        ide = (TextView) findViewById(R.id.id);
        close = (ImageView) findViewById(R.id.closeenfant);
        message = (TextView) findViewById(R.id.message);
        section = (TextView) findViewById(R.id.section);
        search = (EditText) findViewById(R.id.txtchercher);
        content = (ListView) findViewById(R.id.listeitem);
        toggle = (ImageView) findViewById(R.id.toggle);
        conn.hideSystemBars(ListeActivity.this);
        head = (ConstraintLayout) findViewById(R.id.abslay);
        Liste = new ArrayList<>();
        chercher = (Button) findViewById(R.id.chercherbtn);
        addeleve = (Button) findViewById(R.id.addeleve);
        flitreclass = (Spinner) findViewById(R.id.filtreclasse);
        AlertDialog.Builder Message = new AlertDialog.Builder(ListeActivity.this);
        SimpleAdapter adapter = new SimpleAdapter(ListeActivity.this, Liste, R.layout.item, new String[]{"ide", "nom", "date", "lieu", "massar", "clasitem"}, new int[]{R.id.id, R.id.nom, R.id.dateitem, R.id.lieu, R.id.code, R.id.classeitem});
        chercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search.getText().toString().isEmpty()) {
                    Liste.clear();
                    if(flitreclass.getSelectedItem().toString().equals("Tous")){
                        Filtreclass();
                }else {
                        loaddata();

                    }
                    ViewGroup.LayoutParams params = head.getLayoutParams();
                    params.height = 150;
                    head.setLayoutParams(params);
                } else {
                        Cursor c = conn.Filtrageparchamps(flitreclass.getSelectedItem().toString(),search.getText().toString());
                        Liste.clear();
                   while (c.moveToNext()) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("ide", c.getString(0));
                        map.put("nom", c.getString(1) + " " + c.getString(2));
                        map.put("date", c.getString(4));
                        map.put("lieu", c.getString(5));
                        map.put("massar", c.getString(14));
                        map.put("clasitem", c.getString(12));
                        Liste.add(map);
                        content.setAdapter(adapter);
                    }
                   if(c.getCount()==0){
                       Toast.makeText(ListeActivity.this, "Aucun Enfant", Toast.LENGTH_SHORT).show();
                   }
                            message.setVisibility(View.INVISIBLE);
                            closekeyb();
                            ViewGroup.LayoutParams params = head.getLayoutParams();
                            params.height = 150;
                            head.setLayoutParams(params);
                            search.onEditorAction(EditorInfo.IME_ACTION_GO);


                }

                if(content.getCount()==0) {
                    message.setVisibility(View.VISIBLE);
                }
            }
        });
        content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = view.findViewById(R.id.id);
                Dbconn conn = new Dbconn(ListeActivity.this);
                final PopupMenu popup = new PopupMenu(ListeActivity.this,view);
                final MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        Cursor c = conn.consultation(t.getText().toString());
                        switch(item.getItemId()) {

                            case R.id.Consulter:
                                if (c.getCount() > 0) {
                                    Etudiant p = new Etudiant();
                                    p.setNom(c.getString(1));
                                    p.setPrenom(c.getString(2));
                                    p.setGenre(c.getString(3));
                                    p.setNaissence(c.getString(4));
                                    p.setLieu(c.getString(5));
                                    p.setDateentre(c.getString(6));
                                    p.setNomtuteur(c.getString(7));
                                    p.setPretuteur(c.getString(8));
                                    p.setCin(c.getString(9));
                                    p.setTelephone(c.getString(10));
                                    p.setTypetutur(c.getString(11));
                                    p.setClasse(c.getString(12));
                                    p.setSection(c.getString(13));
                                    p.setCodemassar(c.getString(14));
                                    p.setDouar(c.getInt(15));
                                    p.setAnnerscolaire(c.getString(16));
                                    p.setDepart(c.getString(18));
                                    p.setMotif_depart(c.getString(19));
                                    p.setBesoin(c.getString(20));
                                    Intent r = new Intent(ListeActivity.this, ConsultActivity.class);
                                    r.putExtra("etud", p);
                                    startActivity(r);
                                    finish();
                                }
                                return true;
                            case R.id.menusupprimer:
                                Message.setTitle("Confirmer La Suppression");
                                Message.setMessage("Veuillez Vraiment Supprimer Cette Enfant");
                                Message.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        conn.deleteetudiant(Integer.parseInt(t.getText().toString()));
                                        Toast.makeText(ListeActivity.this, "Supprimer Avec Succés", Toast.LENGTH_SHORT).show();

                                        Liste.clear();
                                        loaddata();

                                    }
                                });
                                Message.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                Message.show();
                                return true;
                            case R.id.menuemodifier:
                                if (c.getCount() > 0) {
                                    Etudiant p = new Etudiant();
                                    p.setId(Integer.parseInt(c.getString(0)));
                                    p.setNom(c.getString(1));
                                    p.setPrenom(c.getString(2));
                                    p.setGenre(c.getString(3));
                                    p.setNaissence(c.getString(4));
                                    p.setLieu(c.getString(5));
                                    p.setDateentre(c.getString(6));
                                    p.setNomtuteur(c.getString(7));
                                    p.setPretuteur(c.getString(8));
                                    p.setCin(c.getString(9));
                                    p.setTelephone(c.getString(10));
                                    p.setTypetutur(c.getString(11));
                                    p.setClasse(c.getString(12));
                                    p.setSection(c.getString(13));
                                    p.setCodemassar(c.getString(14));
                                    p.setDouar(c.getInt(15));
                                    p.setAnnerscolaire(c.getString(16));
                                    p.setDepart(c.getString(18));
                                    p.setMotif_depart(c.getString(19));
                                    p.setBesoin(c.getString(20));
                                    Intent i = new Intent(ListeActivity.this, ModifierActivity.class);

                                    i.putExtra("etud", p);
                                    startActivity(i);
                                    finish();
                                    return true;
                                }
                        }
                        return false;
                    }
                });
            }
        });
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = head.getLayoutParams();
                if(params.height != 150){
                     params.height = 150;
                     if(content.getCount()==0) {
                         message.setVisibility(View.VISIBLE);
                     }
                }else{
                    params.height=1100;
                    message.setVisibility(View.INVISIBLE);
                }
                head.setLayoutParams(params);
            }
        });
        addeleve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  n = new Intent(ListeActivity.this , EnfanActivity.class);
                startActivity(n);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListeActivity.this , MenueActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void loaddata() {
        try (

                Cursor c = conn.getAllEnfants(flitreclass.getSelectedItem().toString())) {
            while (c.moveToNext()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ide", c.getString(0));
                map.put("nom", c.getString(1) + " " + c.getString(2));
                map.put("date", c.getString(4));
                map.put("lieu", c.getString(5));
                map.put("massar", c.getString(14));
                map.put("clasitem", c.getString(12));
                Liste.add(map);
                message.setVisibility(View.INVISIBLE);
                SimpleAdapter adapter = new SimpleAdapter(ListeActivity.this, Liste, R.layout.item, new String[]{"ide", "nom","date","lieu","massar","clasitem"}, new int[]{R.id.id, R.id.nom,R.id.dateitem,R.id.lieu,R.id.code,R.id.classeitem});
                content.setAdapter(adapter);
            }
        }catch(Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public void Filtreclass() {
        try (
                Cursor c = conn.getAllcount()) {
            while (c.moveToNext()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ide", c.getString(0));
                map.put("nom", c.getString(1) + " " + c.getString(2));
                map.put("date", c.getString(4));
                map.put("lieu", c.getString(5));
                map.put("massar", c.getString(14));
                map.put("clasitem", c.getString(12));
                Liste.add(map);
                message.setVisibility(View.INVISIBLE);

                SimpleAdapter adapter = new SimpleAdapter(ListeActivity.this, Liste, R.layout.item, new String[]{"ide", "nom","date","lieu","massar","clasitem"}, new int[]{R.id.id, R.id.nom,R.id.dateitem,R.id.lieu,R.id.code,R.id.classeitem});
                content.setAdapter(adapter);
            }
        }catch(Exception ex){
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    public  void closekeyb(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE );
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }


}