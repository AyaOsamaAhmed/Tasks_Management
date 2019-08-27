package com.example.tasks_management;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by egypt2 on 01-Nov-18.
 */

public class HelpClass extends Activity {

          String    GET_JSON_DATA_HTTP_URL;
          String    ResultData ;
    final String    URL_Login = "" ;
    final String    URL_Registe = "" ;
    final String    URL_EXTRMission = "" ;
    HashMap<String,String>   hashMap_username;

HelpClass(){

    hashMap_username = new HashMap<String, String>();
    hashMap_username.put("name","");
    hashMap_username.put("section","");
    hashMap_username.put("department","");
    hashMap_username.put("main_mission","");

}

    String GETStringRequest(String get_URL , final HashMap<String,String> get_param ){

        StringRequest stringRequest =    new StringRequest(Request.Method.POST, get_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d(TAG, "onResponse:resultdata2 "+ResultData);
                        Log.d(TAG, "onResponse: response"+response);

                        //  tv.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: "+error);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                    params = get_param;
                //  params.put("id_member", "55");
               // params.put("username", "1");
               // params.put("password", "1");

                return params;
            }
        };

        // Adding request to request queue
        // AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);
        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        return ResultData ;
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


        boolean check;
        if (info != null && info.isConnected()) {
            check=true;
        }else check=false;
        return check;
    }

}
