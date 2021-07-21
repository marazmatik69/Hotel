package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hotel.Adapters.ShowRoomsAdapter;
import com.example.hotel.Adapters.ShowRoomsAdapter2;
import com.example.hotel.Models.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewForReservation extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView searchCategory;
    ArrayList<Room> list;
    ShowRoomsAdapter2 adapter;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference("Rooms");

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Set the local night mode to some value
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            // Now recreate for it to take effect
            recreate();
        }

        setContentView(R.layout.activity_view_for_reservation);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchCategory = findViewById(R.id.valueCategoryReserv);
        Intent intent = getIntent();
        searchCategory.setText(intent.getStringExtra("btnCategory"));

        list = new ArrayList<>();
        adapter = new ShowRoomsAdapter2(this, list);
        recyclerView.setAdapter(adapter);

        Query query = root
                .orderByChild("category")
                .equalTo(searchCategory.getText().toString());
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            list.clear();
            if (snapshot.exists()) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Room room = dataSnapshot.getValue(Room.class);
                    list.add(room);
                }
                adapter.notifyDataSetChanged();
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
}