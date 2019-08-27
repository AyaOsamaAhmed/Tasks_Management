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
import android.util.ArrayMap;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.android.volley.VolleyLog.TAG;


/**
 * Created by egypt2 on 31-Oct-18.
 */

public class AddEmployee extends Activity {

    Toolbar toolbar ;
    Button                  save_butoon;
    ArrayMap<String,String> new_employee;
    EditText                name,main_mission , username;
    Spinner                 section,department;
    Admin1Employee          admin1employee;
    String                  ls_usermane ;
    HelpClass               helpClass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_connect_addemployee);

        admin1employee=new Admin1Employee();
        helpClass     = new HelpClass();
        setToolbar();

        // define Items
        save_butoon=(Button)findViewById(R.id.save);
        name       =(EditText)findViewById(R.id.username);
        username    =(EditText)findViewById(R.id.username_login);
        section       =(Spinner) findViewById(R.id.section);
        department       =(Spinner) findViewById(R.id.department);
        main_mission       =(EditText)findViewById(R.id.main_mission);
        //
        main_mission.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (main_mission.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        // ArrayList of Spinner Department
        new_employee= new ArrayMap<String, String>();
        //ArrayList of Spinner Department
        ArrayAdapter<CharSequence> ar_one = ArrayAdapter.createFromResource(this,
                R.array.department, android.R.layout.simple_spinner_item);
        ar_one.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        department.setAdapter(ar_one);
        department.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        //department.getBackground().setColorFilter(getResources().getColor(R.color.logo), PorterDuff.Mode.SRC_ATOP);

        //ArrayList of Spinner Section
         ArrayAdapter<CharSequence> ar_two = ArrayAdapter.createFromResource(this,
                R.array.section, android.R.layout.simple_spinner_item);
        ar_two.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        section.setAdapter(ar_two);
        section.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        section.getDropDownVerticalOffset();
        //Button Save
        save_butoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //
                clickSave();
                //
            }
        });

    }
    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_one);
        // setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.add_employee);
        toolbar.setTitleTextColor(Color.rgb(255,255,255));

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar.setTitleMargin(570,0,460,0);
        } else {
            toolbar.setTitleMargin(250,0,130,20);
        }

    }

    public void fontType(){

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Ah-moharram-bold.ttf",true);
    }

    public void clickSave(){

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this , android.R.style.Theme_Black));

        builder.setMessage(R.string.ask_employee).setCancelable(false).setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                 saveData();
                Intent intent = new Intent(AddEmployee.this ,Admin1Employee.class);
                intent.putExtra("username",ls_usermane);
                startActivity(intent);

            }
        }).setNegativeButton(R.string.refuse, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();

    }
    @Override
    public void onBackPressed() {

        alartExit();
    }


    public void createNewEmployee (){
        String  id_employee ;
        int id = 1 ;
        SharedPreferences sh = getSharedPreferences("newemployee", MODE_PRIVATE);

        id = sh.getAll().size();
        id_employee = "employee_"+id;

        SharedPreferences.Editor edit = sh.edit();
        edit.putString(id_employee,username.getText().toString());
        edit.apply();
        Log.d(TAG, "createNewEmployee: "+sh.getAll());
    }
    public void saveData (){
        //
        ls_usermane    = username.getText().toString();
        //
        createNewEmployee();
        //
        SharedPreferences sh = getSharedPreferences(ls_usermane, MODE_PRIVATE);

        SharedPreferences.Editor edit = sh.edit();
        edit.putString("name",name.getText().toString());
        edit.putString("username",username.getText().toString());
        edit.putString("section",section.getSelectedItem().toString());
        edit.putString("department",department.getSelectedItem().toString());
        edit.putString("main_mission",main_mission.getText().toString());

        edit.apply();

        Toast.makeText(this,R.string.message_save,Toast.LENGTH_LONG).show();
    }

    public void alartExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light));

        builder.setMessage(R.string.alart_employee).setCancelable(false).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(AddEmployee.this ,Admin1Employee.class);
                startActivity(intent);
              //  AddEmployee.this.finish();

            }
        }).setNegativeButton(R.string.refuse, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

}

