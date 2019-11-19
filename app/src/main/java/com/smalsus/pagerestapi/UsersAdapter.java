package com.smalsus.pagerestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>
{
    private Context mContext;
    private List<FriendsModel> friendsList;

    public UsersAdapter(Context mContext, List<FriendsModel> friendsList) {
        this.mContext = mContext;
        this.friendsList = friendsList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        Context context = holder.profileImage.getContext();
        FriendsModel friends = friendsList.get(position);
        Picasso.with(context).load(friends.getImageUrl()).into(holder.profileImage);
        holder.textViewName.setText(String.format("%s %s", friends.getfName(), friends.getlName()));
        if(friends.getFriendsLocationModel() != null)
        {
            holder.textViewLocation.setText(String.format("%s, %s", friends.getFriendsLocationModel().getUser_city(), friends.getFriendsLocationModel().getUser_country()));
        }
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewName;
        TextView textViewLocation;
        ImageView profileImage;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.IMAGE_VIEW_ID);
            textViewLocation = itemView.findViewById(R.id.TEXT_VIEW_LOCATION);
            textViewName = itemView.findViewById(R.id.TEXT_VIEW_NAME_ID);
        }
    }
}
