package com.example.tasks_management;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by egypt2 on 05-Nov-18.
 */

public class AdminEmployeeExtr extends Activity {

    Toolbar toolbar ;
    Button          mainemployee,extrMission , extrMissionLast , B_attachment ;
    EditText        addMission;
    TextView        TV_current_data , TV_end_task , TV_current_time , TV_attachment_file;
    String          ls_username , notivication_message , formattedDate , formattedDateTime;
    Spinner         SP_choose_status;
    private static final int PICKFILE_RESULT_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_connect_admin_employee_extr);

        TV_current_data=(TextView) findViewById(R.id.current_data);
        TV_current_time=(TextView)findViewById(R.id.current_time);
        TV_end_task = (TextView)findViewById(R.id.date_time);
        TV_attachment_file=(TextView)findViewById(R.id.attachment_file);
        SP_choose_status=(Spinner)findViewById(R.id.choose_status);
        B_attachment=(Button)findViewById(R.id.bt_attachment);
        addMission = (EditText)findViewById(R.id.adding_mission);
        //
        ls_username = getIntent().getStringExtra("username");
        //
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.SP_choose_status, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
// Apply the adapter to the spinner
        SP_choose_status.setAdapter(adapter);
        SP_choose_status.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        //
        setToolbar();
        //
        clickingButtons();
        dataTime();
        TV_end_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTask();
            }
        });
        //
        B_attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachmentFile();
            }
        });
        //
        /*
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config =
                getBaseContext().getResources().getConfiguration();
        config.setLocale(locale);
        createConfigurationContext(config);
        */
    }

    private void attachmentFile() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,PICKFILE_RESULT_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch(requestCode){
            case PICKFILE_RESULT_CODE:
                if(resultCode==RESULT_OK){
                    String FilePath = data.getData().getPath();
                    TV_attachment_file.setText(FilePath);
                }
                break;

        }
    }

    private void endTask() {
        // arabic calendar
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        final Calendar calendar = Calendar.getInstance(locale);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(AdminEmployeeExtr.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Locale locale = new Locale("ar");
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd" ,locale);
                        Date date = new Date(year-1900,month,day);
                        formattedDate = df.format(date);
                        TV_end_task.setText(formattedDate);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
        // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        //  DialogFragment newFragment = new DataPickerFragment();
        // newFragment.show(getFragmentManager(),"datepicker");
    }


    private void dataTime() {
        Calendar c = Calendar.getInstance();
        Locale locale = new Locale("ar");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd" ,locale);
         formattedDate = df.format(c.getTime());
        TV_current_data.setText(formattedDate);
        SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm" ,locale);
        formattedDateTime = dfTime.format(c.getTime());
        TV_current_time.setText(formattedDateTime);

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_one);
        // setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.add_admin_extr);
        toolbar.setTitleTextColor(Color.rgb(255,255,255));
        //
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar.setTitleMargin(600,0,460,0);
        } else {
            toolbar.setTitleMargin(180,0,100,20);
        }

    }

    private void clickingButtons() {
        mainemployee = (Button)findViewById(R.id.mainemployee);

        mainemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminEmployeeExtr.this ,Employee.class);
                intent.putExtra("username",ls_username);
                startActivity(intent);
            }
        });

        extrMission = (Button)findViewById(R.id.button_mission);

        extrMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               saveData();
                addMission.setText("");
            }
        });

        extrMissionLast = (Button)findViewById(R.id.button_last_mission);
        extrMissionLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent extrmisionlast = new Intent(AdminEmployeeExtr.this , AdminEmployeeExtrLast.class);
                extrmisionlast.putExtra("username",ls_username);
                startActivity(extrmisionlast);
            }
        });
    }

    public void saveData (){
        String  id_additionmission , key_Extr ;
        int id = 1 ;
        key_Extr = "additionmission"+ls_username ;
        SharedPreferences sh = getSharedPreferences(key_Extr, MODE_PRIVATE);
        id = sh.getAll().size();
        id_additionmission = "addition_"+id+"_"+ls_username;

        SharedPreferences.Editor edit = sh.edit();
        edit.putString(id_additionmission,addMission.getText().toString());

        edit.apply();
        //
        SharedPreferences sh_employee = getSharedPreferences(ls_username, MODE_PRIVATE);
        SharedPreferences.Editor edit_employee = sh_employee.edit();
        edit_employee.putString(id_additionmission,addMission.getText().toString());

        edit_employee.apply();
        Log.d(TAG, "saveData: employee " +sh_employee.getAll().toString());
        Log.d(TAG, "saveData: employee addition " +sh.getAll().toString());
        //
        Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
        // Notivication
        notivication_message=addMission.getText().toString();
            Notivication("Addition Mission" , notivication_message);
        //
    }

    private void Notivication(String title , String text){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        //

        //
        //
        //
        Intent intent = new Intent();
        PendingIntent pend = PendingIntent.getActivity(this,(int)System.currentTimeMillis(),intent,0);

        NotificationCompat.Builder notification_builder ;
        NotificationManager notification_manager = (NotificationManager) this
                .getSystemService(this.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String chanel_id = "3000";
            CharSequence name = "Channel Name";
            String description = "Chanel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;//IMPORTANCE_LOW
            NotificationChannel mChannel = new NotificationChannel(chanel_id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            notification_manager.createNotificationChannel(mChannel);
            notification_builder = new NotificationCompat.Builder(this);
        } else {
            notification_builder = new NotificationCompat.Builder(this);
        }
        notification_builder.setSmallIcon(R.mipmap.logo)
                .setContentTitle("Notification Title")
                .setContentText("Notification Body")
                .setAutoCancel(true)
                .setContentIntent(pend);

    }


    @Override
    public void onBackPressed() {
      //  alartExit();
        Intent intent = new Intent(AdminEmployeeExtr.this ,Admin1Employee.class);
        intent.putExtra("username",ls_username);
        startActivity(intent);
    }
    public void alartExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Black));

        // builder.
        builder.setMessage(R.string.logout).setCancelable(false).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(AdminEmployeeExtr.this ,Login.class);
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

