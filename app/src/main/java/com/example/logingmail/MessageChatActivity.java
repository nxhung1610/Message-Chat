package com.example.logingmail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.logingmail.Chat.MessageAdapter;
import com.example.logingmail.Chat.MessageData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MessageChatActivity extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    UserProfile userProfile;
    EditText messageSend;
    RecyclerView messageChat;
    ArrayList<MessageData> messageData ;
    MessageAdapter messageAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_chat);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference().child("Messages");
        this.userProfile = ProfileActivity.userProfile;
        messageSend = findViewById(R.id.messageSend);

        messageChat = findViewById(R.id.messageChat);
        messageChat.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MessageChatActivity.this);
        linearLayoutManager.setStackFromEnd(true);
        messageChat.setLayoutManager(linearLayoutManager);
        messageData = new ArrayList<>();
        messageAdapter = new MessageAdapter(MessageChatActivity.this,messageData);
        messageChat.setAdapter(messageAdapter);
        ReceiveMessage();
    }


    public void SendMessage(View view) {
        if(!messageSend.getText().toString().trim().equals("")){
            MessageData messageData = new MessageData(userProfile.getUid(),userProfile.getName(),messageSend.getText().toString(),userProfile.getUrlAvatar());
            reference.push().setValue(messageData);
        }
        messageSend.setText("");
    }


    public  void ReceiveMessage(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                final MessageData messageDetail = snapshot.getValue(MessageData.class);
                messageAdapter.add(messageDetail);
                messageChat.scrollToPosition(messageAdapter.getItemCount()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}