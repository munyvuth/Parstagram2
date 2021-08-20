package com.example.parstagram2;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.ParseException;
import com.parse.ParseObject;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    List<Post> posts;
    Context context;

    public PostsAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        try {
            holder.bind(post);
        } catch (ParseException e) {
            Log.e("PostsAdapter", "Cannot bind data, possibly related to glide and image", e);
            e.printStackTrace();
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        ImageView ivImagePost;
        TextView tvCaption;
        ImageView ivHeart;
        ImageView ivSave;
        TextView tvName;
        TextView tvTimeStamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImagePost = itemView.findViewById(R.id.ivImagePost);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            ivHeart = itemView.findViewById(R.id.ivHeart);
            ivSave = itemView.findViewById(R.id.ivSave);
            tvName = itemView.findViewById(R.id.tvName);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
        }

        public void bind(Post post) throws ParseException {
            tvUsername.setText(post.getUser().getUsername());
            tvName.setText(post.getUser().getUsername());
            Glide.with(context).load(post.getImage().getFile()).into(ivImagePost);
            tvCaption.setText(post.getDescription());
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
            Log.i("PostsAdapter", "Raw date: " + post.getCreatedAt());
            String formattedDate = sdf.format(post.getCreatedAt());
            tvTimeStamp.setText(TimeFormatter.getTimeDifference(formattedDate));

            ivHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!v.isSelected()) {
                        v.setSelected(true);
                    } else {
                        v.setSelected(false);
                    }
                }
            });

            ivSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!v.isSelected()) {
                        v.setSelected(true);
                    } else {
                        v.setSelected(false);
                    }
                }
            });
        }
    }
}
