package com.example.rajat.blood_bank;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDonor extends Fragment implements AdapterView.OnItemSelectedListener {


Spinner spinner;
    TextView t1,t2;
    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_search_donor, container, false);

        loginDataBaseAdapter = new LoginDataBaseAdapter(getActivity());
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        spinner= (Spinner) v.findViewById(R.id.spin);
        t1= (TextView) v.findViewById(R.id.t1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.bg,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.sp);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
        Cursor res=loginDataBaseAdapter.getsome(item);
        if(res.getCount()==0&&position>0)
        {
            showMessage("Error","No data Found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {

            buffer.append("Name :"+res.getString(0)+"\n");
            buffer.append("Contact :"+res.getString(1)+"\n");

        }
        if(position>0)
        showMessage("Data",buffer.toString());




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
