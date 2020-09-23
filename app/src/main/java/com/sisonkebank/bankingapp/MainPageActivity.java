package com.sisonkebank.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Data.DatabaseHelper;
import Model.User;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener{
    public User user;
    private TextView welcome;
    private Button balance;
    private Button transfer;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        declareVariables();
        welcome.setText(String.format("%s %s", welcome.getText(), user.getNAME()));
        balance = findViewById(R.id.btnAccountBalance);
        transfer = findViewById(R.id.btnAccountTransfer);
        logout = findViewById(R.id.btnLogout);

        balance.setOnClickListener(this);
        transfer.setOnClickListener(this);
        logout.setOnClickListener(this);
    }


    public void declareVariables() {
        Intent p = getIntent();
        String email = p.getExtras().getString("email");
        welcome = findViewById(R.id.txtLoginWelcome);
        DatabaseHelper db = new DatabaseHelper(this);
        user = db.getUserDetails(email);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnAccountBalance:
                Intent balances = new Intent(this, AccountBalanceActivity.class);
                balances.putExtra("email", user.getEMAIL());
                startActivity(balances);
                break;
            case R.id.btnAccountTransfer:
                Intent transfers = new Intent(this, AccountTransferActivity.class);
                transfers.putExtra("email", user.getEMAIL());
                startActivity(transfers);
                break;
            case R.id.btnLogout:
                Intent logout = new Intent(this, LoginActivity.class);
                logout.putExtra("finish", true);
                logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logout);
                finish();
                Toast.makeText(this, "You successfully logged out.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}