package com.example.eiyoukun.mealFragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.eiyoukun.FoodActivity;
import com.example.eiyoukun.FoodAddActivity;
import com.example.eiyoukun.MySQLiteOpenHelper;
import com.example.eiyoukun.Product;
import com.example.eiyoukun.R;

import static androidx.core.content.ContextCompat.getSystemService;

public class PageFragment1 extends Fragment {

    private AutoCompleteTextView foodlist1;
    private AutoCompleteTextView foodlist2;
    private AutoCompleteTextView foodlist3;
    private AutoCompleteTextView foodlist4;
    private TextView calorieSum;
    private TextView protainSum;
    private TextView carbonSum;
    private TextView fatSum;
    private EditText foodGramForm1;

    private double foodgram = 0.0d;
    private double calorie = 0.0d;
    private double protain = 0.0d;
    private double carbon = 0.0d;
    private double fat = 0.0d;

    private MySQLiteOpenHelper mydb;
    private SQLiteDatabase db;

    private ArrayList<Product> products;
    private MySQLiteOpenHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup cotainer,
                             @Nullable Bundle saveInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.page_food1,cotainer
                        , false);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodlist1 = (AutoCompleteTextView)view.findViewById(R.id.foodForm1_1);
        foodlist2 = (AutoCompleteTextView)view.findViewById(R.id.foodForm1_2);
        foodlist3= (AutoCompleteTextView)view.findViewById(R.id.foodForm1_3);
        foodlist4= (AutoCompleteTextView)view.findViewById(R.id.foodForm1_4);
        calorieSum = view.findViewById(R.id.calorieForm1_1);
        protainSum = view.findViewById(R.id.proteinForm1_1);
        carbonSum = view.findViewById(R.id.carbonForm1_1);
        fatSum = view.findViewById(R.id.fatForm1_1);
        foodGramForm1 = view.findViewById(R.id.foodGramForm1_1);

        //データベースの取り込み
        mydb = new MySQLiteOpenHelper(requireActivity());
        db = mydb.getReadableDatabase();
        final String [] mydata;
        ArrayList<String> array = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        Cursor cr = db.rawQuery(sql, null);
        cr.moveToFirst();
        mydata = new String[cr.getCount()];
        int i = 0;
        do {
            mydata[i] = cr.getString(1);
            i ++;
        }while (cr.moveToNext()); {

            int id = cr.getInt(0);
            String foodName = cr.getString(1);
             foodgram = cr.getDouble(2);
             calorie = cr.getDouble(3);
             protain = cr.getDouble(4);
             carbon = cr.getDouble(5);
             fat = cr.getDouble(6);
            products.add(
                    new Product(id, foodName, foodgram, calorie, protain, carbon, fat)
            );
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.requireActivity(),
                android.R.layout.simple_dropdown_item_1line, mydata);
        foodlist1.setAdapter(adapter);
        foodlist2.setAdapter(adapter);
        foodlist3.setAdapter(adapter);
        foodlist4.setAdapter(adapter);

        Button foodButton1 = view.findViewById(R.id.foodButton1_1);
        //ボタンがクリックされた時の処理
        foodButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                double msg0 = calorie * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                double msg1 = protain * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                double msg2 = carbon * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                double msg3 = fat * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                calorieSum.setText(String.valueOf(msg0));
                protainSum.setText(String.valueOf(msg1));
                carbonSum.setText(String.valueOf(msg2));
                fatSum.setText(String.valueOf(msg3));
            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();

        mydb = new MySQLiteOpenHelper(requireActivity());
        db = mydb.getReadableDatabase();
        final String [] mydata;
        ArrayList<String> array = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        Cursor cr = db.rawQuery(sql, null);
        cr.moveToFirst();
        mydata = new String[cr.getCount()];
        int i = 0;
        do {
            mydata[i] = cr.getString(1);
            i ++;
        }while (cr.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.requireActivity(),
                android.R.layout.simple_dropdown_item_1line, mydata);
        foodlist1.setAdapter(adapter);
        foodlist2.setAdapter(adapter);
        foodlist3.setAdapter(adapter);
        foodlist4.setAdapter(adapter);
    }


}
