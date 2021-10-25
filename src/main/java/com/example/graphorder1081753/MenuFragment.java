package com.example.graphorder1081753;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    private int[] imageIds = {R.drawable.chicken,R.drawable.pork,R.drawable.beef,R.drawable.salmon};
    private ListView lstPrefer;
    private ImageView imgShow;
    private TextView txtPrice,txtDescript;
    String[] Meals = new String[] {"迷迭香烤雞餐","蒜泥松阪豬餐","紅燒牛腩餐","鮭魚香檸餐"};
    String[] MealsDescript = new String[]{"嚴選台灣土雞，採地中海式健康料理法","水煮日式松阪豬，搭配低鈉醬油與大蒜","少油、少鹽工法製成熟成牛肉，搭配胡蘿蔔與白蘿蔔","北歐風鮭魚，以檸檬汁佐特製健康醬料"};
    String[] Price = new String[]{"價格：$150","價格：$170","價格：$160","價格：$180"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_menu, container,false);
        lstPrefer = (ListView)view.findViewById(R.id.lstPrefer);
        imgShow = (ImageView)view.findViewById(R.id.imgShow);
        txtPrice = (TextView)view.findViewById(R.id.txtPrice);
        txtDescript = (TextView)view.findViewById(R.id.txtDescript);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,Meals);
        lstPrefer.setAdapter(arrayAdapter);
        lstPrefer.setOnItemClickListener(lstPreferListener);
        return view;
    }

    private AdapterView.OnItemClickListener lstPreferListener = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            imgShow.setImageResource(imageIds[position]);
            txtPrice.setText(Price[position]);
            txtDescript.setText(MealsDescript[position]);
        }
    };

}
