package com.example.test;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    public MyAdapter(List<ListItem> listItems, Context context) {
        ListItems = listItems;
        this.context = context;
    }

    private List<ListItem> ListItems;
    private Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItem  listItem = ListItems.get(position);

        holder.title.setText(listItem.getTitle());
        holder.overview.setText(listItem.getOverview());
        holder.release_date.setText(listItem.getRelease_date());

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w185/"+listItem.getPoster_path())
                .into(holder.poster_path);

        holder.vote_average.setText("Rating: "+String.valueOf(listItem.getVote_average()));


    }

    @Override
    public int getItemCount() {
        return ListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        Typeface cf_bahiana = Typeface.createFromAsset(itemView.getContext().getAssets(),"font/bahiana.otf");

        public TextView overview;
        public TextView release_date;
        public TextView vote_average;
        public ImageView poster_path;


        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            title.setTypeface(cf_bahiana);

            release_date = itemView.findViewById(R.id.release_date);
            overview = itemView.findViewById(R.id.overview);
            vote_average = itemView.findViewById(R.id.rating);
            poster_path = itemView.findViewById(R.id.image);
        }
    }

}
