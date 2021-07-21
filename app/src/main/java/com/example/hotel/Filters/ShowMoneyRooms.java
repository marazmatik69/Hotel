package com.example.hotel.Filters;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hotel.Adapters.ShowRoomsAdapter;
import com.example.hotel.Models.Room;
import com.example.hotel.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowMoneyRooms extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView searchMoneyStart;
    TextView searchMoneyEnd;
    ArrayList<Room> list;
    ShowRoomsAdapter adapter;
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

        setContentView(R.layout.activity_show_money_rooms);

        recyclerView = findViewById(R.id.showMoneyRooms);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchMoneyStart = findViewById(R.id.valueMonStart);
        searchMoneyEnd = findViewById(R.id.valueMonEnd);
        Intent intent = getIntent();
        searchMoneyStart.setText(intent.getStringExtra("MoneyStart"));
        searchMoneyEnd.setText(intent.getStringExtra("MoneyEnd"));


        list = new ArrayList<>();
        adapter = new ShowRoomsAdapter(this, list);
        recyclerView.setAdapter(adapter);

        Query query = root
                .orderByChild("price")
                .startAt(searchMoneyStart.getText().toString())
                .endAt(searchMoneyEnd.getText().toString());
        query.addListenerForSingleValueEvent(valueEventListener);
    }



    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            //list.clear();
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