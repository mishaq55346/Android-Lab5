package com.example.lab5.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.lab5.R;
import java.util.List;

public class LikedAdapter extends RecyclerView.Adapter<LikedAdapter.ViewHolder> {
    private Context Context;
    private List<LikedItem> LikedList;
    public LikedAdapter(Context context, List<LikedItem> likedList) {
        Context = context;
        LikedList = likedList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(Context).inflate(R.layout.liked, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LikedItem currentItem = LikedList.get(position);
        String imageUrl = currentItem.getImageUrl();
        Glide.with(Context)
                .load(imageUrl)
                .centerInside()
                .into(holder.mImageView);
    }
    @Override
    public int getItemCount() {
        return LikedList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view_detail_like);
        }
    }
}