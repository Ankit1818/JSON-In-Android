package com.example.jsonwithimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    ListView listView;
    List<Model>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list);
        list=new ArrayList<>();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "https://vyasprakruti.000webhostapp.com/file/view_without_var.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try
                {

                    JSONObject object=new JSONObject(response);
                    JSONArray array=object.getJSONArray("result");

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object1=array.getJSONObject(i);

                        String name=object1.getString("statename");
                        String price=object1.getString("countryname");
                       // String image=object1.getString("image");

                        Model m =new Model();
                        m.name=name;
                        m.price=price;
                    //    m.image=image;

                        list.add(m);

                    }

                } catch (JSONException e)
            {
                    e.printStackTrace();
                }

                MyAdapter adapter=new MyAdapter();
                listView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "No internet", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);


    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return list.size();
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
        public View getView(int position, View convertView, ViewGroup parent)
        {

            LayoutInflater in=LayoutInflater.from(getApplicationContext());
            convertView=in.inflate(R.layout.design,parent,false);

            TextView txt1=convertView.findViewById(R.id.txt1);
            TextView txt2=convertView.findViewById(R.id.txt2);
            ImageView img=convertView.findViewById(R.id.img);

            Model m =list.get(position);

            txt1.setText(m.name);
            txt2.setText(m.price);
          /*  Picasso.with(getApplicationContext())
                    .load(m.image)
                    .into(img);*/



            return convertView;
        }
    }
}
