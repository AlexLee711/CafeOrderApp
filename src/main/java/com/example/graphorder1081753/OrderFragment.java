package com.example.graphorder1081753;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class OrderFragment extends Fragment {
    ListView lstPrefer;
    private Spinner spnPrefer;
    private SeekBar seekBar;
    private TextView seektext;
    private EditText edtRemarks;
    private RadioGroup rg1,rg2;
    private RadioButton radRice1,radRice2,radRice3,radRice4,radRice5,radSoup1,radSoup2;
    private CheckBox chkBlackTea,chkAmericano,chkLatte,chkMilkTea,chkBun,chkFocaccia,chkSalad;
    int[] addcost = new int[]{35,40,55,60,35,40,55};
    int AddCost;
    int cost;
    String strrice;
    String strsoup;
    String Addition = "";
    String utensil = "";
    String selStore ="";
    String Meal = "";
    String item;
    String[] Stores = new String[] {"台北港墘店","台北光復店(國父紀念館店)","台北民生敦北店","台北中山店","台北天母店","新北板橋府中店","台中勤美草悟道店","台中文心森林店"
            ,"高鐵左營Express店","高雄巨蛋店","高雄美館店","高雄駁二店","高雄文化店"};
    private int[] resIds = {R.drawable.chicken,R.drawable.pork,R.drawable.beef,R.drawable.salmon};
    String[] Meals = new String[] {"迷迭香烤雞餐","蒜泥松阪豬餐","紅燒牛腩餐","鮭魚香檸餐"};
    private Button btnList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_order, container,false);
        edtRemarks = (EditText)view.findViewById(R.id.edtRemarks);
        rg1 = (RadioGroup)view.findViewById(R.id.radGroup1);
        radRice1 = (RadioButton)view.findViewById(R.id.radRice1);
        radRice2 = (RadioButton)view.findViewById(R.id.radRice2);
        radRice3 = (RadioButton)view.findViewById(R.id.radRice3);
        radRice4 = (RadioButton)view.findViewById(R.id.radRice4);
        radRice5 = (RadioButton)view.findViewById(R.id.radRice5);
        rg1.setOnCheckedChangeListener(rg1CheckedChangeListener);
        rg2 = (RadioGroup)view.findViewById(R.id.radGroup2);
        radSoup1 = (RadioButton)view.findViewById(R.id.radSoup1);
        radSoup2 = (RadioButton)view.findViewById(R.id.radSoup2);
        rg2.setOnCheckedChangeListener(rg2CheckedChangeListener);
        chkBlackTea = (CheckBox)view.findViewById(R.id.chkBlackTea);
        chkAmericano = (CheckBox)view.findViewById(R.id.chkAmericano);
        chkLatte = (CheckBox)view.findViewById(R.id.chkLatte);
        chkMilkTea = (CheckBox)view.findViewById(R.id.chkMilkTea);
        chkBun = (CheckBox)view.findViewById(R.id.chkBun);
        chkFocaccia = (CheckBox)view.findViewById(R.id.chkFocaccia);
        chkSalad = (CheckBox)view.findViewById(R.id.chkSalad);

        chkBlackTea.setOnCheckedChangeListener(myListener);
        chkAmericano.setOnCheckedChangeListener(myListener);
        chkLatte.setOnCheckedChangeListener(myListener);
        chkMilkTea.setOnCheckedChangeListener(myListener);
        chkBun.setOnCheckedChangeListener(myListener);
        chkFocaccia.setOnCheckedChangeListener(myListener);
        chkSalad.setOnCheckedChangeListener(myListener);
        Switch sb = (Switch)view.findViewById(R.id.switchUtensil);
        sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sb.setText("需要");
                    utensil = "需要";
                }else{
                    sb.setText("不需要");
                    utensil = "不需要";
                }
            }
        });
        spnPrefer=(Spinner)view.findViewById(R.id.spnPrefer);
        ArrayAdapter<String> adapterStores = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,Stores);
        adapterStores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrefer.setAdapter(adapterStores);
        spnPrefer.setOnItemSelectedListener(spnPreferListener);

        lstPrefer = (ListView)view.findViewById(R.id.lstPrefer);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice,Meals);
        lstPrefer.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lstPrefer.setAdapter(arrayAdapter);
        lstPrefer.setOnItemClickListener(lstPreferListener);

        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seektext = (TextView) view.findViewById(R.id.seektext);
        seekBar.setMax(10);
        seekBar.setProgress(1);

        seektext.setText("數量："+seekBar.getProgress()+" / 最大數量" + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seektext.setText("數量："+seekBar.getProgress()+" / 最大數量" + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnList = (Button)view.findViewById(R.id.btnList);
        btnList.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(item == null ) {
                    Toast.makeText(getActivity(), "請先選取餐點！", Toast.LENGTH_SHORT).show();
                }else if( strrice == null){
                    Toast.makeText(getActivity(), "尚未選擇米飯種類！", Toast.LENGTH_SHORT).show();
                }
                else if( strsoup ==null){
                    Toast.makeText(getActivity(), "尚未選擇湯品種類！", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),List.class);
                    String selmeal = Meal;
                    String rice = strrice;
                    String soup = strsoup;
                    String Add = Addition;
                    String Utensil = utensil;
                    String Store = selStore;
                    int num = seekBar.getProgress();
                    int totalcost =(cost * num) + AddCost;
                    String remarks = edtRemarks.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("STORE",Store);
                    bundle.putString("MEALNAME",selmeal);
                    bundle.putInt("NUM",num);
                    bundle.putInt("COST",totalcost);
                    bundle.putString("REMARKS",remarks);
                    bundle.putString("RICE",rice);
                    bundle.putString("SOUP",soup);
                    bundle.putString("ADDITION",Add);
                    bundle.putString("UTENSIL",Utensil);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
    private AdapterView.OnItemClickListener lstPreferListener = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            item = parent.getItemAtPosition(position).toString();
            if(lstPrefer.isItemChecked(position)){
                Toast.makeText(getActivity(),"目前選取："+item,Toast.LENGTH_SHORT).show();
            }
            if(position == 0){
                cost = 150;
                Meal = Meals[0];
            }
            if(position == 1){
                cost = 170;
                Meal = Meals[1];
            }
            if(position == 2){
                cost = 160;
                Meal = Meals[2];
            }
            if(position == 3){
                cost = 180;
                Meal = Meals[3];
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener rg1CheckedChangeListener = new RadioGroup.OnCheckedChangeListener(){
        @Override

        public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId){
                case R.id.radRice1:
                    strrice = radRice1.getText().toString();
                    break;
                case R.id.radRice2:
                    strrice = radRice2.getText().toString();
                    break;
                case R.id.radRice3:
                    strrice = radRice3.getText().toString();
                    break;
                case R.id.radRice4:
                    strrice = radRice4.getText().toString();
                    break;
                case R.id.radRice5:
                    strrice = radRice5.getText().toString();
                    break;
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener rg2CheckedChangeListener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.radSoup1:
                    strsoup = radSoup1.getText().toString();
                    break;
                case R.id.radSoup2:
                    strsoup = radSoup2.getText().toString();
                    break;
            }
        }
    };

    private CheckBox.OnCheckedChangeListener myListener = new CheckBox.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String s1="",s2="",s3="",s4="",s5="",s6="",s7="";
            int i1,i2,i3,i4,i5,i6,i7;

            if(chkBlackTea.isChecked()){
                s1 = chkBlackTea.getText().toString()+" ";
                i1 = addcost[0];
            }else{
                s1="";
                i1=0;
            }
            if(chkAmericano.isChecked()){
                s2 = chkAmericano.getText().toString()+" ";
                i2 = addcost[1];
            }else{
                s2="";
                i2=0;
            }
            if(chkLatte.isChecked()){
                s3 = chkLatte.getText().toString()+" ";
                i3 = addcost[2];
            }else{
                s3="";
                i3=0;
            }
            if(chkMilkTea.isChecked()){
                s4 = chkMilkTea.getText().toString()+" ";
                i4 = addcost[3];
            }else{
                s4="";
                i4=0;
            }
            if(chkBun.isChecked()){
                s5 = chkBun.getText().toString()+" ";
                i5= addcost[4];
            }else{
                s5="";
                i5=0;
            }
            if(chkFocaccia.isChecked()){
                s6 = chkFocaccia.getText().toString()+" ";
                i6 = addcost[5];
            }else{
                s6="";
                i6=0;
            }
            if(chkSalad.isChecked()){
                s7 = chkSalad.getText().toString()+" ";
                i7 = addcost[6];
            }else{
                s7="";
                i7=0;
            }
            Addition = s1+s2+s3+s4+s5+s6+s7;
            AddCost = i1+i2+i3+i4+i5+i6+i7;
        }
    };
    private AdapterView.OnItemSelectedListener spnPreferListener = new Spinner.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            for(int i = 0;i<Stores.length;i++){
                if(position == i){
                    selStore = Stores[i];
                }
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


}