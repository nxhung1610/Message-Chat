package com.example.logingmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class ProfileActivity extends AppCompatActivity {


    public static UserProfile userProfile;
    @Override
    protected void onStart() {
        super.onStart();
        try{
            this.userProfile = (UserProfile) this.getIntent().getExtras().getSerializable("userProfile");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        setupProfile();
    }

    private void setupProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ImageView avatar = findViewById(R.id.avatar);
        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        Picasso.get().load(Uri.parse(userProfile.getUrlAvatar())).into(avatar);
        name.setText(userProfile.getName());
        email.setText(userProfile.getEmail());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void LogoutAccount(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent mainLogin = new Intent(this,MainActivity.class);
        startActivity(mainLogin);
    }

    public void ChatMessege(View view) {
        Intent message = new Intent(this,MessageChatActivity.class);
        startActivity(message);
    }
}