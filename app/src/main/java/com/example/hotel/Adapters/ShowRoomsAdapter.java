package com.example.hotel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotel.Models.Room;
import com.example.hotel.R;

import java.util.ArrayList;

public class ShowRoomsAdapter extends RecyclerView.Adapter<ShowRoomsAdapter.MyViewHolder> {

    ArrayList<Room> mList;
    Context context;

    public ShowRoomsAdapter(Context context, ArrayList<Room> mList){
        this.context = context;
        this.mList = mList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.show_room_window, parent, false);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView number, category, price, n, info;
        ImageView url;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            number = itemView.findViewById(R.id.number);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.price);
            n = itemView.findViewById(R.id.n);
            info = itemView.findViewById(R.id.info);
            url = itemView.findViewById(R.id.url);

        }
    }
}
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Произошел клик на элемент списка! Вау", Toast.LENGTH_SHORT).show();
                }
            });*/
