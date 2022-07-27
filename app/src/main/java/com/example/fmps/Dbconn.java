package com.example.fmps;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

class Dbconn extends SQLiteOpenHelper {
    private Context context;
    public static final String Database = "Enfante.db";

    public Dbconn(@Nullable Context context) {
        super(context, Database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE erp_etudiants(id  INTEGER PRIMARY KEY AUTOINCREMENT,Nom_enfant TEXT,Prenom_enfant Text , Genre TEXT,Date_de_naissance TEXT,Lieu_de_naissance TEXT ,Date_entree TEXT,Nom_tuteur TEXT,Prenom_tuteur TEXT,CIN_tuteur TEXT,Telephone TEXT,Type_de_parente TEXT,Classe TEXT,Section TEXT,Massar Text,Id_douar int,anner_scolaire text,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,date_depart TEXT,motif_depart TEXT,besoin_specifique TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS erp_etudiants");
        onCreate(db);
    }
    public void deletetable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP table erp_etudiants ");

    }
    public boolean insert(Etudiant p, Context cn) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("Nom_enfant", p.getNom());
            cv.put("Prenom_enfant", p.getPrenom());
            cv.put("Genre", p.getGenre());
            cv.put("Date_de_naissance", p.getNaissence());
            cv.put("Lieu_de_naissance", p.getLieu());
            cv.put("Date_entree", p.getDateentre());
            cv.put("Nom_tuteur", p.getNomtuteur());
            cv.put("Prenom_tuteur", p.getPretuteur());
            cv.put("CIN_tuteur", p.getCin());
            cv.put("Telephone", p.getTelephone());
            cv.put("Type_de_parente", p.getTypetutur());
            cv.put("Classe", p.getClasse());
            cv.put("Section", p.getSection());
            cv.put("Massar", p.getCodemassar());
            cv.put("Id_douar", p.getDouar());
            cv.put("anner_scolaire", p.getAnnerscolaire());
            cv.put("date_depart", p.getDepart());
            cv.put("motif_depart", p.getMotif_depart());
            cv.put("besoin_specifique", p.getBesoin());
            long operation = db.insert("erp_etudiants", null, cv);
            if (operation == -1)
                Toast.makeText(cn, "No Insert", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(cn, "Succes", Toast.LENGTH_SHORT).show();
        } catch (Exception x) {
            Toast.makeText(cn, "Erreur", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    public void updateetudiant(Etudiant p, int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("Nom_enfant", p.getNom());
            cv.put("Prenom_enfant", p.getPrenom());
            cv.put("Genre", p.getGenre());
            cv.put("Date_de_naissance", p.getNaissence());
            cv.put("Lieu_de_naissance", p.getLieu());
            cv.put("Date_entree", p.getDateentre());
            cv.put("Nom_tuteur", p.getNomtuteur());
            cv.put("Prenom_tuteur", p.getPretuteur());
            cv.put("CIN_tuteur", p.getCin());
            cv.put("Telephone", p.getTelephone());
            cv.put("Type_de_parente", p.getTypetutur());
            cv.put("Classe", p.getClasse());
            cv.put("Section", p.getSection());
            cv.put("Massar", p.getCodemassar());
            cv.put("Id_douar", p.getDouar());
            cv.put("anner_scolaire", p.getAnnerscolaire());
            cv.put("date_depart", p.getDepart());
            cv.put("motif_depart", p.getMotif_depart());
            cv.put("besoin_specifique", p.getBesoin());
            db.update("erp_etudiants", cv, "id=?", new String[]{String.valueOf(id)});

        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteetudiant(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("erp_etudiants", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }



    public Cursor getAllcount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM erp_etudiants",null);
        return c;
    }
    public Cursor getAllEnfants(String Emp) {
        Cursor cursor = null;
        String empName = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM erp_etudiants WHERE Classe=?";
        cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Emp) });
        return cursor;
    }
    public Cursor Filtrageparchamps(String Emp,String Em) {
        Cursor cursor = null;
        String empName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT *  FROM erp_etudiants WHERE Classe=?  AND  Nom_enfant = ? OR Prenom_enfant LIKE ? OR Massar LIKE ?";
        cursor = db.rawQuery(select, new String[] { String.valueOf(Emp) ,String.valueOf(Em)  ,String.valueOf(Em) ,String.valueOf(Em)   });
        return cursor;
    }

    public Cursor consultation(String empNo) {
        Cursor cursor = null;
        String empName = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM erp_etudiants WHERE id=?";
        cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(empNo) });

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
        }
        return cursor;

    }

    public void hideSystemBars(Activity activity) {
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController((activity.getWindow().getDecorView()));
        if (windowInsetsController == null) {
            return;
        }
        // Configure the behavior of the hidden system bars
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
    }
    public void rempliredown(Spinner txt , Context c , String [] as ) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(c,
                android.R.layout.simple_spinner_item, as);//setting the country_array to spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txt.setAdapter(adapter);
    }

    public void selectSpinnerValue(Spinner spinner, String myString)
    {
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equals(myString)){
                spinner.setSelection(i);
                break;
            }
        }
    }



}