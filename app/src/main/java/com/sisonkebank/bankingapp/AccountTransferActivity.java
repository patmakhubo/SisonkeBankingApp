package com.sisonkebank.bankingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Objects;

import Data.DatabaseHelper;
import Model.User;

import static java.lang.String.format;

public class AccountTransferActivity extends MainPageActivity implements AdapterView.OnItemSelectedListener {
    private TextView current;
    private TextView savings;
    private EditText amount;
    private boolean type;
    private Spinner accountType;
    private User userinfo;
    private DatabaseHelper database;

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        this.current = findViewById(R.id.txtCurrentBalance);
        this.savings = findViewById(R.id.txtSavingsBalance);
        this.amount = findViewById(R.id.txtTransferAmount);
        accountType = findViewById(R.id.spTransferOptions);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent extras = getIntent();
        String email;

        if (extras != null) {
            email = Objects.requireNonNull(Objects.requireNonNull(extras.getExtras())).getString("email");
            this.database = new DatabaseHelper(this);
            this.userinfo = database.getUserDetails(email);
            this.current.setText(format("%.2f",userinfo.getCURRENT_BALANCE()));
            this.savings.setText(format("%.2f",userinfo.getSAVINGS_BALANCE()));
        }
        accountType.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] accounts = getResources().getStringArray(R.array.transferoptions);
        String selected = accountType.getSelectedItem().toString();
        for (String s : accounts) {
            type = (selected.equals(s));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        String[] accounts = getResources().getStringArray(R.array.transferoptions);
        String selected = accountType.getSelectedItem().toString();
        for (String account : accounts) {
            type = (selected.equals(account));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
    private boolean hasBlankInputs() {
        return amount.getText().toString().isEmpty()  ;
    }
    @SuppressLint("DefaultLocale")
    public void transferAmount(View view) {
        if(hasBlankInputs()){
            Toast.makeText(this,"Please fill in the whole form enter an amount.", Toast.LENGTH_SHORT).show();
        }else {
            double sum1;
            double sum2;
            if(!type){
                if(userinfo.getCURRENT_BALANCE() > Double.parseDouble(amount.getText().toString())) {
                    sum1 = userinfo.getCURRENT_BALANCE() - Double.parseDouble(amount.getText().toString());
                    sum2 = userinfo.getSAVINGS_BALANCE() + Double.parseDouble(amount.getText().toString());
                    userinfo.setCURRENT_BALANCE(sum1);
                    userinfo.setSAVINGS_BALANCE(sum2);
                    int i = database.updateBalance(userinfo);
                    if (i == 1){
                        Toast.makeText(this,"Transfer completed successfully.", Toast.LENGTH_SHORT).show();
                        this.current.setText(format("%.2f",userinfo.getCURRENT_BALANCE()));
                        this.savings.setText(format("%.2f",userinfo.getSAVINGS_BALANCE()));
                    }
                } else {
                    Toast.makeText(this,"You have insufficient current funds", Toast.LENGTH_SHORT).show();
                }
            } else {
                if(userinfo.getSAVINGS_BALANCE() > Double.parseDouble(amount.getText().toString())) {
                    sum1 = userinfo.getSAVINGS_BALANCE() - Double.parseDouble(amount.getText().toString());
                    sum2 = userinfo.getCURRENT_BALANCE() + Double.parseDouble(amount.getText().toString());
                    userinfo.setCURRENT_BALANCE(sum2);
                    userinfo.setSAVINGS_BALANCE(sum1);
                    int i = database.updateBalance(userinfo);
                    if (i == 1){
                        Toast.makeText(this,"Transfer completed successfully.", Toast.LENGTH_SHORT).show();
                        this.current.setText(format("%.2f",userinfo.getCURRENT_BALANCE()));
                        this.savings.setText(format("%.2f",userinfo.getSAVINGS_BALANCE()));
                    }
                } else {
                    Toast.makeText(this,"You have insufficient savings funds", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}