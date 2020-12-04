package com.example.snowtamdecoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class SnowtamDecodeActivity extends AppCompatActivity {

    public ArrayList<SnowtamDecode> listSnowtamsDecode;
    public RecyclerView listViewDecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snowtam_decode);
        listSnowtamsDecode=getListOfCodesInfos();
        //decodeSnowtams()
        listViewDecode=findViewById(R.id.list_info_decodes);
        listViewDecode.setLayoutManager(new LinearLayoutManager(SnowtamDecodeActivity.this, RecyclerView.VERTICAL,false));
        MyAdapterDecoder codeAdapterDecoder=new MyAdapterDecoder(SnowtamDecodeActivity.this,listSnowtamsDecode);//,HomeActivity.this);
        listViewDecode.setAdapter(codeAdapterDecoder);
        //decodeSnowtams();
    }

    public void decodeSnowtams(){
        //.........
    }

    public ArrayList<SnowtamDecode> getListOfCodesInfos(){
        ArrayList<SnowtamDecode> snowtamDecodeArrayList=new ArrayList<>();
        for (SnowtamHash snowtamHash:Global.snowtamHashesGlobal){
            snowtamDecodeArrayList.add(new SnowtamDecode(snowtamHash.getValue(),"valeur decode...",R.drawable.information));
        }
        return snowtamDecodeArrayList;
    }

}
