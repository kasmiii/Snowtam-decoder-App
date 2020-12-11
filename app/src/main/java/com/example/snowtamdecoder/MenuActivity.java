package com.example.snowtamdecoder;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {

    public String code_1,code_2,code_3,code_4;
    public ArrayList<SnowtamHash> hashList;
    public RecyclerView listView;
    RelativeLayout recyclerViewTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=findViewById(R.id.list_codes);
        listView.setLayoutManager(new LinearLayoutManager(MenuActivity.this, RecyclerView.VERTICAL,false));
        getCodes();
        Global.currentCode=Global.code1;
        processData(Global.currentCode);

        listView.setOnTouchListener(new OnSwipeTouchListener(MenuActivity.this) {
            public void onSwipeTop() {
                //Toast.makeText(MenuActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Intent intent=new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
            }
            public void onSwipeLeft() {
                Intent intent=new Intent(MenuActivity.this,SnowtamDecodeActivity.class);
                startActivity(intent);
            }
            public void onSwipeBottom() {
                //Toast.makeText(MenuActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.code_1:
                Global.currentCode=Global.code1;
                processData(Global.currentCode);
                return true;
            case R.id.code_2:
                    Global.currentCode=Global.code2;
                    processData(Global.currentCode);
                return true;
            case R.id.code_3:
                    Global.currentCode=Global.code3;
                    processData(Global.currentCode);

                return true;
            case R.id.code_4:
                    Global.currentCode=Global.code4;
                    processData(Global.currentCode);
                return true;
            case R.id.home:
                Intent intent=new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getCodes(){
        Intent intent = getIntent();
        Global.code1=intent.getStringExtra("code1");
        Global.code2=intent.getStringExtra("code2");
        Global.code3=intent.getStringExtra("code3");
        Global.code4=intent.getStringExtra("code4");
    }

    public ArrayList<SnowtamHash> getAllCodes(String str){

        ArrayList<SnowtamHash> listOfHases=new ArrayList<SnowtamHash>();
        int beginIndex=str.indexOf(')');
        int begin=beginIndex-2;
        int limitIndex=str.indexOf(".)\nCREATED");
        String snowtam=str.substring(begin,limitIndex);// begin-3
        HashMap<String,String> hashCodes=new HashMap<String, String>();
        int i=1;
        int step=i,difference=0;
        char indiceChar;
        ArrayList<String> listOfString=new ArrayList<>();
        char c[]=new char[20];
        int indice_char_table=0;
        while (i<snowtam.length()){
            step=i;
            while (snowtam.charAt(step)!=')'){
                step++;
                if (step==snowtam.length())break;
            }
            String code;
            if(i==1){
                code=snowtam.substring(i,step-1);
                indiceChar=snowtam.charAt(step-1);
            }
            else{
                code=snowtam.substring(i,step-2);
                indiceChar=snowtam.charAt(i-3);
            }
            if(!code.equals("")){
                listOfHases.add(new SnowtamHash(indiceChar+"",code,R.drawable.flight));
            }
            i=step+2;
        }
        return listOfHases;
    }

    public void processData(String codeAerodrome){
        GetDataSnotam serviceStream = RetrofitClientSnotam.getRetrofitInstance().create(GetDataSnotam.class);

        if(codeAerodrome==null){
            Toast.makeText(MenuActivity.this, "code is null or empty", Toast.LENGTH_SHORT).show();
        }
        Call<List<RetroSnowtam>> call = serviceStream.getStreams(codeAerodrome);

        call.enqueue(new Callback<List<RetroSnowtam>>() {
                         @Override
                         public void onResponse(Call<List<RetroSnowtam>> call, Response<List<RetroSnowtam>> response) {

                             for (RetroSnowtam retroSnowtam : response.body()) {
                                 if (retroSnowtam.getAll().contains("SNOWTAM")) {
                                     Global.currentRetroSnowtam = retroSnowtam;
                                     Global.currentSnowtam = Global.currentRetroSnowtam.getAll();
                                     hashList = getAllCodes(Global.currentSnowtam);
                                     Global.snowtamHashesGlobal=hashList;
                                     MyAdapter codeAdapter=new MyAdapter(MenuActivity.this,hashList);//,HomeActivity.this);
                                     listView.setAdapter(codeAdapter);
                                 }
                             }
                         }
                         @Override
                         public void onFailure(Call<List<RetroSnowtam>> call, Throwable t) {
                             Toast.makeText(MenuActivity.this, "error during requesting server ! verify first that the code is valid...", Toast.LENGTH_SHORT).show();
                         }
                     }
        );
        getAerodromeInformation();
    }

    public void getAerodromeInformation(){

        GetDataSnotam serviceStream = RetrofitClientSnotam.getRetrofitInstance().create(GetDataSnotam.class);

        Call<List<AerodromeInformation>> call = serviceStream.getAerodromeInformation(Global.currentCode);

        call.enqueue(new Callback<List<AerodromeInformation>>() {
                         @Override
                         public void onResponse(Call<List<AerodromeInformation>> call, Response<List<AerodromeInformation>> response) {
                             Global.aerodromeInfo=response.body().get(0);
                             System.out.println(Global.aerodromeInfo);
                         }
                         @Override
                         public void onFailure(Call<List<AerodromeInformation>> call, Throwable t) {
                             Toast.makeText(MenuActivity.this, "error during requesting server geolocalisation ! verify first that the code is valid...", Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }

}
