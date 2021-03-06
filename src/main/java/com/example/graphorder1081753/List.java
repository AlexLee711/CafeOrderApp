package com.example.graphorder1081753;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class List extends AppCompatActivity {
    private static final String FILENAME = "List.txt";
    private TextView txtList;
    private RadioGroup rgPay;
    private RadioButton rd1,rd2;
    String pay;
    int cost;
    int a=10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        txtList = (TextView) findViewById(R.id.txtlist);
        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(btnBackListener);
        Button btnPay = (Button)findViewById(R.id.btnPay);
        btnPay.setOnClickListener(btnPayListener);
        rgPay = (RadioGroup) findViewById(R.id.rgPay);
        rd1 = (RadioButton) findViewById(R.id.rad1);
        rd2 = (RadioButton) findViewById(R.id.rad2);
        rgPay.setOnCheckedChangeListener(rgPayListener);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String store = bundle.getString("STORE");
        String name = bundle.getString("MEALNAME");
        int number = bundle.getInt("NUM");
        String rice = bundle.getString("RICE");
        String soup = bundle.getString("SOUP");
        String remarks = bundle.getString("REMARKS");
        String addition = bundle.getString("ADDITION");
        String utensil = bundle.getString("UTENSIL");
        cost = bundle.getInt("COST");
        String str = "???????????????"+store + "\n" +"?????????"+name + "\n" + "?????????" + number + "\n" + "?????????" + rice+ "\n" + "?????????" + soup + "\n" + "???????????????" + addition + "\n" + "?????????" + remarks + "\n" + "?????????" + cost
                + "\n" + "?????????" + utensil;
       txtList.setText(str);

    }



    private Button.OnClickListener btnBackListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            txtList.setText("");
            finish();
        }
    };
    private Button.OnClickListener btnPayListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            Random x =new Random();
            a = x.nextInt(1000000);

            if(pay == null){
                Toast toast = Toast.makeText(List.this,"????????????????????????",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(List.this);
                builder.setTitle("??????????????????");
                builder.setMessage("???????????????"+ cost + "\n" + "???????????????" + pay + "\n" + "???????????????" + a);
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener rgPayListener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId){
                case R.id.rad1:
                    pay = rd1.getText().toString();
                    break;
                case R.id.rad2:
                    pay = rd2.getText().toString();
                    break;

            }
        }
    };
}
