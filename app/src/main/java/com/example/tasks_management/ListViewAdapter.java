package com.example.tasks_management;

import android.content.Context;
import android.content.Intent;
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
public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context         context;
    int             position_employee ;
    LayoutInflater  inflater;

    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();


    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist  ) {

        this.context = context;

        data = arraylist;
        resultp = data.get(0);

        imageLoader = new ImageLoader(context);

    }

    @Override
    public int getCount() {
        return resultp.size()/2;
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
                    inflate(R.layout.admin1_employee_in, viewGroup, false);
        }

        // Locate the TextViews
       employee_in = (TextView) convertview.findViewById(R.id.empolyee);
        button_miss = (Button) convertview.findViewById(R.id.miss_employee);
        //
        String key ="name"+position;
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
        button_miss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: listener "+position );
                Intent intent = new Intent( context ,Employee.class);
                String key ="username"+position;
                intent.putExtra("username",resultp.get(key));
                context.startActivity(intent);
            }
        });

        return convertview;

    }
}


class ImageLoader {

    public ImageLoader (Context context){


    }

}