package com.sisonkebank.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;

import Data.DatabaseHelper;
import Model.User;

public class AccountBalanceActivity extends MainPageActivity {
    private TextView name;
    private TextView surname;
    private TextView current;
    private TextView savings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        this.name = findViewById(R.id.textView);
        this.surname = findViewById(R.id.textView2);
        this.current = findViewById(R.id.txtCurrentBalance);
        this.savings = findViewById(R.id.txtSavingsBalance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent extras = getIntent();
        String email = "none";

        if (extras != null) {
            email = extras.getExtras().getString("email");
            Log.d("Intent: ",email);
            DatabaseHelper database = new DatabaseHelper(this);
            User userinfo = database.getUserDetails(email);
            System.err.println("maill"+userinfo.getNAME());
            System.err.println(userinfo.getEMAIL());
            this.name.setText(userinfo.getNAME());
            this.surname.setText( userinfo.getSURNAME());
            this.current.setText("R " + userinfo.getCURRENT_BALANCE());
            this.savings.setText("R " + userinfo.getSAVINGS_BALANCE());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}