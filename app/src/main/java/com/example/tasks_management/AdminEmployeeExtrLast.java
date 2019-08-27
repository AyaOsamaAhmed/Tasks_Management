package com.example.tasks_management;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.android.volley.VolleyLog.TAG;

public class AdminEmployeeExtrLast extends Activity {

    Toolbar toolbar ;
    ListView        list_view ;
    EditText        addMission;
    String          ls_username , notivication_message;
    ArrayList<HashMap<String,String>> arrayList_employee;
    HashMap<String,String>      hash_employees;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_connect_admin_employee_extr_last);

        list_view = (ListView)findViewById(R.id.list_additionitem_last);
        addMission = (EditText)findViewById(R.id.adding_mission);
        arrayList_employee=new ArrayList<HashMap<String,String>>();
        hash_employees = new HashMap<String, String>();

        //
        ls_username = getIntent().getStringExtra("username");
        //
        setToolbar();

        //
        retrieveemployee();
        Log.d(TAG, "last mission: " + arrayList_employee);
         ListViewAdapterExtr adapter = new ListViewAdapterExtr(AdminEmployeeExtrLast.this, arrayList_employee ,ls_username );
         list_view.setAdapter(adapter);
        //

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_one);
        // setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.last_mission);
        toolbar.setTitleTextColor(Color.rgb(255,255,255));
        //
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar.setTitleMargin(600,0,460,0);
        } else {
            toolbar.setTitleMargin(180,0,100,20);
        }

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


    @Override
    public void onBackPressed() {
        //  alartExit();
        Intent intent = new Intent(AdminEmployeeExtrLast.this ,AdminEmployeeExtr.class);
        intent.putExtra("username",ls_username);
        startActivity(intent);
    }
}
