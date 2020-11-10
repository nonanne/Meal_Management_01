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
    private TextView calorieSum1;
    private TextView calorieSum2;
    private TextView calorieSum3;
    private TextView calorieSum4;
    private TextView protainSum1;
    private TextView protainSum2;
    private TextView protainSum3;
    private TextView protainSum4;
    private TextView carbonSum1;
    private TextView carbonSum2;
    private TextView carbonSum3;
    private TextView carbonSum4;
    private TextView fatSum1;
    private TextView fatSum2;
    private TextView fatSum3;
    private TextView fatSum4;
    private EditText foodGramForm1;
    private EditText foodGramForm2;
    private EditText foodGramForm3;
    private EditText foodGramForm4;
    private TextView calorieTotalForm;
    private TextView protainTotalForm;
    private TextView carbonTotalForm;
    private TextView fatTotalForm;

    private double foodgram = 0.0d;
    private double calorie = 0.0d;
    private double protain = 0.0d;
    private double carbon = 0.0d;
    private double fat = 0.0d;

    private double msg1_0;
    private double msg1_1;
    private double msg1_2;
    private double msg1_3;
    private double msg2_0;
    private double msg2_1;
    private double msg2_2;
    private double msg2_3;
    private double msg3_0;
    private double msg3_1;
    private double msg3_2;
    private double msg3_3;
    private double msg4_0;
    private double msg4_1;
    private double msg4_2;
    private double msg4_3;


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
        calorieSum1 = view.findViewById(R.id.calorieForm1_1);
        calorieSum2 = view.findViewById(R.id.calorieForm1_2);
        calorieSum3 = view.findViewById(R.id.calorieForm1_3);
        calorieSum4 = view.findViewById(R.id.calorieForm1_4);
        protainSum1 = view.findViewById(R.id.proteinForm1_1);
        protainSum2 = view.findViewById(R.id.proteinForm1_2);
        protainSum3 = view.findViewById(R.id.proteinForm1_3);
        protainSum4 = view.findViewById(R.id.proteinForm1_4);
        carbonSum1 = view.findViewById(R.id.carbonForm1_1);
        carbonSum2 = view.findViewById(R.id.carbonForm1_2);
        carbonSum3 = view.findViewById(R.id.carbonForm1_3);
        carbonSum4 = view.findViewById(R.id.carbonForm1_4);
        fatSum1 = view.findViewById(R.id.fatForm1_1);
        fatSum2 = view.findViewById(R.id.fatForm1_2);
        fatSum3 = view.findViewById(R.id.fatForm1_3);
        fatSum4 = view.findViewById(R.id.fatForm1_4);
        foodGramForm1 = view.findViewById(R.id.foodGramForm1_1);
        foodGramForm2 = view.findViewById(R.id.foodGramForm1_2);
        foodGramForm3 = view.findViewById(R.id.foodGramForm1_3);
        foodGramForm4 = view.findViewById(R.id.foodGramForm1_4);
        calorieTotalForm = view.findViewById(R.id.calorieSumForm1);
        protainTotalForm = view.findViewById(R.id.proteinSumForm1);
        carbonTotalForm = view.findViewById(R.id.carbonSumForm1);
        fatTotalForm = view.findViewById(R.id.fatSumForm1);

        //データベースの取り込み
        mydb = new MySQLiteOpenHelper(requireActivity());
        db = mydb.getReadableDatabase();

        String sql = "SELECT * FROM Products";
        Cursor cr = db.rawQuery(sql, null);
        cr.moveToFirst();
        final String [] mydata = new String[cr.getCount()];
        int i = 0;
        while (cr.moveToNext()) {
            mydata[i] = cr.getString(1);
            i ++;
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
                String name = foodlist1.getText().toString();
                Cursor cr = db.query(
                        "Products",
                        new String[] {
                                "id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"
                        },
                        "foodName = ?",
                        new String[]{ name },
                        null, null, null
                );
                boolean exist = cr.moveToNext();
                if (exist){
                    foodgram = cr.getDouble(2);
                    calorie = cr.getDouble(3);
                    protain = cr.getDouble(4);
                    carbon = cr.getDouble(5);
                    fat = cr.getDouble(6);
                } else {
                    foodgram = 0;
                    calorie = 0;
                    protain = 0;
                    carbon = 0;
                    fat = 0;
                }

                // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                msg1_0 = calorie * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                msg1_1 = protain * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                msg1_2 = carbon * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                msg1_3 = fat * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                calorieSum1.setText(String.valueOf(msg1_0));
                protainSum1.setText(String.valueOf(msg1_1));
                carbonSum1.setText(String.valueOf(msg1_2));
                fatSum1.setText(String.valueOf(msg1_3));

                EiyouTotal();

            }
        });

        Button foodButton2 = view.findViewById(R.id.foodButton1_2);
        //ボタンがクリックされた時の処理
        foodButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = foodlist2.getText().toString();
                Cursor cr = db.query(
                        "Products",
                        new String[] {
                                "id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"
                        },
                        "foodName = ?",
                        new String[]{ name },
                        null, null, null
                );
                boolean exist = cr.moveToNext();
                if (exist){
                    foodgram = cr.getDouble(2);
                    calorie = cr.getDouble(3);
                    protain = cr.getDouble(4);
                    carbon = cr.getDouble(5);
                    fat = cr.getDouble(6);
                } else {
                    foodgram = 0;
                    calorie = 0;
                    protain = 0;
                    carbon = 0;
                    fat = 0;
                }

                // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                msg2_0 = calorie * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                msg2_1 = protain * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                msg2_2 = carbon * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                msg2_3 = fat * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                calorieSum2.setText(String.valueOf(msg2_0));
                protainSum2.setText(String.valueOf(msg2_1));
                carbonSum2.setText(String.valueOf(msg2_2));
                fatSum2.setText(String.valueOf(msg2_3));

                EiyouTotal();
            }
        });

        Button foodButton3 = view.findViewById(R.id.foodButton1_3);
        //ボタンがクリックされた時の処理
        foodButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = foodlist3.getText().toString();
                Cursor cr = db.query(
                        "Products",
                        new String[] {
                                "id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"
                        },
                        "foodName = ?",
                        new String[]{ name },
                        null, null, null
                );
                boolean exist = cr.moveToNext();
                if (exist){
                    foodgram = cr.getDouble(2);
                    calorie = cr.getDouble(3);
                    protain = cr.getDouble(4);
                    carbon = cr.getDouble(5);
                    fat = cr.getDouble(6);
                } else {
                    foodgram = 0;
                    calorie = 0;
                    protain = 0;
                    carbon = 0;
                    fat = 0;
                }

                // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                msg3_0 = calorie * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                msg3_1 = protain * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                msg3_2 = carbon * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                msg3_3 = fat * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                calorieSum3.setText(String.valueOf(msg3_0));
                protainSum3.setText(String.valueOf(msg3_1));
                carbonSum3.setText(String.valueOf(msg3_2));
                fatSum3.setText(String.valueOf(msg3_3));

                EiyouTotal();
            }
        });

        Button foodButton4 = view.findViewById(R.id.foodButton1_4);
        //ボタンがクリックされた時の処理
        foodButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = foodlist4.getText().toString();
                Cursor cr = db.query(
                        "Products",
                        new String[] {
                                "id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"
                        },
                        "foodName = ?",
                        new String[]{ name },
                        null, null, null
                );
                boolean exist = cr.moveToNext();
                if (exist){
                    foodgram = cr.getDouble(2);
                    calorie = cr.getDouble(3);
                    protain = cr.getDouble(4);
                    carbon = cr.getDouble(5);
                    fat = cr.getDouble(6);
                } else {
                    foodgram = 0;
                    calorie = 0;
                    protain = 0;
                    carbon = 0;
                    fat = 0;
                }
                // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                msg4_0 = calorie * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                msg4_1 = protain * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                msg4_2 = carbon * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                msg4_3 = fat * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                calorieSum4.setText(String.valueOf(msg4_0));
                protainSum4.setText(String.valueOf(msg4_1));
                carbonSum4.setText(String.valueOf(msg4_2));
                fatSum4.setText(String.valueOf(msg4_3));

                EiyouTotal();
            }
        });


    }

    public void EiyouTotal (){
        String msg0Total = String.valueOf(msg1_0 + msg2_0 + msg3_0 + msg4_0);
        calorieTotalForm.setText(msg0Total);
        String msg1Total = String.valueOf(msg1_1 + msg2_1 + msg3_1 + msg4_1);
        protainTotalForm.setText(msg1Total);
        String msg2Total = String.valueOf(msg1_2 + msg2_2 + msg3_2 + msg4_2);
        carbonTotalForm.setText(msg2Total);
        String msg3Total = String.valueOf(msg1_3 + msg2_3 + msg3_3 + msg4_3);
        fatTotalForm.setText(msg3Total);
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
