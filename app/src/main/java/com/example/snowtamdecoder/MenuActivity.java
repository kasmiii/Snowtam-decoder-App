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
    public List<SnowtamHash> hashList;
    public RecyclerView listView;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        relativeLayout = findViewById(R.id.relativeLayout);
        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(MenuActivity.this) {
            public void onSwipeTop() {
                //Toast.makeText(MenuActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Intent intent=new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(MenuActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Intent intent=new Intent(MenuActivity.this,SnowtamDecodeActivity.class);
                startActivity(intent);
                Toast.makeText(MenuActivity.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                //Toast.makeText(MenuActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

        listView=findViewById(R.id.list_codes);
        listView.setLayoutManager(new LinearLayoutManager(MenuActivity.this, RecyclerView.VERTICAL,false));
        //my new added code...
        getCodes();
        Global.currentCode=this.code_1;
        processData(Global.currentCode);
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
                //if(Global.currentCode!=this.code_1){
                    Global.currentCode=this.code_1;
                    //getAerodromeInformation();
                    //processData(Global.currentCode);
                    Intent intent1=new Intent(MenuActivity.this,SnowtamDecodeActivity.class);
                    startActivity(intent1);
                //}

                //Toast.makeText(getApplicationContext(),this.code_1,Toast.LENGTH_LONG).show();
                return true;
            case R.id.code_2:

                //if(Global.currentCode!=this.code_2){
                    Global.currentCode=this.code_2;
                    processData(Global.currentCode);
                //}
                /*Intent intent=new Intent(MenuActivity.this,MapsActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),this.code_2,Toast.LENGTH_LONG).show();*/
                return true;
            case R.id.code_3:
                //if(Global.currentCode!=this.code_3){
                    Global.currentCode=this.code_3;
                    processData(Global.currentCode);
                //}
                return true;
            case R.id.code_4:
                //if(Global.currentCode!=this.code_4){
                    Global.currentCode=this.code_4;
                    processData(Global.currentCode);
                //}
                return true;
            case R.id.home:
                Intent intent=new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"Item home Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getCodes(){
        Intent intent = getIntent();
        this.code_1=intent.getStringExtra("code1");
        this.code_2=intent.getStringExtra("code2");
        this.code_3=intent.getStringExtra("code3");
        this.code_4=intent.getStringExtra("code4");
    }

    public List<SnowtamHash> getAllCodes(String str){

        List<SnowtamHash> listOfHases=new ArrayList<SnowtamHash>();
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

        if(codeAerodrome.equals("") || codeAerodrome==null){
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
                                     Global.snowtamHashesGlobal=(ArrayList)hashList;

                                     for (SnowtamHash snowtamHash:hashList){
                                         System.out.println(snowtamHash.toString()+"\n");
                                     }
                                     MyAdapter codeAdapter=new MyAdapter(MenuActivity.this,hashList);//,HomeActivity.this);
                                     listView.setAdapter(codeAdapter);
                                 }
                             }
                         }
                         @Override
                         public void onFailure(Call<List<RetroSnowtam>> call, Throwable t) {
                             //System.out.println("error during requesting server ! verify first that the code is valid...");
                             Toast.makeText(MenuActivity.this, "error during requesting server ! verify first that the code is valid...", Toast.LENGTH_SHORT).show();
                         }
                     }
        );
        getAerodromeInformation();
    }

    public void getAerodromeInformation(){

        GetDataSnotam serviceStream = RetrofitClientSnotam.getRetrofitInstance().create(GetDataSnotam.class);

        //System.out.println("begin sending request to server...");
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
        //return aerodromeInformation;
    }

}
