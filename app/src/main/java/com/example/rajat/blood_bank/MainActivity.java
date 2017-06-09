package com.example.rajat.blood_bank;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    Button btnSignIn, btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blood Bank");

        toolbar.setTitleTextColor(Color.WHITE);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        btnSignIn = (Button) findViewById(R.id.login);
        btnSignUp = (Button) findViewById(R.id.register);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUP = new Intent(getApplicationContext(), Registeration.class);
                startActivity(intentSignUP);
            }
        });



    }

    public void signIn(View V) {

        final EditText editTextUserName = (EditText) findViewById(R.id.name);
        final EditText editTextPassword = (EditText) findViewById(R.id.passw);
        Button btnSignIn = (Button) findViewById(R.id.login);
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();


                String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);


                if (password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Users.class);
                    intent.putExtra("username", userName);
                    startActivity(intent);


                } else {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

}


