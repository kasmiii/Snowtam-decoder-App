package com.example.snowtamdecoder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapterDecoder extends RecyclerView.Adapter<MyAdapterDecoder.CustomViewHolder> {

    private List<SnowtamDecode> SnowtamsDecodeHashList;
    private Context context;

    public MyAdapterDecoder(Context context, List<SnowtamDecode> dataListSnowtamsDecodes){
        this.SnowtamsDecodeHashList = dataListSnowtamsDecodes;
        this.context=context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        //Get a reference to the Views in our layout//
        public final View myView;

        TextView textViewCode;
        TextView textViewDecodeValue;
        ImageView imageViewFlag;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            textViewCode=myView.findViewById(R.id.textView_code_decode_activity);
            textViewDecodeValue=myView.findViewById(R.id.textView_value_decode_activity);
            imageViewFlag=myView.findViewById(R.id.imageView_flag_decode_activity);
        }
    }

    @Override
//Construct a RecyclerView.ViewHolder//

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.code_decode_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textViewCode.setText(SnowtamsDecodeHashList.get(position).getInfo());
        holder.textViewDecodeValue.setText(SnowtamsDecodeHashList.get(position).getInfoDecode());
        holder.imageViewFlag.setImageResource(SnowtamsDecodeHashList.get(position).getFlag());
    }

//Calculate the item count for the RecylerView//

    @Override
    public int getItemCount() {
        return SnowtamsDecodeHashList.size();
    }

}