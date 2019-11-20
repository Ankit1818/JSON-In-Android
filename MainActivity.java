package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        list = new ArrayList<>();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://prakrutivyas.000webhostapp.com/regserver/view2.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        String name = object.getString("username");
                        String pass = object.getString("password");
                        String email = object.getString("email");

                        Model m = new Model();
                        m.username = name;
                        m.password = pass;
                        m.email = email;

                        list.add(m);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MyAdapter myAdapter=new MyAdapter();
                listView.setAdapter(myAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
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
            TextView txt3=convertView.findViewById(R.id.txt3);

            Model m =list.get(position);

            txt1.setText(m.username);
            txt2.setText(m.password);
            txt3.setText(m.email);


            return convertView;
        }
    }
}