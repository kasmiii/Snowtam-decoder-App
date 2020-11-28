package com.example.snowtamdecoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                putCodes();
            }
        });
    }

    public void putCodes(){
        String getFirstCode=firstCode.getText().toString();
        String getSecondCode=secondCode.getText().toString();
        String getThirdCode=thirdCode.getText().toString();
        String getFourthCode=fourthCode.getText().toString();
        System.out.println(getFirstCode+" "+getSecondCode+" "+getThirdCode+" "+getFourthCode);
        Intent intent=new Intent(MainActivity.this,MenuActivity.class);
        intent.putExtra("code1",getFirstCode);
        intent.putExtra("code2",getSecondCode);
        intent.putExtra("code3",getThirdCode);
        intent.putExtra("code4",getFourthCode);
        startActivity(intent);
    }


}
