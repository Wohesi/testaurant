package com.example.test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    // Movie url:
    String url = "https://api.themoviedb.org/3/genre/9648/movies?api_key=d62e410d87b8cb0fea0dfa6cadb99429&language=en-US&include_adult=false&sort_by=created_at.asc";

    // Setting the adapters & lists for items
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> movieList;

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

        recyclerView = rootView.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        movieList = new ArrayList<ListItem>();
        loadData();

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // Getting Json data and parsing it into an adaptor
    private void loadData() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,

                // If successful:
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");

                            for (int i =0; i<jsonArray.length(); i++) {
                                JSONObject movie = jsonArray.getJSONObject(i);
                                ListItem item = new ListItem(
                                        movie.getString("title"),
                                        movie.getString("overview"),
                                        movie.getString("release_date"),
                                        movie.getString("poster_path"),
                                        movie.getInt("id"),
                                        movie.getInt("vote_average")
                                );
                                movieList.add(item);

                                // Add items to adapter
                                adapter = new MyAdapter(movieList, getContext());
                                recyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },

                // if it fails
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