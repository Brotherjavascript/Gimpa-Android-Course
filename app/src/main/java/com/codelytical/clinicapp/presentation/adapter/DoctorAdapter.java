package com.codelytical.clinicapp.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codelytical.clinicapp.R;
import com.codelytical.clinicapp.data.model.DoctorModel;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {

    private List<DoctorModel> listItems;

    public void setListDataItems(List<DoctorModel> listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public DoctorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(listItems.get(position).getFirst_name()+" "+listItems.get(position).getLast_name());
        holder.tvEmail.setText(listItems.get(position).getEmail());
        Glide.with(holder.imageView)
                .load(listItems.get(position).getAvatar())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(listItems == null)
            return 0;
        else
            return listItems.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvName;
        TextView tvEmail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.avatar);

            tvName = itemView.findViewById(R.id.fullName);
            tvEmail = itemView.findViewById(R.id.email);


        }
    }
}
