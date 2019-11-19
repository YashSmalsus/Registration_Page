package com.smalsus.pagerestapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FetchAndDisplay extends AppCompatActivity {

    //public static String details="user_details_string";
    public static String authenticationToken = "loginToken";
    public static String userId = "userId";
    public static String imageUrl = "imageURL";
    TextView userDetails;
    CircleImageView circularImageView;

    public static String firstName= "first";
    public static String last_Name= "lastName";
    public static String date_of_birth= "strDate";
    public static String userGender= "gender_string";
    public static String phoneNumber= "phone";
    public static String userName= "user";
    public static String email= "email";
    public static String userPassword= "log_in_password";

    TextView user_firstName, user_lastName, user_birthOfDate, user_gender, user_phoneNumber, user_userName, user_emailId, user_userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_and_display);
        circularImageView = findViewById(R.id.USER_PROFILE_IMAGE);
        user_firstName = findViewById(R.id.FIRST_NAME);
        user_lastName = findViewById(R.id.LAST_NAME);
        user_birthOfDate = findViewById(R.id.DATE_OF_BIRTH);
        user_gender = findViewById(R.id.GENDER);
        user_phoneNumber = findViewById(R.id.PHONE_NUMBER);
        user_userName = findViewById(R.id.USER_NAME);
        user_emailId = findViewById(R.id.EMAIL_ID);
        user_userPassword = findViewById(R.id.PASSWORD);
        //userDetails = findViewById(R.id.USER_PROFILE_DETAILS);
        Intent intent = getIntent();
        Picasso.with(getBaseContext()).load(intent.getStringExtra(imageUrl)).into(circularImageView);
        user_firstName.setText(intent.getStringExtra(firstName));
        user_lastName.setText(intent.getStringExtra(last_Name));
        user_birthOfDate.setText(intent.getStringExtra(date_of_birth));
        user_gender.setText(intent.getStringExtra(userGender));
        user_phoneNumber.setText(intent.getStringExtra(phoneNumber));
        user_userName.setText(intent.getStringExtra(userName));
        user_emailId.setText(intent.getStringExtra(email));
        user_userPassword.setText(intent.getStringExtra(userPassword));
        //userDetails.setText(intent.getStringExtra(details));
    }
    public void onClickFriendsListButton(View view)
    {
        Intent getIntent = getIntent();
        String authToken = getIntent.getStringExtra(authenticationToken);
        String user_id = getIntent.getStringExtra(userId);
        Intent newIntent = new Intent(FetchAndDisplay.this, FriendsDisplayActivity.class);
        newIntent.putExtra(FriendsDisplayActivity.userID, user_id);
        newIntent.putExtra(FriendsDisplayActivity.userToken, authToken);
        startActivity(newIntent);
    }

    public void onClickLogOutButton(View view)
    {
        Intent intent = new Intent(FetchAndDisplay.this, LogInActivity.class);
        startActivity(intent);
    }

    public void onClickUpdateUserProfile(View view)
    {
        Intent getParameters = getIntent();
        String user_id = getParameters.getStringExtra(userId);
        String authToken = getParameters.getStringExtra(authenticationToken);
        Intent intent = new Intent(FetchAndDisplay.this, UpdateProfileActivity.class);
        intent.putExtra(UpdateProfileActivity.userId, user_id);
        intent.putExtra(UpdateProfileActivity.authToken, authToken);
        startActivity(intent);
    }
}

