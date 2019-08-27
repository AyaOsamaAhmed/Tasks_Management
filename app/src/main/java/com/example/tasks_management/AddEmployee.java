package com.example.tasks_management;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.android.volley.VolleyLog.TAG;


/**
 * Created by egypt2 on 31-Oct-18.
 */

public class AddEmployee extends Activity {

    Toolbar toolbar ;
    Button                  save_butoon;
    ArrayMap<String,String> new_employee;
    String                  ls_id,ls_name,ls_username,ls_password,ls_section,ls_department,ls_main_mission;
    EditText                name,main_mission , username , password;
    Spinner                 section,department;
    Admin1Employee          admin1employee;
    String                  ls_usermane , databasename;;
    HelpClass               helpClass ;
    FirebaseFirestore   databaseEmployee ;
    DatabaseReference   databaseReference;
    DataEmployee        dataEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_connect_addemployee);

        admin1employee = new Admin1Employee();
        helpClass = new HelpClass();
        setToolbar();
        // Database name
        databasename = "New_Employee";
        //
        FirebaseApp.initializeApp(AddEmployee.this);
        databaseEmployee = FirebaseFirestore.getInstance();
        // databaseReference = FirebaseDatabase.getInstance().getReference(databasename);
        // databaseReference.keepSynced(true);
        // define Items
        save_butoon = (Button) findViewById(R.id.save);
        name = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        username = (EditText) findViewById(R.id.username_login);
        section = (Spinner) findViewById(R.id.section);
        department = (Spinner) findViewById(R.id.department);
        main_mission = (EditText) findViewById(R.id.main_mission);
        //
        main_mission.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (main_mission.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        // ArrayList of Spinner Department
        new_employee = new ArrayMap<String, String>();
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
                // saveData();
                saveDataFirebase();
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

/*
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
    */
    public void saveData (){
        //
        ls_usermane    = username.getText().toString();
        //
       // createNewEmployee();
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

    public void saveDataFirebase(){
        //set data
        setData();
        //validation
        if(validation_data()) {
            String  id = "1";
            ls_id = id ;
            dataEmployee = new DataEmployee(ls_id ,ls_name,ls_username,ls_password,ls_section,ls_department,ls_main_mission);

            databaseReference.child(id).setValue(dataEmployee);

            /* Create a new user with a first and last name
            Map<String, Object> user = new HashMap<>();
            user.put("employee_id", databaseReference.push().getKey());
            user.put("name", name.getText().toString());
            user.put("username", username.getText().toString());
            user.put("password", password.getText().toString());
            user.put("section", section.getSelectedItem().toString());
            user.put("department", department.getSelectedItem().toString());
            user.put("main_mission", main_mission.getText().toString());
            */
            // Add a new document with a generated ID
            databaseEmployee.collection("New_employee")
                    .add(dataEmployee)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });


            Toast.makeText(this, R.string.message_save, Toast.LENGTH_LONG).show();
        }
    }

    private Boolean validation_data() {
        if (ls_name.isEmpty()) {Toast.makeText(this, "من فضلك... قم بإدخال اسم الموظف", Toast.LENGTH_SHORT).show(); return false ;}
        if (ls_password.isEmpty() || ls_password.length() < 11){Toast.makeText(this, "من فضلك... قم بإدخال كلمة المرور", Toast.LENGTH_SHORT).show(); return false ;}
        //  if (ls_card.isEmpty()) {Toast.makeText(this, "من فضلك... قم بإدخال رقم عضويه العميل", Toast.LENGTH_SHORT).show(); return false ;}
        if (ls_username.isEmpty()) {Toast.makeText(this, "من فضلك... قم بإدخال اسم المستخدم الخاص بالموظف", Toast.LENGTH_SHORT).show(); return false ;}
        if (ls_section.isEmpty()) {Toast.makeText(this, "من فضلك... قم بإختيار القسم الخاص بالعميل", Toast.LENGTH_SHORT).show(); return false ;}
        if (ls_department.isEmpty()) {Toast.makeText(this, "من فضلك... قم بإختيار الإدارة التابع لها الموظف", Toast.LENGTH_SHORT).show(); return false ;}
        if (ls_main_mission.isEmpty()) {Toast.makeText(this, "من فضلك... قم بإدخال المهام الاساسية للموظف", Toast.LENGTH_SHORT).show(); return false ;}

        return true ;
    }
    private void setData () {
        ls_name = name.getText().toString();
        ls_username = username.getText().toString();
        ls_password = password.getText().toString();
        ls_section = section.getSelectedItem().toString();
        ls_department = department.getSelectedItem().toString();
        ls_main_mission = main_mission.getText().toString();

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

