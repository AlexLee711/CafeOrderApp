package com.example.graphorder1081753;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Spinner;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private EditText id,pwd;
    private Spinner spnPrefer;

    String[] Counties = new String[] {"基隆市","台北市","新北市","桃園市","新竹市","新竹縣","苗栗縣","台中市","彰化縣","南投縣","雲林縣","嘉義市","嘉義縣","台南市","高雄市","屏東縣",
            "宜蘭縣","花蓮縣","台東縣","澎湖縣","金門縣","連江縣"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        id = (EditText)findViewById(R.id.id);
        pwd = (EditText)findViewById(R.id.pwd);
        spnPrefer=(Spinner) findViewById(R.id.spnPrefer);
        ArrayAdapter<String> adapterCounties = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Counties);
        adapterCounties.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrefer.setAdapter(adapterCounties);

    }
    public void registerok(View view){
        SharedPreferences result = getSharedPreferences("personal",0);
        String userid = id.getText().toString();
        String userpwd = pwd.getText().toString();
        if(userid.isEmpty() || userpwd.isEmpty()){
            Toast.makeText(getApplicationContext(), "帳號或密碼不可空白", Toast.LENGTH_SHORT).show();
        }else{
            result.edit()
                    .putString("uid",userid)
                    .putString("upwd",userpwd)
                    .commit();
            Toast.makeText(getApplicationContext(), "註冊成功", Toast.LENGTH_SHORT).show();
        }

        finish();

    }
    public void cancel(View view){
        finish();
    }
}
