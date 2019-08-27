package com.example.tasks_management;

import android.app.Activity;
import android.os.Bundle;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by egypt2 on 03-Oct-18.
 */
public class Admin2 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin2);
            fontType();
    }

    public void fontType(){

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this,"fonts/Ah-moharram-bold.ttf",true);
    }

}
