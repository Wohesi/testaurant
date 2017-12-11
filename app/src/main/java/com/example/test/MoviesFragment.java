package com.example.test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


public class MoviesFragment extends Fragment {


    // URLS for getting the data
    String url_menu ="https://resto.mprog.nl/menu";

    // Setting the adapters & lists for items
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.movies_fragment, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listItems = new ArrayList<ListItem>();
        loadRecyclerViewData();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    // Getting Json data and parsing it into an adaptor
    private void loadRecyclerViewData() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url_menu,

                // In success this method will be executed
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("items");

                            for (int i =0; i<jsonArray.length(); i++) {
                                JSONObject o = jsonArray.getJSONObject(i);
                                ListItem item = new ListItem(
                                        o.getString("name"),
                                        o.getString("description"),
                                        o.getString("category"),
                                        o.getString("image_url"),
                                        o.getInt("id"),
                                        o.getInt("price")
                                );
                                listItems.add(item);

                                adapter = new MyAdapter(listItems, getContext());
                                recyclerView.setAdapter(adapter);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                // if it fails an error will be shown.
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}