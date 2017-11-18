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

/**
 * Created by Wout on 17-11-2017.
 */

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

        holder.name.setText(listItem.getName());
        holder.desc.setText(listItem.getDesc());
        holder.cat.setText(listItem.getCat());

//        Picasso.with(context)
//                .load(listItem.getImg_url())
//                .into(holder.image);

        holder.price.setText("Costs :"+" $ "+String.valueOf(listItem.getPrice()));

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "you added "+listItem.getName(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return ListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        Typeface cf_bahiana = Typeface.createFromAsset(itemView.getContext().getAssets(),"font/bahiana.otf");
        public TextView desc;
        public TextView cat;
        public TextView price;

        public ImageView image;

        public Button button;


        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            name.setTypeface(cf_bahiana);

            cat = itemView.findViewById(R.id.category);
            desc = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);

            image = itemView.findViewById(R.id.image);

            button = itemView.findViewById(R.id.button);
            //layout = itemView.findViewById(R.id.layout);


        }
    }

}

//com.example.test.R.id.name
//        layout/card.xml:24
