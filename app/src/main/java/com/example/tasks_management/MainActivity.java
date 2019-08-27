package com.example.tasks_management;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.anwarshahriar.calligrapher.Calligrapher;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar ;
    Button      button_main , button_main2 ;
    TextView start, tab_start , tab_Privacy ,Privacy , Login, tab_login ,add_client , tab_add_client , tab_contact_us , tab_recordclients;
    private int height_start , height_Privacy , height_Login, height_add_client , height_contact_us , height_recordclients ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_connect);

        setToolbar();

        button_main = (Button) findViewById(R.id.main1);
        button_main2 = (Button) findViewById(R.id.main2);



        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Admin1.class);
                startActivity(intent);
            }
        });
        /*button_main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Admin2.class);
                startActivity(intent);
            }
        });*/
        //---------------
        View fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                createDeclearLayout();
            }
        });
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_one);
        // setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.main);
        toolbar.setTitleTextColor(Color.rgb(255,255,255));
        //
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar.setTitleMargin(600,0,460,0);
        } else {
            toolbar.setTitleMargin(280,0,130,20);
        }

    }


    private void createDeclearLayout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

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
       /* Boolean   open_close ;
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        open_close= drawer.isDrawerVisible(Gravity.RIGHT);
        if (open_close) {
            drawer.closeDrawers();
        }else {
            alartExit();
        }*/
        alartExit();

    }

    public void fontType(){

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Ah-moharram-bold.ttf",true);
    }

    public void alartExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Black));

        builder.setMessage(R.string.logout).setCancelable(false).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(MainActivity.this ,Login.class);
                startActivity(intent);
                
            }
        }).setNegativeButton(R.string.refuse, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
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

            //toggleButton.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
           // getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            toggleButton = new AppCompatImageButton(toolbar.getContext(), null, R.attr.toolbarNavigationButtonStyle);
            toolbar.addView(toggleButton, new ActionBar.LayoutParams(GravityCompat.END));

            //
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                toolbar.setTitleMargin(600,0,460,0);
            } else {
                toolbar.setTitleMargin(280,0,130,20);
            }
            //


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


}
