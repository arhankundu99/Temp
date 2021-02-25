package com.blackbrick.epsilonintern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    private TextView userName;
    private Button signoutBtn;
    private ImageView userImage;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.user_name);
        userImage = findViewById(R.id.user_profile_image);
        signoutBtn = findViewById(R.id.sign_out_btn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        userName.setText(account.getDisplayName());
        Uri userPhoto = account.getPhotoUrl();
        System.out.println(userPhoto);
        Glide.with(this).load(userPhoto).into(userImage);
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void signOut(){
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(MainActivity.this, "onPause" ,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this, "onStop" ,Toast.LENGTH_SHORT).show();
    }
}

