package com.example.eiyoukun.mealFragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eiyoukun.MySQLiteOpenHelper;
import com.example.eiyoukun.Product;
import com.example.eiyoukun.R;

import java.util.ArrayList;

public class PageFragment4 extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup cotainer,
                             @Nullable Bundle saveInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.page_food4,cotainer
                        , false);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodlist1 = (AutoCompleteTextView)view.findViewById(R.id.foodForm4_1);
        foodlist2 = (AutoCompleteTextView)view.findViewById(R.id.foodForm4_2);
        foodlist3= (AutoCompleteTextView)view.findViewById(R.id.foodForm4_3);
        foodlist4= (AutoCompleteTextView)view.findViewById(R.id.foodForm4_4);
        calorieSum1 = view.findViewById(R.id.calorieForm4_1);
        calorieSum2 = view.findViewById(R.id.calorieForm4_2);
        calorieSum3 = view.findViewById(R.id.calorieForm4_3);
        calorieSum4 = view.findViewById(R.id.calorieForm4_4);
        protainSum1 = view.findViewById(R.id.proteinForm4_1);
        protainSum2 = view.findViewById(R.id.proteinForm4_2);
        protainSum3 = view.findViewById(R.id.proteinForm4_3);
        protainSum4 = view.findViewById(R.id.proteinForm4_4);
        carbonSum1 = view.findViewById(R.id.carbonForm4_1);
        carbonSum2 = view.findViewById(R.id.carbonForm4_2);
        carbonSum3 = view.findViewById(R.id.carbonForm4_3);
        carbonSum4 = view.findViewById(R.id.carbonForm4_4);
        fatSum1 = view.findViewById(R.id.fatForm4_1);
        fatSum2 = view.findViewById(R.id.fatForm4_2);
        fatSum3 = view.findViewById(R.id.fatForm4_3);
        fatSum4 = view.findViewById(R.id.fatForm4_4);
        foodGramForm1 = view.findViewById(R.id.foodGramForm4_1);
        foodGramForm2 = view.findViewById(R.id.foodGramForm4_2);
        foodGramForm3 = view.findViewById(R.id.foodGramForm4_3);
        foodGramForm4 = view.findViewById(R.id.foodGramForm4_4);
        calorieTotalForm = view.findViewById(R.id.calorieSumForm1);
        protainTotalForm = view.findViewById(R.id.proteinSumForm1);
        carbonTotalForm = view.findViewById(R.id.carbonSumForm1);
        fatTotalForm = view.findViewById(R.id.fatSumForm1);

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

        String msg4_0Total = String.valueOf(msg1_0 + msg2_0 + msg3_0 + msg4_0);
        calorieTotalForm.setText(msg4_0Total);
        String msg4_1Total = String.valueOf(msg1_1 + msg2_1 + msg3_1 + msg4_1);
        protainTotalForm.setText(msg4_1Total);
        String msg4_2Total = String.valueOf(msg1_2 + msg2_2 + msg3_2 + msg4_2);
        carbonTotalForm.setText(msg4_2Total);
        String msg4_3Total = String.valueOf(msg1_3 + msg2_3 + msg3_3 + msg4_3);
        fatTotalForm.setText(msg4_3Total);
        Bundle result0 = new Bundle();
        result0.putString("totalCalorie4", msg4_0Total);
        result0.putString("totalProtein4", msg4_1Total);
        result0.putString("totalCarbon4", msg4_2Total);
        result0.putString("totalFat4", msg4_3Total);
        getParentFragmentManager().setFragmentResult("requestKey4_0", result0);

/*　今後上記のコードと比較したいからわざと残しています
        Bundle result0 = new Bundle();
        result0.putString("bundleKey1_0", msg1_0Total);
        getParentFragmentManager().setFragmentResult("requestKey1_0", result0);
        Bundle result1 = new Bundle();
        result1.putString("bundleKey1_1", msg1_1Total);
        getParentFragmentManager().setFragmentResult("requestKey1_1", result1);
        Bundle result2 = new Bundle();
        result2.putString("bundleKey1_2", msg1_2Total);
        getParentFragmentManager().setFragmentResult("requestKey1_2", result2);
        Bundle result3 = new Bundle();
        result3.putString("bundleKey1_3", msg1_3Total);
        getParentFragmentManager().setFragmentResult("requestKey1_3", result3);
 */
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