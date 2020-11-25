package com.example.snowtamdecoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button nextButton;
    private EditText firstCode,secondCode,thirdCode,fourthCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstCode=this.findViewById(R.id.home_activity_firstCode_input);
        secondCode=this.findViewById(R.id.home_activity_secondCode_input);
        thirdCode=this.findViewById(R.id.home_activity_thirdCode_input);
        fourthCode=this.findViewById(R.id.home_activity_fourthCode_input);

        nextButton=this.findViewById(R.id.main_next_btn);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getCodes();
                Intent intent=new Intent(MainActivity.this,SnowtamCodesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getCodes(){
        String getFirstCode=firstCode.getText().toString();
        String getSecondCode=secondCode.getText().toString();
        String getThirdCode=thirdCode.getText().toString();
        String getFourthCode=fourthCode.getText().toString();
        Toast.makeText(this, getFirstCode+":"+getSecondCode+":"+getThirdCode+":"+getFourthCode, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }


}
