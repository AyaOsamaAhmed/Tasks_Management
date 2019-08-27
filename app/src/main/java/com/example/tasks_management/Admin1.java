package com.example.tasks_management;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by egypt2 on 03-Oct-18.
 */

public class Admin1 extends Activity  implements NavigationView.OnNavigationItemSelectedListener   {
    Toolbar toolbar ;
    DrawerLayout drawer ;
    ActionBarDrawerToggle toggle;
    private EndDrawerToggle drawerToggle ;
    NavigationView navigationView;
    Button  button_finance ,button_engineer ,button_market ,button_secretary,button_relations,button_maintance,button_purchases ,button_electric ;
    boolean check;
    HelpClass   helpClass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_connect_admin1);

        //
        helpClass = new HelpClass();
        //
        drawer();

        clickingButtons();
    }

    public void fontType(){

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Ah-moharram-bold.ttf",true);
    }

    private void drawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_one);
        // setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.admin1);
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

    private void clickingButtons() {
        button_finance = (Button) findViewById(R.id.button_finance);

        button_finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin1.this ,Admin1Employee.class);
                intent.putExtra("username","");
                startActivity(intent);
            }
        });
        //
        button_engineer = (Button) findViewById(R.id.button_engineer);

        button_engineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin1.this ,Admin1Employee.class);
                startActivity(intent);
            }
        });
        //
        button_market = (Button) findViewById(R.id.button_market);

        button_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin1.this ,Admin1Employee.class);
                startActivity(intent);
            }
        });
        //
        button_secretary = (Button) findViewById(R.id.button_secretary);

        button_secretary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin1.this ,Admin1Employee.class);
                startActivity(intent);
            }
        });
        //
        button_relations = (Button) findViewById(R.id.button_relations);

        button_relations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin1.this ,Admin1Employee.class);
                startActivity(intent);
            }
        });
        //
        button_maintance = (Button) findViewById(R.id.button_maintance);

        button_maintance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin1.this ,Admin1Employee.class);
                startActivity(intent);
            }
        });
        //
        button_purchases = (Button) findViewById(R.id.button_purchases);

        button_purchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin1.this ,Admin1Employee.class);
                startActivity(intent);
            }
        });
        //
        button_electric = (Button) findViewById(R.id.button_electric);

        button_electric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin1.this ,Admin1Employee.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
        Boolean   open_close ;
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        open_close= drawer.isDrawerVisible(Gravity.RIGHT);
        if (open_close) {
            drawer.closeDrawers();
        }else {
            Intent intent = new Intent(Admin1.this ,MainActivity.class);
            startActivity(intent);
        }

    }

    public void alartExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme_Black));

        // builder.
        builder.setMessage(R.string.logout).setCancelable(false).setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
             //   Admin1.this.finish();
                Intent intent = new Intent(Admin1.this ,Login.class);
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

            toggleButton = new AppCompatImageButton(toolbar.getContext(), null,
                    R.attr.toolbarNavigationButtonStyle);
            toolbar.addView(toggleButton, new ActionBar.LayoutParams(GravityCompat.END));

            //
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                toolbar.setTitleMargin(600,0,460,0);
            } else {
                toolbar.setTitleMargin(280,0,130,20);
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
                Intent intent = new Intent(Admin1.this ,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.main1 :
                Intent intent2 = new Intent(Admin1.this ,Admin1.class);
                startActivity(intent2);
                break;
            case R.id.main2:
                Intent intent3 = new Intent(Admin1.this ,MainActivity.class);
                startActivity(intent3);
                break;
            case R.id.call_us:
                Intent intentcall = new Intent(Admin1.this ,MainActivity.class);
                startActivity(intentcall);
                break;
            case R.id.exit:
                alartExit();
                break;
           default:
               break;
       }
        return true;
    }

    private boolean checkNetwork() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();


        if (info != null && info.isConnected()) {
            check=true;
        }else check=false;
        return check;
    }
}
