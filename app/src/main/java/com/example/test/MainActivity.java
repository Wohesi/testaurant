package com.example.test;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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

    // Setting variables for the toolbar
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tv_black_24dp,
            R.drawable.ic_favorite_black_24dp,
            R.drawable.ic_person_black_24dp,

    };
//
//    // URLS for getting the data
//    String url_menu ="https://resto.mprog.nl/menu";
//
//    // Setting the adapters & lists for items
//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter adapter;
//    private List<ListItem> listItems;

    // On create main function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Setting up the tabs and toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


        // Setting up variables for the recycler view
//        recyclerView = (RecyclerView) this.findViewById(R.id.rv);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        listItems = new ArrayList<ListItem>();
//        loadRecyclerViewData();

    }

    // Setting icons to the tabs
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    // Setting the tabs into the tabbar
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MoviesFragment(), "Movies");
        adapter.addFragment(new LikesFragment(), "Likes");
        adapter.addFragment(new LoginFragment(), "Login");
        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

//    // Getting Json data and parsing it into an adaptor
//    private void loadRecyclerViewData() {
//        StringRequest stringRequest = new StringRequest(
//                Request.Method.GET, url_menu,
//
//                // In success this method will be executed
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray jsonArray = jsonObject.getJSONArray("items");
//
//                            for (int i =0; i<jsonArray.length(); i++) {
//                                JSONObject o = jsonArray.getJSONObject(i);
//                                ListItem item = new ListItem(
//                                        o.getString("name"),
//                                        o.getString("description"),
//                                        o.getString("category"),
//                                        o.getString("image_url"),
//                                        o.getInt("id"),
//                                        o.getInt("price")
//                                );
//                                listItems.add(item);
//
//                                adapter = new MyAdapter(listItems, getApplicationContext());
//                                recyclerView.setAdapter(adapter);
//
//                            }
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//
//                // if it fails an error will be shown.
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }

}