package com.example.rajat.blood_bank;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.content.Context;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Registeration extends AppCompatActivity {
    Toolbar toolbar;
    EditText editTextUserName, editTextPassword, editTextContact, editTextAddress, editTextEmail,editTextName;
    Button btnCreateAccount;
    Spinner spinner;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        editTextName= (EditText) findViewById(R.id.name);
        editTextUserName = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.pass);
        editTextContact = (EditText) findViewById(R.id.cont);
        spinner= (Spinner) findViewById(R.id.spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bg,R.layout.spinnerlayout);
        adapter.setDropDownViewResource(R.layout.sp);
        spinner.setAdapter(adapter);
        editTextAddress = (EditText) findViewById(R.id.address);
        editTextEmail = (EditText) findViewById(R.id.email);
        btnCreateAccount = (Button) findViewById(R.id.account);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                String address = editTextAddress.getText().toString();
                String contact=editTextContact.getText().toString();
                String blood=spinner.getSelectedItem().toString();
                String name=editTextName.getText().toString();
                String email=editTextEmail.getText().toString();



                // check if any of the fields are vaccant
                if (userName.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_SHORT).show();
                    return;
                }
                // check if both password matches
                if(loginDataBaseAdapter.checkUsername(userName))
                {
                    Toast.makeText(getApplicationContext(), "Username already exists ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    // Save the Data in Database
                    long row = loginDataBaseAdapter.insertEntry(new UserData(name,userName, password, address,contact,blood,email));
                    Toast.makeText(getApplicationContext(), "Account Successfully Created " , Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(myIntent);
                    finish();
                }

            }
        });


    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

}
