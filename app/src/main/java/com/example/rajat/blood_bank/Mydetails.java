package com.example.rajat.blood_bank;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mydetails extends Fragment {

    private TextView textViewname, textViewpass, tvAddress,tvcontact,tvblood,tvadd,tvname,tvemail;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_mydetails, container, false);
        Intent in = getActivity().getIntent();
        String userName = in.getStringExtra("username");
        UserData objUserData = new LoginDataBaseAdapter(getActivity()).getUserData(userName);
        textViewname = (TextView)v. findViewById(R.id.text1);
        tvadd= (TextView) v.findViewById(R.id.add);
        tvname= (TextView) v.findViewById(R.id.nme);
        tvemail= (TextView) v.findViewById(R.id.em);
        textViewpass = (TextView)v. findViewById(R.id.text2);
        tvAddress = (TextView)v. findViewById(R.id.text3);
        tvcontact= (TextView)v. findViewById(R.id.text4);
        tvblood= (TextView)v. findViewById(R.id.text5);
        textViewname.setText("Name:     " );
        tvname.setText(objUserData.getName());
        textViewpass.setText("Email:     ");
        tvemail.setText(objUserData.getEmail());
        tvAddress.setText("Address:     " );
        tvadd.setText(objUserData.getAddress());
        tvcontact.setText("Contact:     "+objUserData.getContact());
        tvblood.setText("Blood Group:   "+objUserData.getBgroup());

        return v;


    }

}
