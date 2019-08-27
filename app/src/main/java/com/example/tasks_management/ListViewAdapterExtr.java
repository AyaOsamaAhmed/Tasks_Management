package com.example.tasks_management;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by aya on 11/1/2016.
 */
public class ListViewAdapterExtr extends BaseAdapter {

    // Declare Variables

    Context         context;
    int             position_employee ;
    LayoutInflater  inflater;
    String          ls_username ;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();


    public ListViewAdapterExtr(Context context,
                               ArrayList<HashMap<String, String>> arraylist ,String username ) {

        this.context = context;
        ls_username = username;
        data = arraylist;
        resultp = data.get(0);

        imageLoader = new ImageLoader(context);

    }

    @Override
    public int getCount() {
        return resultp.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertview, ViewGroup viewGroup) {
        // Declare Variables
        TextView employee_in ;
        Button  button_miss ;
        if (convertview == null) {
            convertview = LayoutInflater.from(context).
                    inflate(R.layout.employee_extr_in, viewGroup, false);
        }

        // Locate the TextViews
       employee_in = (TextView) convertview.findViewById(R.id.additionmission);

        //
        Log.d(TAG, "getView: resultp");
        String key = "addition_"+position+"_"+ls_username;
        String emp = resultp.get(key);
        employee_in.setText(emp);

        // Locate the ImageView in listview_item.xml
//        imglink = (ImageView) itemView.findViewById(R.id.image);

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
       // imageLoader.DisplayImage("http://52.41.120.12:8080/Restaurant/uploadedFiles/"+resultp.get(MenuParent.IMG), imglink);

        //
        //convertview.setOnClickListener(new CustomOnClickListener(callback, position));
        //


        return convertview;

    }
}


