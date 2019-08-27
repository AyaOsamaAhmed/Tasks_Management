package com.example.tasks_management;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import me.anwarshahriar.calligrapher.Calligrapher;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by egypt2 on 29-Oct-18.
 */

public class Admin1Employee extends Activity implements NavigationView.OnNavigationItemSelectedListener  {

    Toolbar toolbar ;
    DrawerLayout drawer ;
    private EndDrawerToggle drawerToggle ;
    NavigationView navigationView;
    Button                      button_miss ;
    boolean                     check;
    TextView                    empolyee_text;
    String                      ls_username ;
    ListView                    listView_employee;
    ArrayList<HashMap<String,String>>           arrayList_employee;
    HashMap<String,String>      hash_employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_connect_admin1_employee);
        //
        drawer();

        //
        arrayList_employee=new ArrayList<HashMap<String,String>>();
        hash_employees = new HashMap<String, String>();
        listView_employee=(ListView)findViewById(R.id.list_item);
        empolyee_text  =(TextView) findViewById(R.id.empolyee);
        button_miss=(Button)findViewById(R.id.miss_employee);
        //
        ls_username = getIntent().getStringExtra("username");
        //
        retrieveemployee();

        ListViewAdapter adapter = new ListViewAdapter(Admin1Employee.this, arrayList_employee );

        listView_employee.setAdapter(adapter);


     /*   listView_employee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
        int position, long id) {
            Log.d(TAG, "onItemClick: "+position);


        }
            });*/

    }

    public void retrieveemployee (){
        int len , id = 0;
        String  key = "" , id_employee , employee="" ,username_employee = "";

        SharedPreferences sh = getSharedPreferences("newemployee", MODE_PRIVATE);
          len =  sh.getAll().size();
        Log.d(TAG, "retrieveemployee: length" + len);
        for( ;id < len;id++ ){
            key = "employee_"+id;
            id_employee=   sh.getString(key,"");
            employee = retrieveData(id_employee,1);
            username_employee = retrieveData(id_employee,2);

            hash_employees.put("name"+id,employee);
             hash_employees.put("username"+id,username_employee);

        }
        Log.d(TAG, "retrieveemployee: hash_employee"+hash_employees);
        arrayList_employee.add(hash_employees);

    }
    public String retrieveData (String  employee, int choose) {
        // retrive Data
        SharedPreferences sh = getSharedPreferences(employee, MODE_PRIVATE);

        if (choose == 1){
       return sh.getString("name", "");
    }else if (choose == 2){
            return sh.getString("username", "");
        }
    return null;
    }

    public void fontType(){

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Ah-moharram-bold.ttf",true);
    }

    private void drawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_one);
        // setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.admin1Employee);
        toolbar.setTitleTextColor(Color.rgb(255,255,255));
        //toolbar.setTitleMargin(250,0,0,0);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.isDrawerOpen(Gravity.RIGHT);

        // toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle = new EndDrawerToggle(this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getItemIconTintList();
        navigationView.setItemIconTintList(null);
      /*  toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                    drawer.closeDrawer(Gravity.RIGHT);
                } else {
                    drawer.openDrawer(Gravity.RIGHT);
                }
            }
        });*/

    }
    @Override
    public void onBackPressed() {
        Boolean   open_close ;
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        open_close= drawer.isDrawerVisible(Gravity.RIGHT);
        if (open_close) {
            drawer.closeDrawers();
        }else {
            Intent intent = new Intent(Admin1Employee.this ,Admin1.class);
            startActivity(intent);

        }

    }



    public class EndDrawerToggle implements DrawerLayout.DrawerListener {

        private DrawerLayout drawerLayout;
        private DrawerArrowDrawable arrowDrawable;
        private AppCompatImageButton toggleButton;
        private String openDrawerContentDesc;
        private String closeDrawerContentDesc;

        public EndDrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar,
                               int openDrawerContentDescRes, int closeDrawerContentDescRes) {

            this.drawerLayout = drawerLayout;
            this.openDrawerContentDesc = activity.getString(openDrawerContentDescRes);
            this.closeDrawerContentDesc = activity.getString(closeDrawerContentDescRes);

            arrowDrawable = new DrawerArrowDrawable(toolbar.getContext());
            arrowDrawable.setDirection(DrawerArrowDrawable.ARROW_DIRECTION_END);

            toggleButton = new AppCompatImageButton(toolbar.getContext(), null,
                    R.attr.toolbarNavigationButtonStyle);
            toolbar.addView(toggleButton, new ActionBar.LayoutParams(GravityCompat.END));

            //
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                toolbar.setTitleMargin(600,0,460,0);
            } else {
                toolbar.setTitleMargin(280,0,100,20);
            }
            //
            //toolbar.setTitleMargin(420,0,200,30);

            toggleButton.setImageDrawable(arrowDrawable);
            toggleButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    toggle();
                                                }
                                            }
            );
        }

        public void syncState() {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                setPosition(1f);
            } else {
                setPosition(0f);
            }
        }

        public void toggle() {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        }

        public void setPosition(float position) {
            if (position == 1f) {
                arrowDrawable.setVerticalMirror(true);
                toggleButton.setContentDescription(closeDrawerContentDesc);
            } else if (position == 0f) {
                arrowDrawable.setVerticalMirror(false);
                toggleButton.setContentDescription(openDrawerContentDesc);
            }
            arrowDrawable.setProgress(position);
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            setPosition(Math.min(1f, Math.max(0, slideOffset)));
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            setPosition(1f);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            setPosition(0f);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.home :
                Intent intent = new Intent(Admin1Employee.this ,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.main1 :
                Intent intent2 = new Intent(Admin1Employee.this ,Admin1.class);
                startActivity(intent2);
                break;
            case R.id.main2:
                Intent intent3 = new Intent(Admin1Employee.this ,MainActivity.class);
                startActivity(intent3);
                break;
            case R.id.call_us:
                Intent intentcall = new Intent(Admin1Employee.this ,MainActivity.class);
                startActivity(intentcall);
                break;
            case R.id.exit:
                alartExit();
                break;
            case R.id.addMember:
                if(checkNetwork()) {
                    Intent intentmember = new Intent(Admin1Employee.this, AddEmployee.class);
                    startActivity(intentmember);
                    break;
                }
                else {
                Intent intentmember = new Intent(Admin1Employee.this, AddEmployee.class);
                startActivity(intentmember);
                    break;
                }
          /*  case R.id.memeberTahla:
                if(checkNetwork()) {
                    Intent intent5 = new Intent(Admin1Employee.this, MainActivity.class);
                    startActivity(intent5);
                    break;
                }
                else {

                    break;
                }*/
            default:
                break;
        }
        return true;
    }
    public void Exit() {
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
    private boolean checkNetwork() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();


        if (info != null && info.isConnected()) {
            check=true;
        }else check=false;
        return check;
    }
    public void alartExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Black));

        // builder.
        builder.setMessage(R.string.logout).setCancelable(false).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //   Admin1.this.finish();
                Intent intent = new Intent(Admin1Employee.this ,Login.class);
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
