package com.example.lab5.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.lab5.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> implements Serializable {
    private Context mContext;
    private ArrayList<Item> mExampleList;
    private List<LikedItem> LikedList = new ArrayList<>();
    public String imageUrl;
    public Adapter(Context context, ArrayList<Item> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Glide.with(holder.mImageView.getContext())
                .load(mExampleList.get(position).getImageUrl())
                .centerCrop()
                .into(holder.mImageView);
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public Button like_btn;
        public Button dislike_btn;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            like_btn = itemView.findViewById(R.id.like);
            dislike_btn = itemView.findViewById(R.id.dislike);
            like_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    Item currentItem = mExampleList.get(position);
                    imageUrl = currentItem.getImageUrl();
                    LikedList.add(new LikedItem(imageUrl));
                    Singleton.getInstance().setItems(LikedList);
                    like_btn.setVisibility(View.INVISIBLE);
                    dislike_btn.setVisibility(View.INVISIBLE);
                }
            });
            dislike_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    like_btn.setVisibility(View.INVISIBLE);
                    dislike_btn.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}