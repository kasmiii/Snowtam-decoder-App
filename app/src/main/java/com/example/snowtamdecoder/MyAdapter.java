package com.example.snowtamdecoder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private List<SnowtamHash> snowtamHashList;
    private Context context;

    public MyAdapter(Context context, List<SnowtamHash> dataListCodes){
        this.snowtamHashList = dataListCodes;
        this.context=context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
//Get a reference to the Views in our layout//
        public final View myView;

        TextView textViewCode;
        TextView textViewValue;
        ImageView imageViewFlag;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            textViewCode=myView.findViewById(R.id.textView_code);
            textViewValue=myView.findViewById(R.id.textView_value);
            imageViewFlag=myView.findViewById(R.id.imageView_flag);
        }
    }

    @Override
//Construct a RecyclerView.ViewHolder//

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.code_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
//Set the data//

    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textViewCode.setText(snowtamHashList.get(position).getCode());
        holder.textViewValue.setText(snowtamHashList.get(position).getValue());
        holder.imageViewFlag.setImageResource(snowtamHashList.get(position).getIcon());
    }

//Calculate the item count for the RecylerView//

@Override
    public int getItemCount() {
        return snowtamHashList.size();
    }

}

