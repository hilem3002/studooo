package com.example.studooov2.UserSignUpLogin.Activities.PostActivities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Activities.loginActivities.ErrorMessageActivity;
import com.example.studooov2.UserSignUpLogin.Activities.notiticationActivity.BolumSecmeActivity;
import com.example.studooov2.UserSignUpLogin.Activities.profileActivities.profileActivity;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.ApiRequest;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.OnUsersLoadedListener;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.paging.UserAdapter;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.paging.userViewModel;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.example.studooov2.UserSignUpLogin.userSearchAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

public class SearchUsersActivity extends AppCompatActivity {

    TextInputEditText searchTextEdittext;
    int offset = 0;
    int pageSize = 50;
    RecyclerView userSearchRecycleview;
    MaterialButton cancelSearchButton;
    ApiRequest request;
    Intent intent;
    LinearLayoutManager manager;
    userSearchAdapter adapter;
    regularUser user;
    BottomNavigationView bottomMenu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_users_design);

        searchTextEdittext = findViewById(R.id.searchTextEdittext);
        userSearchRecycleview = findViewById(R.id.userSearchRecycleview);
        cancelSearchButton = findViewById(R.id.cancelSearchButton);
        request = new ApiRequest();
        manager = new LinearLayoutManager(SearchUsersActivity.this);

        user = (regularUser) getIntent().getParcelableExtra("user");
        bottomMenu = findViewById(R.id.bottommenu);
        bottomMenu.setSelectedItemId(R.id.action_search);
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.action_addpost) {
                    Intent intent1 = new Intent(getApplicationContext(), PostCreatingActivity.class);
                    intent1.putExtra("user",(Parcelable) user);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                }
                else if (item.getItemId()==R.id.action_home) {
                    Intent intent1 = new Intent(getApplicationContext(), PostSeeActivity.class);
                    intent1.putExtra("user",(Parcelable) user);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    finish();
                    return true;
                }
                else if (item.getItemId()==R.id.action_profile) {
                    Intent intent = new Intent(getApplicationContext(), profileActivity.class);
                    intent.putExtra("user",(Parcelable) user);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                }
                else if (item.getItemId()==R.id.action_notification) {
                    Intent intent = new Intent(getApplicationContext(), BolumSecmeActivity.class);
                    intent.putExtra("user",(Parcelable) user);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                }
                else {
                    return true;
                }
            }
        });

        cancelSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchTextEdittext.setText(null);
                userSearchRecycleview.setLayoutManager(null);
                userSearchRecycleview.setAdapter(null);
            }
        });

        searchTextEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (searchTextEdittext.getText().toString() != null) {

                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    manager.scrollToPosition(0);
                    userSearchRecycleview.setLayoutManager(manager);
                    userSearchRecycleview.setHasFixedSize(true);

                    try {

                        request.getRequestByFilter(offset, pageSize, searchTextEdittext.getText().toString(), new OnUsersLoadedListener() {
                            @Override
                            public void OnUsersLoading(Boolean loadingSuccess) {

                            }

                            @Override
                            public void OnUsersLoaded(List<regularUser> users) {
                                if (!users.isEmpty()) {

                                    adapter = new userSearchAdapter(users, SearchUsersActivity.this, user);
                                    userSearchRecycleview.setAdapter(adapter);
                                }
                            }

                            @Override
                            public void OnUsersLoadFailed(Boolean errorSuccess) {
                                errorPageExecution();
                            }
                        });

                    }
                    catch (Exception e) {

                        // error page executed
                        errorPageExecution();

                    }
                }

            }
        });

        /*userSearchRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = manager.getChildCount();
                int totalItemCount = manager.getItemCount();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    offset++;
                    Log.d("t", "son");
                    request.getRequestByFilter(offset, 8, searchTextEdittext.getText().toString(), new OnUsersLoadedListener() {
                        @Override
                        public void OnUsersLoading(Boolean loadingSuccess) {

                        }

                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void OnUsersLoaded(List<regularUser> users) {
                            if (!users.isEmpty()) {
                                adapter.addListToRegularUsers(users);
                            }
                        }

                        @Override
                        public void OnUsersLoadFailed(Boolean errorSuccess) {
                            errorPageExecution();
                        }
                    });
                }
            }
        });*/
    }

    private void errorPageExecution() {

        intent = new Intent(SearchUsersActivity.this, ErrorMessageActivity.class);
        startActivity(intent);

    }
}
