package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotel.Adapters.ShowRoomsAdapter;
import com.example.hotel.Adapters.ShowRoomsAdapter2;
import com.example.hotel.Models.Reservation;
import com.example.hotel.Models.Room;
import com.example.hotel.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationA extends AppCompatActivity {

    EditText first, last, roomId, clientN;
    TextView result;
    Button btnReservation;
    DatabaseReference db;
    ConstraintLayout root;
    String allDate;

    ArrayList<Room> list;
    String email = FirebaseAuth.getInstance().getUid();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

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
        setContentView(R.layout.activity_reservation);

        db = FirebaseDatabase.getInstance().getReference();
        root = findViewById(R.id.reservation_activity);

        roomId = findViewById(R.id.roomId); roomId.setEnabled(false);
        first = findViewById(R.id.firstDate); first.setEnabled(false);
        last = findViewById(R.id.lastDate); last.setEnabled(false);
        clientN = findViewById(R.id.clientN); clientN.setEnabled(false);
        result = findViewById(R.id.result);
        btnReservation = findViewById(R.id.reservationRoom); btnReservation.setEnabled(false);

        //подстановка значений
        Intent intent = getIntent();
        roomId.setText(intent.getStringExtra("roomNumber"));
        clientN.setText(intent.getStringExtra("roomPeople"));
        first.setText(intent.getStringExtra("zaezd"));
        last.setText(intent.getStringExtra("viezd"));
        //clientId.setText(email);
        allDate = first.getText().toString().replaceAll("[.\\/\\-]","") + last.getText().toString().replaceAll("[.\\/\\-]","");


        list = new ArrayList<>();

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReservation();
            }
        });
        
        Query query = FirebaseDatabase.getInstance().getReference("Rooms/" + roomId.getText().toString() + "/allReservations")
                .orderByChild("allDate")
                //.equalTo(first.getText().toString().replaceAll("[.\\/\\-]", "") + last.getText().toString().replaceAll("[.\\/\\-]", ""));
                .startAt(first.getText().toString().replaceAll("[.\\/\\-]", ""))//+ first.getText().toString().replaceAll("[.\\/\\-]",""))
                .endAt(last.getText().toString().replaceAll("[.\\/\\-]", ""));// + last.getText().toString().replaceAll("[.\\/\\-]",""));
        query.addListenerForSingleValueEvent(valueEventListener);
        
    }

    private void btnReservation() {
        if (TextUtils.isEmpty(roomId.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Введите номер комнаты", Toast.LENGTH_LONG).show();
            return;
        }
        ;
        if (TextUtils.isEmpty(first.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Введите дату заезда", Toast.LENGTH_LONG).show();
            return;
        }
        ;
        if (TextUtils.isEmpty(last.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Введите дату выезда", Toast.LENGTH_LONG).show();
            return;
        }
        ;
        if (TextUtils.isEmpty(clientN.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Введите количество человек", Toast.LENGTH_LONG).show();
            return;
        }
        ;

        Toast.makeText(getApplicationContext(), "Номер забронирован!", Toast.LENGTH_LONG).show();
        //Reservation reservation = new Reservation(email, roomId.getText().toString(), first.getText().toString(), last.getText().toString(), clientN.getText().toString(), first.getText().toString() + "-" + last.getText().toString());
        Reservation reservation = new Reservation(email, roomId.getText().toString(), first.getText().toString(), last.getText().toString(), clientN.getText().toString(), allDate);
        db.child("Reservations").child(UUID.randomUUID().toString()).setValue(reservation);
        db.child("Rooms").child(roomId.getText().toString()).child("allReservations").child(UUID.randomUUID().toString()).setValue(reservation);
        //db.child("Users").child(email).child("allReservations").child(UUID.randomUUID().toString()).setValue(reservation);

        Intent intent = new Intent(ReservationA.this, Menu.class);
        startActivity(intent);
        finish();
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            list.clear();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                Room room = dataSnapshot.getValue(Room.class);
                list.add(room);
            }

            if (list.isEmpty()) {
                result.setText("Номер свободен и его можно забронировать");
                btnReservation.setEnabled(true);
            }
            else {
                result.setText("Номер занят! Пожалуйста, введите другую дату или выберите другой номер.");
                btnReservation.setEnabled(false);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
}