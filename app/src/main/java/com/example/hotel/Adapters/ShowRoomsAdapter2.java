package com.example.hotel.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotel.Menu;
import com.example.hotel.Models.Room;
import com.example.hotel.R;
import com.example.hotel.ReservationA;
import com.example.hotel.ViewForReservation;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Calendar;

public class ShowRoomsAdapter2 extends RecyclerView.Adapter<ShowRoomsAdapter2.MyViewHolder> {

    ArrayList<Room> mList;
    Context context;

    public ShowRoomsAdapter2(Context context, ArrayList<Room> mList){
        this.context = context;
        this.mList = mList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.show_reserv_room_window, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.number.setText(mList.get(position).getNumber());
        holder.category.setText(mList.get(position).getCategory());
        holder.price.setText(mList.get(position).getPrice());
        holder.n.setText(mList.get(position).getN());
        holder.info.setText(mList.get(position).getInfo());
        Glide
                .with(context)
                .load(mList.get(position).getUrl())
                .into(holder.url);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView number, category, price, n, info;
        ImageView url;
        Button btnReservation;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            number = itemView.findViewById(R.id.number);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.price);
            n = itemView.findViewById(R.id.n);
            info = itemView.findViewById(R.id.info);
            url = itemView.findViewById(R.id.url);
            btnReservation = itemView.findViewById(R.id.buttonReserv);

            btnReservation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ReservationA.class);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);

                    LayoutInflater inflater = LayoutInflater.from(context);
                    View search_window = inflater.inflate(R.layout.search_window, null);
                    dialog.setView(search_window);

                    final MaterialEditText zaezd = search_window.findViewById(R.id.datezaezda);
                    final MaterialEditText viezd = search_window.findViewById(R.id.dateviezda);


                    //Отмена
                    dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.dismiss();
                        }
                    });

                    //Найти
                    dialog.setPositiveButton("Найти", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            if (TextUtils.isEmpty(zaezd.getText().toString())) {
                                Snackbar.make(itemView, "Введите дату заезда", Snackbar.LENGTH_LONG).show();
                                return;
                            }

                            if (TextUtils.isEmpty(viezd.getText().toString())) {
                                Snackbar.make(itemView, "Введите дату выезда", Snackbar.LENGTH_LONG).show();
                                return;
                            }

                            if(zaezd.getText().toString().length() < 10){
                                Snackbar.make(itemView, "Дата введена неверно", Snackbar.LENGTH_LONG).show();
                                return;
                            }

                            if(viezd.getText().toString().length() < 10){
                                Snackbar.make(itemView, "Дата введена неверно", Snackbar.LENGTH_LONG).show();
                                return;
                            }

                            if(viezd.getText().toString().length() >10){
                                Snackbar.make(itemView, "Дата введена неверно", Snackbar.LENGTH_LONG).show();
                                return;
                            }

                            if(zaezd.getText().toString().length() > 10){
                                Snackbar.make(itemView, "Дата введена неверно", Snackbar.LENGTH_LONG).show();
                                return;
                            }

                            //Выбираем значения и переключаемся на активность ViewForReservation
                            Intent intent = new Intent(context, ReservationA.class);
                            intent.putExtra("zaezd", zaezd.getText().toString());
                            intent.putExtra("viezd", viezd.getText().toString());
                            intent.putExtra("roomNumber", number.getText().toString());
                            intent.putExtra("roomPeople", n.getText().toString());
                            context.startActivity(intent);
                        }
                    });
                    dialog.show();

                }
            });
        }
    }
}
