package com.example.snowtamdecoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
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
        listViewDecode.setOnTouchListener(new OnSwipeTouchListener(SnowtamDecodeActivity.this) {
            public void onSwipeTop() {
                //Toast.makeText(MenuActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Intent intent1=new Intent(SnowtamDecodeActivity.this,MenuActivity.class);
                startActivity(intent1);
            }
            public void onSwipeLeft() {
                Intent intent=new Intent(SnowtamDecodeActivity.this,MapsActivity.class);
                startActivity(intent);
            }
            public void onSwipeBottom() {

            }

        });
    }

    public String decodeB(String str){
        String result="";

        String day;
        String month;
        String year;
        String hour;
        String minute;

        month=str.substring(0,2);
        year="2020";
        day=str.substring(2,4);
        hour=str.substring(4,6);
        minute=str.substring(6,8);
        result+=day;
        int monthInt=Integer.parseInt(month);
        switch (monthInt){
            case 1:
                result+=" "+"January";
                break;
            case 2:
                result+=" "+"February";
                break;
            case 3:
                result+=" "+"March";
                break;
            case 4:
                result+=" "+"April";
                break;
            case 5:
                result+=" "+"May";
                break;
            case 6:
                result+=" "+"June";
                break;
            case 7:
                result+=" "+"July";
                break;
            case 8:
                result+=" "+"August";
                break;
            case 9:
                result+=" "+"September";
                break;
            case 10:
                result+=" "+"October";
                break;
            case 11:
                result+=" "+"November";
                break;
            case 12:
                result+=" "+"December";
                break;
            default:
                result+="";
        }
        result+=" 2020"+" "+hour+"H"+minute;

        return result;
    }

    public String decodeC(String str){
        String result="";
        String strNumber=str.substring(0,2);
        int runwayNumber=Integer.parseInt(strNumber);
        result="RUNWAY ";
        if(!str.contains("R")){
            result=result+runwayNumber+" LEFT";
        }

        else {
            runwayNumber+=30;
            result=result+runwayNumber+" RIGHT";
        }
        return result;
    }

    public String decodeD(String str){
        return "CLEARED RUNWAY LENGTH "+str+"M";
    }

    public String decodeE(String str){
        String result="CLEARED RUNWAY WIDTH ";//930R
        String numberMeter;
        int index;
        if(str.contains("R")){
            index=str.indexOf("R");
            numberMeter=str.substring(0,index);
            result+=numberMeter+"M RIGHT";
        }
        else {
            index = str.indexOf("L");
            numberMeter = str.substring(0, index);
            result += numberMeter + "M LEFT";
        }
        return result;
    }

    public String getExplication(int number){
        String result="";
        /*
        * 0 - PISTE DÉBLAYÉE ET SÈCHE (CLEAR AND DRY)
1 - HUMIDE (DAMP)
2 - MOUILLÉE OU FLAQUES D'EAU (WET or WATER PATCHES)
3 - GIVRE OU GELÉE BLANCHE - épaisseur normalement moins de 1 mm - (RIME OR FROST COVERED)
4 - NEIGE SÈCHE (DRY SNOW)
5 - NEIGE MOUILLÉE (WET SNOW)
6 - NEIGE FONDANTE (SLUSH)
7 - GLACE (ICE)
8 - NEIGE COMPACTÉE (COMPACTED OR ROLLED SNOW)
9 - ORNIÈRES ET ARÊTES (FROZEN RUTS OR RIDGES)
        * */
        switch (number){
            case 1:
                result+="DAMP";
                break;
            case 2:
                result+="WET or WATER PATCHES";
                break;
            case 3:
                result+="RIME OR FROST COVERED";
                break;
            case 4:
                result+="DRY SNOW";
                break;
            case 5:
                result+="WET SNOW";
                break;
            case 6:
                result+="SLUSH";
                break;
            case 7:
                result+="ICE";
                break;
            case 8:
                result+="COMPACTED OR ROLLED SNOW";
                break;
            case 9:
                result+="FROZEN RUTS OR RIDGES";
                break;
            default:
                result+="";
        }
        return result;
    }

    public String decodeF(String str){
        String result="";
        ArrayList<String> listOfStrings=new ArrayList<>();
        int start=0;
        int i=0;
        while(i<str.length()){
            if(str.charAt(i)=='/'){
                listOfStrings.add(str.substring(start,i));
                start=i+1;
                i++;
            }
            i++;
            if (i==str.length()){
                listOfStrings.add(str.substring(start,i));
            }
        }
        for (String string:listOfStrings){
            System.out.println(string+"\t");
        }
        int firstNumber=Integer.parseInt(listOfStrings.get(0));
        int secondNumber=Integer.parseInt(listOfStrings.get(1));
        int thirdNumber=Integer.parseInt(listOfStrings.get(2));
        String firstIndex="Threshold: ";
        String secondIndex="Mid runway: ";
        String thirdIndex="Roll out: ";

        result=result+firstIndex+" "+getExplication(firstNumber)+" / "
                +secondIndex+" "+getExplication(secondNumber)+" / "
                +thirdIndex+" "+getExplication(thirdNumber);

        return result;
    }


    public ArrayList<SnowtamDecode> getListOfCodesInfos(){
        ArrayList<SnowtamDecode> snowtamDecodeArrayList=new ArrayList<>();
        String valeur_decode="";
        for (SnowtamHash snowtamHash:Global.snowtamHashesGlobal){
            System.out.println("snowtam Letter::"+snowtamHash.getCode());
            switch(snowtamHash.getCode()){
                case 'A'+"":
                    valeur_decode="Aeroport "+Global.aerodromeInfo.getAirportName();
                    break;
                case 'B'+"":
                    valeur_decode=decodeB(snowtamHash.getValue());
                    break;
                case  'C'+"":
                    valeur_decode=decodeC(snowtamHash.getValue());
                    break;
                case  'D'+"":
                    valeur_decode=decodeD(snowtamHash.getValue());
                    break;
                case  'E'+"":
                    valeur_decode=decodeE(snowtamHash.getValue());
                    break;
                case  'F'+"":
                    valeur_decode=decodeF(snowtamHash.getValue());
                    break;
                default:
                    valeur_decode=snowtamHash.getValue();
            }
            snowtamDecodeArrayList.add(new SnowtamDecode(snowtamHash.getValue(),valeur_decode,R.drawable.information));
        }

        for (SnowtamDecode snowtamDecode:snowtamDecodeArrayList){
            System.out.println("decodegae:: "+snowtamDecode.getInfoDecode());
        }
        return snowtamDecodeArrayList;
    }

}
