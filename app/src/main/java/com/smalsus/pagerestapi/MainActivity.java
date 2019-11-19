package com.smalsus.pagerestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CountryCodePicker cpp;
    String countryCode;

    String first_name;
    String last_name;
    String phone_number;
    String user_name;
    String email;
    String password;



    int selected_gender;
    int year;
    int month;
    int date;
    String dateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button userSignUp = findViewById(R.id.TETIL);
        userSignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InitializeAndValidateData();
                        String S = first_name + ", " + last_name + ", " + ", " + phone_number + ", " + selected_gender + ", " + dateOfBirth + ", " + password + ", " + user_name + ", " + email;
                        InitializeVariables iv = new InitializeVariables(first_name, last_name, phone_number,user_name,dateOfBirth,selected_gender, email, password);
                        Call<ResponseBody> call =  RetrofitClient
                                .getInstance()
                                .getApi()
                                .createUser(iv.getMobileNumber(), iv.getFirstName(), iv.getLastName(), iv.getGender(), iv.getDateOfBirth(), iv.getEmail(), iv.getUserName(), iv.getPassword());


                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    if (response.body() != null) {
                                        response.body().string();
                                        Toast.makeText(MainActivity.this, "Registration Successful.", Toast.LENGTH_LONG).show();
                                    }
                                    else Toast.makeText(MainActivity.this, "Internal Error, Please Try Again.", Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Registration failed.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );//End of the Register Button On Click Listener

        TextView userSignIn = findViewById(R.id.ELTIL);
        userSignIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                        startActivity(intent);
                    }
                }
        );//End of the Sign In TextView On Click Listener

    }//End of the onCreate Method



    public void InitializeAndValidateData() {
        TextInputEditText fName = findViewById(R.id.FIRST_NAME_EDIT_TEXT_ID);
        first_name = fName.getText().toString().trim();

        TextInputEditText lName = findViewById(R.id.LAST_NAME_EDIT_TEXT_ID);
        last_name = lName.getText().toString().trim();

        cpp = findViewById(R.id.ccp);
        countryCode = cpp.getSelectedCountryCodeWithPlus();
        TextInputEditText pNumber = findViewById(R.id.PHONE_NUMBER_EDIT_TEXT_ID);
        phone_number = countryCode + "-" + pNumber.getText().toString().trim();

        TextInputEditText uName = findViewById(R.id.USER_NAME_EDIT_TEXT_ID);
        user_name = uName.getText().toString().trim();

        if (user_name.isEmpty()) {
            uName.setError("Username is required.");
            uName.requestFocus();
            return;
        }
        if (user_name.length() > 50) {
            uName.setError("Username should be atmost 50 characters long");
            uName.requestFocus();
        }


        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DATE);
        showDate(year, month, date);

        RadioGroup gender_group = findViewById(R.id.GENDER_RADIO_GROUP_ID);
        int id = gender_group.getCheckedRadioButtonId();
        if(id == -1)
        {

        }
        else
        {
            switch (id)
            {
                case R.id.OTHERS_RADIO_BUTTON_ID:
                    selected_gender = 3;
                    break;
                case R.id.FEMALE_RADIO_BUTTON_ID:
                    selected_gender = 2;
                    break;
                case R.id.MALE_RADIO_BUTTON_ID:
                    selected_gender = 1;
                    break;
            }
        }


        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        TextInputEditText userEmail = findViewById(R.id.EMAIL_EDIT_TEXT_ID);
        email = userEmail.getText().toString().trim();
        if(!email.matches(emailPattern))
        {
            userEmail.setError("Invalid Email Address.");
            userEmail.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            userEmail.setError("Email Id is required.");
            userEmail.requestFocus();
        }
        TextInputEditText userPassword = findViewById(R.id.PASSWORD_EDIT_TEXT_ID);
        password = userPassword.getText().toString().trim();

        if (password.isEmpty()) {
            userPassword.setError("Password is required");
            userPassword.requestFocus();
        }
    }
    DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showDate(year, month, dayOfMonth);
        }
    };
    @Override
    public Dialog onCreateDialog(int id)
    {
        if(id == 999)
        {
            return new DatePickerDialog(this, myDateListener, year, month, date);
        }
        return  null;
    }
    public void showDate(int year, int month, int date)
    {
        this.year = year;
        this.month = month;
        this.date = date;
        dateOfBirth = this.year + "-" + (this.month + 1) + "-" + this.date;
        TextInputEditText displayDate = findViewById(R.id.SELECT_DATE_EDIT_TEXT_ID);
        displayDate.setText(dateOfBirth);
    }
    public void callDatePicker(View view) {
        showDialog(999);
    }
}

