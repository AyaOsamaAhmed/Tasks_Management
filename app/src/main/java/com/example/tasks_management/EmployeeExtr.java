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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by egypt2 on 22-Oct-18.
 */

public class EmployeeExtr extends Activity {

    Toolbar toolbar ;
    ListView list_view ;
    Button         mainemployee ;
    String         ls_username;
    ArrayList<HashMap<String,String>> arrayList_employee;
    HashMap<String,String>      hash_employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_connect_employee_extr);
        list_view = (ListView)findViewById(R.id.list_item);
        arrayList_employee=new ArrayList<HashMap<String,String>>();
        hash_employees = new HashMap<String, String>();

        //
        ls_username = getIntent().getStringExtra("username");
        //
        setToolbar();

        clickingButtons();
        //
        retrieveemployee();
        ListViewAdapterExtr adapter = new ListViewAdapterExtr(EmployeeExtr.this, arrayList_employee ,ls_username );
        list_view.setAdapter(adapter);

    }

    private void retrieveemployee() {
        int len , id = 0;
        String  id_additionmission = "" , id_employee , employee="" ,username_employee = "";
        String  key_Extr = "additionmission"+ls_username ;

        SharedPreferences sh = getSharedPreferences(ls_username, MODE_PRIVATE);
        len =  sh.getAll().size()- 5;

        for( ;id < len;id++ ){
            id_additionmission = "addition_"+id+"_"+ls_username;
            id_employee=   sh.getString(id_additionmission,"");

            hash_employees.put( id_additionmission,id_employee);
            Log.d(TAG, "retrieveemployee: hash_employee"+hash_employees);
        }
        arrayList_employee.add(hash_employees);
    }

    private void clickingButtons() {

        mainemployee = (Button)findViewById(R.id.mainemployee);

        mainemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeExtr.this ,Employee.class);
                intent.putExtra("username",ls_username);
                startActivity(intent);
            }
        });

    }


    public void retrieveData () {
        // retrive Data
        SharedPreferences sh = getSharedPreferences("AdditionMission", MODE_PRIVATE);
        String addMission_data = sh.getString("addMission", "");

    }

    public String retrieveData (String  employee) {
        // retrive Data
        SharedPreferences sh = getSharedPreferences(employee, MODE_PRIVATE);

        return null;
    }
    public void fontType(){

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Ah-moharram-bold.ttf",true);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_one);
        // setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.add_employee_extr);
        toolbar.setTitleTextColor(Color.rgb(255,255,255));
        //
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar.setTitleMargin(600,0,460,0);
        } else {
            toolbar.setTitleMargin(280,0,130,20);
        }

    }

    @Override
    public void onBackPressed() {
        alartExit();
    }
    public void alartExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Black));

        // builder.
        builder.setMessage(R.string.logout).setCancelable(false).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(EmployeeExtr.this ,Login.class);
                startActivity(intent);

            }
        }).setNegativeButton(R.string.refuse, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

}
