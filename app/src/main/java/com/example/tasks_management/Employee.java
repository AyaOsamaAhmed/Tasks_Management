package com.example.tasks_management;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by egypt2 on 02-Oct-18.
 */

public class Employee extends Activity {

    String          username ,password , ls_username;
    Toolbar toolbar ;
    Button          extrmiss ;
    TextView        name_tx , section_tx , department_tx , main_mission_tx ;
    HelpClass       helpclass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_connect_employee);
        //
        helpclass = new HelpClass();
        ls_username = getIntent().getStringExtra("username");

        Log.d(TAG, "onCreate: " + ls_username);
        //
        name_tx=(TextView)findViewById(R.id.tx_name);
        section_tx=(TextView)findViewById(R.id.tx_section);
        department_tx=(TextView)findViewById(R.id.tx_deparment);
        main_mission_tx=(TextView)findViewById(R.id.tx_mission);
        //
        setToolbar();

        retrieveLogin();
        //
        if (username.equals("Admin")) {
        toolbar.setTitle(R.string.admin_employee);}
    }

    private void clickingButtons( String login) {

        extrmiss = (Button)findViewById(R.id.extrmiss);
        if (username.equals("Admin")) {

            extrmiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Employee.this, AdminEmployeeExtr.class);
                    intent.putExtra("username",ls_username);
                    startActivity(intent);
                }
            });
        }else
        {
            extrmiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Employee.this, EmployeeExtr.class);
                    intent.putExtra("username",ls_username);
                    startActivity(intent);
                }
            });

        }
    }

    public void fontType(){

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Ah-moharram-bold.ttf",true);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_one);
        // setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.employee);
        toolbar.setTitleTextColor(Color.rgb(255,255,255));
        //
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar.setTitleMargin(400,0,460,0);
        } else {
            toolbar.setTitleMargin(180,0,30,20);
        }

    }
    @Override
    public void onBackPressed() {


        if (username.equals("Admin")){
            Intent intent = new Intent(Employee.this ,Admin1Employee.class);
            intent.putExtra("username",ls_username);
            startActivity(intent);
        }else
        {
            alartExit();
        }
    }

    public void alartExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Black));

        // builder.
        builder.setMessage(R.string.logout).setCancelable(false).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Employee.this ,Login.class);
                startActivity(intent);

            }
        }).setNegativeButton(R.string.refuse, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    public void retrieveData () {
        // retrive Data
        SharedPreferences sh = getSharedPreferences(ls_username, MODE_PRIVATE);

        String name = sh.getString("name", "");
        String section = sh.getString("section", "");
        String department = sh.getString("department", "");
        String main_mission = sh.getString("main_mission", "");
        Log.d(TAG, "retrieveData: "+name);

            name_tx.setText(name);
            section_tx.setText(section);
            department_tx.setText(department);
            main_mission_tx.setText(main_mission);
    }

    public void retrieveLogin () {
        // retrive Data
        SharedPreferences sh = getSharedPreferences("Login", MODE_PRIVATE);

         username = sh.getString("name", "");
         password = sh.getString("password", "");
        Log.d(TAG, "retrieveLogin: "+username);
       // Toast.makeText(this, username+"****", Toast.LENGTH_LONG).show();
        if (username == null || username.isEmpty()){
           // name_tx.setText("الموظف  ");
        }else {
            retrieveData();
        }

        clickingButtons(username);

    }

}
