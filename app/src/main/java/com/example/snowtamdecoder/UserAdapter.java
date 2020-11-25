/*
package com.example.snowtamdecoder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public Context context;
    public List<RetroUsers> retroUsers;

    public UserAdapter(Context context,List<RetroUsers> retroUsers){
        this.context=context;
        this.retroUsers=retroUsers;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sale_view= LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new UserAdapter.ViewHolder(sale_view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        holder.nameTextView.setText(this.retroUsers.get(position).getUser());

    }


    @Override
    public int getItemCount() {
        return retroUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            this.nameTextView=itemView.findViewById(R.id.user);
        }
    }

}
*/
