package com.smalsus.pagerestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {
    String log_in_email;
    String log_in_password;

    String userDetailsBody;

    String gender_string;

    String date_string = "";

    Date date;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button bLIn = findViewById(R.id.LOG_IN_BUTTON_ID);
        TextView bURId = findViewById(R.id.NEW_USER_ID);


        bLIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getAcceptance();
                        progress = new ProgressDialog(LogInActivity.this);
                        progress.setMessage("Fetching Data From The Server");
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setIndeterminate(true);
                        progress.setProgress(0);
                        progress.show();

                        final int progress_time = 10;
                        Thread t = new Thread(){
                            @Override
                            public void run() {
                                int jumpTime = 0;
                                while(jumpTime < progress_time)
                                {
                                    try {
                                        sleep(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    jumpTime += 5;
                                    progress.setProgress(jumpTime);
                                }
                            }
                        };
                        t.start();

                        Call<LoginResponseUserDetails> call = RetrofitClientLogIn
                                .getInstance()
                                .getNewApi()
                                .accessUser(log_in_email, log_in_password);


                        call.enqueue(new Callback<LoginResponseUserDetails>() {
                            @Override
                            public void onResponse(Call<LoginResponseUserDetails> call, Response<LoginResponseUserDetails> response) {
                                if (response.body() != null) {
                                    Toast.makeText(LogInActivity.this, "User Verified, Access Granted", Toast.LENGTH_LONG).show();
                                    response.body();
                                    String phone = response.body().getPhoneNumber();
                                    String first = response.body().getFirstName();
                                    String lastName = response.body().getLastName();
                                    int gender = response.body().getGender();
                                    Date dateOfBirth = response.body().getDateBirth();
                                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                                    String strDate = formatter.format(dateOfBirth);
                                    String user = response.body().getUserName();
                                    String email = response.body().getEmailID();
                                    String loginToken = response.body().getAuthToken();
                                    String userId = response.body().getId();
                                    String imageURL = response.body().getImageUrl();
                                    if(gender == 2)
                                    {
                                        gender_string = "Female";
                                    }
                                    if(gender == 3)
                                    {
                                        gender_string = "Others";
                                    }
                                    if(gender == 1)
                                    {
                                        gender_string = "Male";
                                    }
                                    /*String user_details_string = new String("First Name: " + first + "\n\n" +
                                            "Last Name: " + lastName + "\n\n" + "Date Of Birth: " +
                                            strDate + "\n\n" + "Gender: " + gender_string +
                                            "\n\n" + "Phone Number: " + phone + "\n\n" + "User Name: " + user + "\n\n" +
                                            "Email ID: " + email + "\n\n" + "Password: " + log_in_password);*/
                                    Intent intent = new Intent(LogInActivity.this, FetchAndDisplay.class);
                                    /*intent.putExtra(FetchAndDisplay.details, user_details_string);*/
                                    intent.putExtra(FetchAndDisplay.authenticationToken, loginToken);
                                    intent.putExtra(FetchAndDisplay.imageUrl, imageURL);
                                    intent.putExtra(FetchAndDisplay.userId, userId);
                                    intent.putExtra(FetchAndDisplay.firstName, first);
                                    intent.putExtra(FetchAndDisplay.last_Name, lastName);
                                    intent.putExtra(FetchAndDisplay.phoneNumber, phone);
                                    intent.putExtra(FetchAndDisplay.userGender, gender_string);
                                    intent.putExtra(FetchAndDisplay.date_of_birth, strDate);
                                    intent.putExtra(FetchAndDisplay.userName, user);
                                    intent.putExtra(FetchAndDisplay.email, email);
                                    intent.putExtra(FetchAndDisplay.userPassword, log_in_password);
                                    startActivity(intent);
                                }
                                else Toast.makeText(LogInActivity.this, "Internal Error, Please Try Again.", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<LoginResponseUserDetails> call, Throwable t) {
                                Toast.makeText(LogInActivity.this, "Access Denied!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
        );

        bURId.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LogInActivity.this, ScrollingActivity .class);
                        startActivity(intent);
                    }
                }
        );
    }
    public void getAcceptance()
    {
        TextInputEditText tvREmail = findViewById(R.id.CHECK_EMAIL_EDIT_TEXT_ID);
        TextInputEditText tvRPassword = findViewById(R.id.CHECK_PASSWORD_EDIT_TEXT_ID);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        log_in_email = tvREmail.getText().toString().trim();
        if(!log_in_email.matches(emailPattern))
        {
            tvREmail.setError("Please enter a valid email address");
            tvREmail.requestFocus();
            return;
        }
        if(log_in_email.isEmpty())
        {
            tvREmail.setError("This field should not be empty");
            tvREmail.requestFocus();
        }
        log_in_password = tvRPassword.getText().toString().trim();
        if(log_in_password.isEmpty())
        {
            tvRPassword.setError("This field should not be empty.");
            tvRPassword.requestFocus();
            return;
        }
    }
}
