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

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {

    CountryCodePicker cpp;
    String countryCode;

    public static String userId = "user_id";
    public static String authToken = "authToken";

    String first_name;
    String last_name;
    String phone_number;
    String password;

    int selected_gender;
    int year;
    int month;
    int date;
    String dateOfBirth;

    /*TextView firstName = findViewById(R.id.FIRST_NAME);
    TextView lastName = findViewById(R.id.LAST_NAME);
    TextView birthOfDate = findViewById(R.id.DATE_OF_BIRTH);
    TextView gender = findViewById(R.id.GENDER);
    TextView phoneNumber = findViewById(R.id.PHONE_NUMBER);
    *//*TextView userName = findViewById(R.id.USER_NAME);
    TextView emailId = findViewById(R.id.EMAIL_ID);*//*
    TextView userPassword = findViewById(R.id.PASSWORD);*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        Intent intent = getIntent();
        final String userID = intent.getStringExtra(userId);
        final String authenticationToken = intent.getStringExtra(authToken);
        Button updateProfileButton = findViewById(R.id.BUTTON_SUBMIT_CHANGES_ID);
        TextView logInTextView = findViewById(R.id.TEXT_VIEW_LOG_IN_ID);
        updateProfileButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setData();
                        Call<ResponseBody> call = RetrofitUpdateUserDetailsClient
                                .getInstance()
                                .getNewApi()
                                .updateUserProfileDetails(userID, phone_number, first_name, last_name, selected_gender, dateOfBirth, password, authenticationToken);
                        call.enqueue(
                                new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if(response.body() != null)
                                        {
                                            Toast.makeText(UpdateProfileActivity.this, "User Profile Data is Updated.", Toast.LENGTH_LONG).show();
                                            /*firstName.setText(first_name);
                                            lastName.setText(last_name);
                                            birthOfDate.setText(dateOfBirth);
                                            gender.setText(selected_gender);
                                            phoneNumber.setText(phone_number);
                                            userPassword.setText(password);*/
                                        }
                                        else Toast.makeText(UpdateProfileActivity.this, "Updation operation failed.", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t)
                                    {
                                        Toast.makeText(UpdateProfileActivity.this, "Unable to access Database.", Toast.LENGTH_LONG).show();
                                    }
                                }
                        );

                    }
                }
        );
        logInTextView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent logInIntent = new Intent(UpdateProfileActivity.this, LogInActivity.class);
                        startActivity(logInIntent);
                    }
                }
        );
    }
    public void setData() {
        TextInputEditText fName = findViewById(R.id.TEXT_INPUT_FIELD_FIRST_NAME_ID);
        first_name = fName.getText().toString().trim();

        TextInputEditText lName = findViewById(R.id.TEXT_INPUT_FIELD_LAST_NAME_ID);
        last_name = lName.getText().toString().trim();

        cpp = findViewById(R.id.ccp);
        countryCode = cpp.getSelectedCountryCodeWithPlus();
        TextInputEditText pNumber = findViewById(R.id.TEXT_INPUT_EDIT_TEXT_PHONE_NUMBER_ID);
        phone_number = countryCode + "-" + pNumber.getText().toString().trim();

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DATE);
        showDate(year, month, date);

        RadioGroup gender_group = findViewById(R.id.GENDER_RADIO_GROUP_ID);
        int id = gender_group.getCheckedRadioButtonId();
        if (id == -1) {

        } else {
            switch (id) {
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

        TextInputEditText userPassword = findViewById(R.id.PASSWORD_EDIT_TEXT_ID);
        password = userPassword.getText().toString().trim();

        if (password.isEmpty()) {
            userPassword.setError("Password is required");
            userPassword.requestFocus();
        }
        if (password.length() > 50)
        {
            userPassword.setError("Password can atmost 50 characters long.");
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
        TextInputEditText displayDate = findViewById(R.id.TEXT_INPUT_EDIT_DATE_OF_BIRTH_ID);
        displayDate.setText(dateOfBirth);
    }
    public void onCallDatePicker(View view) {
        showDialog(999);
    }
}
