package com.openclassrooms.entrevoisins.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.openclassrooms.entrevoisins.R;

public class DetailNeighbourgActivity extends AppCompatActivity {

    Button mFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbourg);

        mFavorites = findViewById(R.id.floatingActionButton);
    }
}