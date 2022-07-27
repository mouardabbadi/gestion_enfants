package com.example.fmps;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLCONNECTION {
    private static String ip = "185.4.176.94";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "pre_production";
    private static String username = "stagiaire";
    private static String password = "b3WjPx^@7CSmc";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;
    public Connection connection = null;
    public void GetConnection(Context Activity){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            Toast.makeText(Activity, "Succes", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(Activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(Activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
