package com.example.studooov2.UserSignUpLogin.Activities.PostActivities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.paging.UserAdapter;
import com.example.studooov2.UserSignUpLogin.InternetOperations.Data.paging.userViewModel;
import com.example.studooov2.UserSignUpLogin.Models.regularUser;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class denemePagingActivity extends AppCompatActivity {

    TextInputEditText searchTextEdittext;
    RecyclerView userSearchRecycleview;
    MaterialButton cancelSearchButton;
    UserAdapter adapter;
    userViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deneme_paging_design);

        searchTextEdittext = findViewById(R.id.searchTextEdittext);
        userSearchRecycleview = findViewById(R.id.userSearchRecycleview);
        cancelSearchButton = findViewById(R.id.cancelSearchButton);
        adapter = new UserAdapter(this);
        ViewModelProvider provider = new ViewModelProvider(this);
        model = provider.get(userViewModel.class);
        userSearchRecycleview.setLayoutManager(new LinearLayoutManager(this));
        userSearchRecycleview.setHasFixedSize(true);
        userSearchRecycleview.setAdapter(adapter);

        model.getList().observe(this, new Observer<PagingData<regularUser>>() {
            @Override
            public void onChanged(PagingData<regularUser> regularUserPagingData) {
                adapter.submitData(getLifecycle(), regularUserPagingData);
            }
        });

    }
}
