package com.smalsus.pagerestapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsDisplayActivity extends AppCompatActivity {

    private List<FriendsModel> usersList;

    public static String userID = "user_id";
    public static String userToken = "authToken";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_display);

        Intent intent = getIntent();
        String UID = intent.getStringExtra(userID);
        String T = intent.getStringExtra(userToken);

        Call<List<Friends>> call = RetrofitFriendClient
                .getInstance()
                .getNewApi()
                .getFriends(UID, T);
        call.enqueue(
                new Callback<List<Friends>>() {
                    @Override
                    public void onResponse(Call<List<Friends>> call, Response<List<Friends>> response) {
                            if(response.body() != null)
                            {
                                Toast.makeText(getApplicationContext(), "Data Fetched", Toast.LENGTH_LONG).show();
                                RecyclerView recyclerView = findViewById(R.id.RECYCLER_VIEW_ID);
                                usersList = response.body().get(0).getAssociateList();
                                UsersAdapter adapter = new UsersAdapter(getApplicationContext(), usersList);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(adapter);
                            }
                            else Toast.makeText(getApplicationContext(), "Data Empty", Toast.LENGTH_LONG).show();
                        }
                        @Override
                    public void onFailure(Call<List<Friends>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Data Unreachable", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
