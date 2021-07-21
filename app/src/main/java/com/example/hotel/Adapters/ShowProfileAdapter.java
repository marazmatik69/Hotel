package com.example.hotel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.Models.User;
import com.example.hotel.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowProfileAdapter extends RecyclerView.Adapter<ShowProfileAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public ShowProfileAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.show_profile_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = list.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.phone.setText(user.getPhone());
        holder.password.setText(user.getPassword());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        EditText name, email, phone, password;
        Button updateData, seeAllReservations, updateEmail, updatePassword;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.valueName);
            email = itemView.findViewById(R.id.valueEmail);
            phone = itemView.findViewById(R.id.valuePhone);
            password = itemView.findViewById(R.id.valuePassword);

            updateData = itemView.findViewById(R.id.updateData);
            updateEmail = itemView.findViewById(R.id.updateEmail);
            updatePassword = itemView.findViewById(R.id.updatePassword);

            String id = FirebaseAuth.getInstance().getUid();

            updateData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newName = name.getText().toString();
                    String newPhone = phone.getText().toString();
                    String newPassword = password.getText().toString();
                    String newEmail = email.getText().toString();
                    updateData(id, newName, newEmail, newPassword, newPhone);
                }
            });

            updatePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newName = name.getText().toString();
                    String newPhone = phone.getText().toString();
                    String newPassword = password.getText().toString();
                    String newEmail = email.getText().toString();
                    updatePassword(id, newName, newEmail, newPassword, newPhone);
                }
            });

            updateEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newName = name.getText().toString();
                    String newPhone = phone.getText().toString();
                    String newPassword = password.getText().toString();
                    String newEmail = email.getText().toString();
                    updateEmail(id, newName, newEmail, newPassword, newPhone);

                }
            });
        }

        private  void updateData (String id, String name, String email, String password, String phone) {
            DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(id);
            User user = new User(name, email, password, phone);
            db.setValue(user);
            Toast.makeText(context, "Данные были изменены", Toast.LENGTH_LONG).show();
        }

        private void updateEmail (String id, String name, String email, String password, String phone) {
            FirebaseUser userFirebase = FirebaseAuth.getInstance().getCurrentUser();
            userFirebase.updateEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Почта изменена успешно", Toast.LENGTH_LONG).show();
                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(id);
                                User user = new User(name, email, password, phone);
                                db.setValue(user);
                            }
                            else {
                                Toast.makeText(context, "Почта не была изменена. Повторите попытку", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

        private void updatePassword (String id, String name, String email, String password, String phone) {
            FirebaseUser userFirebase = FirebaseAuth.getInstance().getCurrentUser();
            userFirebase.updatePassword(password)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Смена пароля произошла успешно", Toast.LENGTH_LONG).show();
                                DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(id);
                                User user = new User(name, email, password, phone);
                                db.setValue(user);
                            }

                            else {
                                Toast.makeText(context, "Пароль не был изменен. Повторите попытку.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }
}