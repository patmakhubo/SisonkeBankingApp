package com.sisionkebank.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Pattern;

import Data.DatabaseHelper;
import Model.User;

public class RegistrationActivity extends AppCompatActivity implements Credentials {

    private EditText txtName;
    private EditText txtSurname;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtMobile;
    private RadioGroup rgGender;
    private String selectedGender;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        declareVariables();

    }
    @Override
    public void processLogin(View view) {
        Intent register = new Intent(this, LoginActivity.class);
        startActivity(register);
    }
    @Override
    public void processRegistration(View view) {
        if(hasBlankInputs()){
            Toast.makeText(this,"Please fill in the whole form ", Toast.LENGTH_SHORT).show();
        }else {
            processGender();
            if(!isNameValid(txtName.getText().toString())) {
                Toast.makeText(this,"Please enter a valid name (example: john).", Toast.LENGTH_SHORT).show();
            }else if(!isNameValid(txtSurname.getText().toString())) {
                Toast.makeText(this,"Please enter a valid surname (example: doe).", Toast.LENGTH_SHORT).show();
            }else if(!isValidEmail()){
                Toast.makeText(this,"Please enter a valid email address (example: john.doe@mail.com).", Toast.LENGTH_SHORT).show();
            }else if (!isValidPassword()){
                Toast.makeText(this,"Password is too short. It should at least be five characters long.", Toast.LENGTH_SHORT).show();
            }else if(!isValidMobile()){
                Toast.makeText(this,"Please enter a valid number (example: 0712324344).", Toast.LENGTH_SHORT).show();
            }else if(selectedGender.equals("None")){
                Toast.makeText(this,"Please select a gender ", Toast.LENGTH_SHORT).show();
            }else {
                DatabaseHelper db = new DatabaseHelper(this);
                if(!db.isRegistered(txtEmail.getText().toString())){
                    Toast.makeText(this,txtPassword.getText().toString(), Toast.LENGTH_LONG).show();
                    User user = new User();
                    user.setNAME(txtName.getText().toString());
                    user.setSURNAME(txtSurname.getText().toString());
                    user.setEMAIL(txtEmail.getText().toString());
                    user.setPASSWORD(txtPassword.getText().toString());
                    user.setMOBILE(txtMobile.getText().toString());
                    user.setGENDER(selectedGender);
                    user.setSAVINGS_BALANCE(100.50);
                    user.setCURRENT_BALANCE(2000.00);
                    db.addUser(user);
                    Intent register = new Intent(this,MainPageActivity.class );
                    Toast.makeText(this,"You successfully created an account.", Toast.LENGTH_SHORT).show();
                    startActivity(register);
                }else {
                    Toast.makeText(this,"User does exist", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void declareVariables() {
        this.txtName = findViewById(R.id.txtName);
        this.txtSurname = findViewById(R.id.txtSurname);
        this.txtEmail = findViewById(R.id.txtEmail);
        this.txtMobile = findViewById(R.id.txtMobile);
        this.rgGender = (RadioGroup) findViewById(R.id.genderOptions);
        this.txtPassword = findViewById(R.id.password);
    }

    public boolean isNameValid(String name){
        return name.matches("^([A-Za-z]+)(\\s[A-Za-z]+)*\\s?$");
    }
    private boolean hasBlankInputs() {
        return txtName.getText().toString().isEmpty() ||
                txtSurname.getText().toString().isEmpty() ||
                txtEmail.getText().toString().isEmpty() ||
                txtMobile.getText().toString().isEmpty() ;
    }

    private boolean isValidEmail(){
        return Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches();
    }

    private boolean isValidMobile() {
        String phone = txtMobile.getText().toString();
        return Pattern.matches("\\d+", phone);
    }
    private boolean isValidPassword(){
        return txtPassword.getText().toString().length() > 4;
    }

    private void processGender() {
        int rbCheck = rgGender.getCheckedRadioButtonId();
        switch (rbCheck){
            case R.id.rbMale:
                selectedGender = "Male";
                break;
            case R.id.rbFemale:
                selectedGender = "Female";
                break;
            default:
                selectedGender = "None";break;
        }
    }
}