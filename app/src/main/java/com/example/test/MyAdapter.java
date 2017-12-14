package com.example.test;

import android.content.Context;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    // Firebase database connection
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseDatabase;

    // Firebase user
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


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

        // get the database reference
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

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

        // add a movie to the user database
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get the title of the movie to push into the database
                final UserLikes likedMovie = new UserLikes(listItem.getTitle());

                // get the key of the movie
                String key = mDatabase.child("users").child(firebaseUser.getUid()).child("movies").push().getKey();

                Map<String, Object> likedMovies = likedMovie.toMap();

                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("users/"+ key, likedMovies);
                childUpdates.put("users/"+firebaseUser.getUid()+"/"+key, likedMovies);
                mDatabase.updateChildren(childUpdates);

//
//                Map<String, Object> map = new HashMap<>();
//                map.put("movie", likedMovie);
//
//
//                mDatabase.child("users").child(firebaseUser.getUid()).child("movies").push().setValue(map);


                // https://stackoverflow.com/questions/39815117/add-an-item-to-a-list-in-firebase-databse
//                mDatabase.child("users" )
//                        .child( firebaseUser.getUid())
//                        .child("movies").push()
//                        .setValue(likedMovie);

                // Show which movie is added
                Toast.makeText(MyAdapter.this.context, "Liked: " + listItem.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
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
        public ImageButton like;


        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            title.setTypeface(cf_bahiana);

            release_date = itemView.findViewById(R.id.release_date);
            overview = itemView.findViewById(R.id.overview);
            vote_average = itemView.findViewById(R.id.rating);
            poster_path = itemView.findViewById(R.id.image);

            like = itemView.findViewById(R.id.imageButton);
        }
    }

}
