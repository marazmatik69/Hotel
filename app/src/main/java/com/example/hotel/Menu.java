package com.example.hotel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hotel.Filters.ShowCategoryRooms;
import com.example.hotel.Filters.ShowMoneyRooms;
import com.example.hotel.Filters.ShowPeopleRooms;
import com.example.hotel.Models.Review;
import com.example.hotel.Models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.UUID;

public class Menu extends AppCompatActivity {
    Button btnReservation, btnShowRooms, btnSearchCategory, btnSearchMoney, btnSearchPeople, btnProfile, btnMessage;
    String userId = FirebaseAuth.getInstance().getUid();
    DatabaseReference db = FirebaseDatabase.getInstance().getReference("Reviews");

    //RelativeLayout root;

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

        setContentView(R.layout.activity_menu);

        //root = findViewById(R.id.menu_activity);
        btnReservation = findViewById(R.id.btnReservation);
        btnShowRooms = findViewById(R.id.btnShowRooms);
        btnSearchCategory = findViewById(R.id.btnShowCategory);
        btnSearchMoney = findViewById(R.id.btnShowMoney);
        btnSearchPeople = findViewById(R.id.btnShowPeople);
        btnProfile = findViewById(R.id.btnProfile);
        btnMessage = findViewById(R.id.btnMessage);

        btnSearchCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchCategoryWindow();
            }
        });
        btnSearchMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchMoneyWindow();
            }
        });
        btnSearchPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchPeopleWindow();
            }
        });

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchWindow();
            }
        });
        btnShowRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ShowRooms.class);
                startActivity(intent);
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessageWindow();
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Profile.class);
                startActivity(intent);;
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void showSearchWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View search_category_window = inflater.inflate(R.layout.search_category_window, null);
        dialog.setView(search_category_window);

        Button btnStandart = search_category_window.findViewById(R.id.btnStandart);
        Button btnStudio = search_category_window.findViewById(R.id.btnStudio);
        Button btnLuxe = search_category_window.findViewById(R.id.btnLuxe);

        btnStandart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ViewForReservation.class);
                intent.putExtra("btnCategory", "Стандарт");
                startActivity(intent);
            }
        });
        btnStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ViewForReservation.class);
                intent.putExtra("btnCategory", "Студия");
                startActivity(intent);
            }
        });
        btnLuxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ViewForReservation.class);
                intent.putExtra("btnCategory", "Люкс");
                startActivity(intent);
            }
        });

        //Отмена
        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    private void showSearchCategoryWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View search_category_window = inflater.inflate(R.layout.search_category_window, null);
        dialog.setView(search_category_window);

        Button btnStandart = search_category_window.findViewById(R.id.btnStandart);
        Button btnStudio = search_category_window.findViewById(R.id.btnStudio);
        Button btnLuxe = search_category_window.findViewById(R.id.btnLuxe);

        btnStandart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ShowCategoryRooms.class);
                intent.putExtra("btnCategory", "Стандарт");
                startActivity(intent);
            }
        });
        btnStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ShowCategoryRooms.class);
                intent.putExtra("btnCategory", "Студия");
                startActivity(intent);
            }
        });
        btnLuxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, ShowCategoryRooms.class);
                intent.putExtra("btnCategory", "Люкс");
                startActivity(intent);
            }
        });

        //Отмена
        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();
    }

    private void showSearchMoneyWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View search_money_window = inflater.inflate(R.layout.search_money_window, null);
        dialog.setView(search_money_window);

        EditText textMoneyStart = search_money_window.findViewById(R.id.valueMoneyStart);
        EditText textMoneyEnd = search_money_window.findViewById(R.id.valueMoneyEnd);


        //Отмена
        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Найти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                try {
                    Intent intent = new Intent(Menu.this, ShowMoneyRooms.class);
                    intent.putExtra("MoneyStart", textMoneyStart.getText().toString());
                    intent.putExtra("MoneyEnd", textMoneyEnd.getText().toString());
                    startActivity(intent);
                } catch (ArithmeticException arithmeticException) {
                    Toast.makeText(getApplicationContext(), "Проверьте введенные данные", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
        dialog.show();
    }

    private void showSearchPeopleWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View search_people_window = inflater.inflate(R.layout.search_people_window, null);
        dialog.setView(search_people_window);

        EditText textPeople = search_people_window.findViewById(R.id.colvo);

        //Отмена
        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Найти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                    Intent intent = new Intent(Menu.this, ShowPeopleRooms.class);
                    intent.putExtra("People", textPeople.getText().toString());
                    startActivity(intent);

            }
        });
        dialog.show();
    }

    private void showMessageWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View message_window = inflater.inflate(R.layout.message_window, null);
        dialog.setView(message_window);

        final MaterialEditText clientEmailMessage = message_window.findViewById(R.id.clientEmailMessage);
        final MaterialEditText clientNameMessage = message_window.findViewById(R.id.clientNameMessage);
        final MaterialEditText typeMessage = message_window.findViewById(R.id.typeMessage);
        final MaterialEditText textMessage = message_window.findViewById(R.id.textMessage);

        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Отправить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                if (TextUtils.isEmpty(clientEmailMessage.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Введите e-mail", Toast.LENGTH_LONG).show();
                    return;
                }
                ;
                if (TextUtils.isEmpty(clientNameMessage.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Введите имя", Toast.LENGTH_LONG).show();
                    return;
                }
                ;
                if (TextUtils.isEmpty(typeMessage.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Введите тему сообщения", Toast.LENGTH_LONG).show();
                    return;
                }
                ;
                if (TextUtils.isEmpty(textMessage.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Введите сообщение", Toast.LENGTH_LONG).show();
                    return;
                }
                ;
                Review review = new Review(userId, clientNameMessage.getText().toString(), clientEmailMessage.getText().toString(), typeMessage.getText().toString(), textMessage.getText().toString());
                db.child(UUID.randomUUID().toString()).setValue(review);
                Toast.makeText(getApplicationContext(), "Сообщение отправлено", Toast.LENGTH_LONG).show();

            }
        });
        dialog.show();

    }
}