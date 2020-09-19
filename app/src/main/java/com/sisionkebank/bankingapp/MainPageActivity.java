package com.sisionkebank.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import Data.DatabaseHelper;
import Model.User;

public class MainPageActivity extends AppCompatActivity implements GlobalVariables{

    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle p = getIntent().getExtras();
        String email =p.getString("email");
        welcome = findViewById(R.id.txtLoginWelcome);
        DatabaseHelper db = new DatabaseHelper(this);
        User user = db.getUserDetails(email);
        welcome.setText(welcome.getText() + " " + user.getNAME());
    }

    @Override
    public void declareVariables() {

    }

    public void viewAccountBalance(View view) {
    }

    public void processtransfers(View view) {
    }

    public void processLogout(View view) {
        Intent logout = new Intent(this, LoginActivity.class);
        logout.putExtra("finish",true);
        logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logout);
        finish();
        Toast.makeText(this,"You successfully logged out.", Toast.LENGTH_SHORT).show();
    }
}