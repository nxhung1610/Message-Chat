package com.example.logingmail.Chat;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logingmail.MessageChatActivity;
import com.example.logingmail.ProfileActivity;
import com.example.logingmail.R;
import com.example.logingmail.UserProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_RIGHT = 0;
    public static final int MSG_TYPE_LEFT = 1;

    Context context;
    ArrayList<MessageData> messageData;
    UserProfile user;

    public MessageAdapter(Context context, ArrayList<MessageData> messageData) {
        this.context = context;
        this.messageData = messageData;
        user = ProfileActivity.userProfile;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType){
            case MSG_TYPE_RIGHT:
                view = LayoutInflater.from(context).inflate(R.layout.message_item_right,parent,false);
                break;
            case MSG_TYPE_LEFT:
                view = LayoutInflater.from(context).inflate(R.layout.message_item_left,parent,false);
                break;
        }
        return new MessageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        MessageData messageDetail = messageData.get(position);
        TextView message = holder.itemView.findViewById(R.id.messageSender);
        message.setText(messageDetail.getMessage());
        try{
            ImageView avatarIcon = holder.itemView.findViewById(R.id.avatarIcon);
            TextView nameSender = holder.itemView.findViewById(R.id.nameSender);
            Picasso.get().load(Uri.parse(messageDetail.getUrlAvatar())).into(avatarIcon);
            nameSender.setText(messageDetail.getProfile().getName());
        }
        catch (Exception e){

        }


    }

    @Override
    public int getItemCount() {
        return messageData.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(messageData.get(position).getSender().equals(user.getUid())){
            return MSG_TYPE_RIGHT;
        }
        else{
            return MSG_TYPE_LEFT;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void add(MessageData messageDetail){
        this.messageData.add(messageDetail);
        notifyDataSetChanged();
    }
}
