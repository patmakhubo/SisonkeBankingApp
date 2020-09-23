package com.sisonkebank.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Data.DatabaseHelper;
import Model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        declareVariables();
    }
    public void declareVariables() {
        Button btnLogin = findViewById(R.id.btnCreateAccount);
        this.txtEmail = findViewById(R.id.txtEmail);
        this.txtPassword = findViewById(R.id.password);
    }
    public void processLogin(View v) {
        if(hasBlankInputs()){
            Toast.makeText(this,"Please enter a username and a password. ", Toast.LENGTH_SHORT).show();
        } else {
            if(!isValidEmail()){
                Toast.makeText(this,"Please enter a valid email address (example: john.doe@mail.com).", Toast.LENGTH_SHORT).show();
            }else if (!isValidPassword()){
                Toast.makeText(this,"Password is too short. It should at least be five characters long.", Toast.LENGTH_SHORT).show();
            } else {
                DatabaseHelper db = new DatabaseHelper(this);
                if(db.isRegistered(txtEmail.getText().toString())){
                    User user = db.ValidateLogin(txtEmail.getText().toString(), txtPassword.getText().toString());
                    try{
                        if(user != null && user.getPASSWORD().equals(txtPassword.getText().toString())){
                        Toast.makeText(this,"You successfully logged in.", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(this,MainPageActivity.class );
                        login.putExtra("email", user.getEMAIL());
                        txtEmail.setText("");
                        txtPassword.setText("");
                        startActivity(login);
                    } }catch(Exception ex) {
                        Toast.makeText(this,"Incorrect password.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this,"Invalid credentials. Doesnt exist", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public boolean hasBlankInputs() {
        return txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty();
    }


    public boolean isValidEmail(){
        return Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches();
    }

    public boolean isValidPassword(){
        return txtPassword.getText().toString().length() > 4;
    }

    public void processRegistration(View view) {
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }
}