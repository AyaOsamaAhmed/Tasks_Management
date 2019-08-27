package com.example.tasks_management;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.content.ContentValues.TAG;

/**
 * Created by egypt2 on 30-Aug-18.
 */

public class Login extends Activity {
    TextView      tv_toolbar ;
    EditText     username , password ;
    Button       login  ;
    String       ls_username ,ls_password, Result_check ;
    Long         pressBack;
    boolean      check;
    final Object o_name = "Admin";
    Typeface      typeface_moharram ;
    HelpClass       helpClass;
    HashMap<String,String> hashParam;
    //
    TextView start, tab_start , tab_Privacy ,Privacy , Login, tab_login ,add_client , tab_add_client , tab_contact_us , tab_recordclients;
    private int height_start , height_Privacy , height_Login, height_add_client , height_contact_us , height_recordclients ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tv_toolbar = (TextView)findViewById(R.id.toolbar);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.button_login);
        helpClass = new HelpClass();
        hashParam = new HashMap<String, String>();

        //

        typeface_moharram=Typeface.createFromAsset(getAssets(),"fonts/Ah-moharram-bold.ttf");
       // tv_toolbar.setTypeface(typeface_moharram);

        //Clicking Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        saveData();
        // if (checkNetwork()) {                                                                //AVILABLE NETWORK
        //check for user & pass
        ls_username = username.getText().toString();
        ls_password = password.getText().toString();
        hashParam.put("username",ls_username);
        hashParam.put("password",ls_password);
//        Result_check = helpClass.GETStringRequest(helpClass.URL_Login, hashParam );
        //
            if (ls_username.equals("Admin")){                                   // (Result_check == "True" && username.equals("Admin"))
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("username",ls_username);
                startActivity(intent);
              //  Log.d(TAG, "onClick: Admin--" + username.getText());
            } else if (!ls_username.equals("Admin")) {                         //(Result_check == "True" )
                Intent intent = new Intent(Login.this, Employee.class);
                intent.putExtra("username",ls_username);
                startActivity(intent);
                Log.d(TAG, "onClick: else--"+username.getText());
            }
        //}
      //  else {
                   // Toast.makeText(getApplicationContext(), "Welcome " + username.getText().toString() + "\n Sorry Webservice don't work Now \n Try again later", Toast.LENGTH_LONG).show();
                   // alart("I'm Sorry, Your Internet don't connect \n Please,Connect WiFi OR Open Mobile Data");

                //}
            }
        });
        //---------------
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                createDeclearLayout();
            }
        });
    }

    private void createDeclearLayout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

        View listViewClient = getLayoutInflater().inflate(R.layout.layout_phone, null);
        start = (TextView) listViewClient.findViewById(R.id.start);
        tab_start = (TextView) listViewClient.findViewById(R.id.tab_start);

        Privacy = (TextView) listViewClient.findViewById(R.id.Privacy);
        tab_Privacy = (TextView) listViewClient.findViewById(R.id.tab_Privacy);

        Login = (TextView)listViewClient.findViewById(R.id.login);
        tab_login = (TextView)listViewClient.findViewById(R.id.tab_login);

        add_client = (TextView) listViewClient.findViewById(R.id.add_client);
        tab_add_client = (TextView) listViewClient.findViewById(R.id.tab_add_client);


        tab_recordclients=(TextView)listViewClient.findViewById(R.id.tab_record_client);

        tab_contact_us=(TextView)listViewClient.findViewById(R.id.tab_contact_us);
        builder.setNegativeButton("إغلاق", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }

        });
        builder.setView(listViewClient);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //----------- set height
        setHeight();

        //------------

    }


    public void fontType(){

        Calligrapher calligrapher = new Calligrapher(this);
            calligrapher.setFont(this,"fonts/Ah-moharram-bold.ttf",true);
    }

    public void saveData (){

        SharedPreferences sh = getSharedPreferences("Login", MODE_PRIVATE);

        SharedPreferences.Editor edit = sh.edit();
        edit.putString("name",username.getText().toString());
        edit.putString("password",password.getText().toString());

        edit.apply();

       // Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
    }

    public void setHeight(){
        getHeight("S", tab_start.getHeight());
        getHeight("P", tab_Privacy.getHeight());
        getHeight("L",tab_login.getHeight());
        getHeight("C", tab_add_client.getHeight());
        getHeight("R", tab_recordclients.getHeight());
        getHeight("Contact", tab_contact_us.getHeight());

    }

    public void setHeightZero(){
        tab_start.setHeight(0);
        tab_Privacy.setHeight(0);
        tab_login.setHeight(0);
        tab_add_client.setHeight(0);
        tab_recordclients.setHeight(0);
        tab_contact_us.setHeight(0);
    }

    private void getHeight(String s, int height) {
        if (height != 0) {
            switch (s) {
                case "S":
                    height_start = height;
                    break;
                case "P":
                    height_Privacy = height;
                    break;
                case "L":
                    height_Login = height;
                    break;
                case "C":
                    height_add_client = height;
                    break;
                case "R":
                    height_recordclients=height;
                    break;
                case "Contact":
                    height_contact_us = height ;
                    break;
            }

        }
    }

    public void tabStart(View view) {
        setHeight();
        setHeightZero();
        tab_start.setHeight(height_start);
        // width , eight
    }

    public void tabPrivacy(View view) {
        setHeight();
        setHeightZero();
        tab_Privacy.setHeight(height_Privacy);
        // width , height
    }

    public void tabAdd_client(View view) {
        setHeight();
        setHeightZero();
        tab_add_client.setHeight(height_add_client);
        // width , height

    }

    public void tabContact_us(View view) {

        setHeight();
        setHeightZero();
        tab_contact_us.setHeight(height_contact_us);
        // width , height
    }

    public void tab_recordclient(View view) {

        setHeight();
        setHeightZero();
        tab_recordclients.setHeight(height_recordclients);
        // width , height
    }


    public void tablogin(View view) {
        setHeight();
        setHeightZero();
        tab_login.setHeight(height_Login);
        // width , height
    }
    @Override
    public void onBackPressed() {
        alartExit();
    }

    public void alart(String message) {
        AlertDialog al = new AlertDialog.Builder(this).create();
        al.setMessage(message);
        al.setButton("O.K", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        al.show();
    }

    private boolean checkNetwork() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            check=true;
        }else check=false;
        return check;
    }
    public void alartExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
