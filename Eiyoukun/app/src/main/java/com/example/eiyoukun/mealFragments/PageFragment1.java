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
import java.util.List;

public class PageFragment1 extends Fragment {

    private AutoCompleteTextView foodlist1;
    private AutoCompleteTextView foodlist2;
    private AutoCompleteTextView foodlist3;
    private AutoCompleteTextView foodlist4;
    private TextView calorieForm1;
    private TextView calorieForm2;
    private TextView calorieForm3;
    private TextView calorieForm4;
    private TextView protainForm1;
    private TextView protainForm2;
    private TextView protainForm3;
    private TextView protainForm4;
    private TextView carbonForm1;
    private TextView carbonForm2;
    private TextView carbonForm3;
    private TextView carbonForm4;
    private TextView fatForm1;
    private TextView fatForm2;
    private TextView fatForm3;
    private TextView fatForm4;
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
    /*-------
    修正　ArrayList<Product> =>List<Product>
     Listの抽象クラスの方が利便性があるため
     */
    private List<Product> products;
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
        calorieForm1 = view.findViewById(R.id.calorieForm1_1);
        calorieForm2 = view.findViewById(R.id.calorieForm1_2);
        calorieForm3 = view.findViewById(R.id.calorieForm1_3);
        calorieForm4 = view.findViewById(R.id.calorieForm1_4);
        protainForm1 = view.findViewById(R.id.proteinForm1_1);
        protainForm2 = view.findViewById(R.id.proteinForm1_2);
        protainForm3 = view.findViewById(R.id.proteinForm1_3);
        protainForm4 = view.findViewById(R.id.proteinForm1_4);
        carbonForm1 = view.findViewById(R.id.carbonForm1_1);
        carbonForm2 = view.findViewById(R.id.carbonForm1_2);
        carbonForm3 = view.findViewById(R.id.carbonForm1_3);
        carbonForm4 = view.findViewById(R.id.carbonForm1_4);
        fatForm1 = view.findViewById(R.id.fatForm1_1);
        fatForm2 = view.findViewById(R.id.fatForm1_2);
        fatForm3 = view.findViewById(R.id.fatForm1_3);
        fatForm4 = view.findViewById(R.id.fatForm1_4);
        foodGramForm1 = view.findViewById(R.id.foodGramForm1_1);
        foodGramForm2 = view.findViewById(R.id.foodGramForm1_2);
        foodGramForm3 = view.findViewById(R.id.foodGramForm1_3);
        foodGramForm4 = view.findViewById(R.id.foodGramForm1_4);
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
        if(cr.moveToFirst()) {
            mydata = new String[cr.getCount()];
            int i = 0;
            do {
                mydata[i] = cr.getString(1);
                i++;
            } while (cr.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.requireActivity(),
                    android.R.layout.simple_dropdown_item_1line, mydata);
            foodlist1.setAdapter(adapter);
            foodlist2.setAdapter(adapter);
            foodlist3.setAdapter(adapter);
            foodlist4.setAdapter(adapter);
        }


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

                if (foodlist1.length() == 0 || foodGramForm1.length() == 0) {
                    foodgram = 0;
                    calorie = 0;
                    protain = 0;
                    carbon = 0;
                    fat = 0;
                } else {
                    // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                    msg1_0 = calorie * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                    msg1_1 = protain * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                    msg1_2 = carbon * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                    msg1_3 = fat * Double.parseDouble(foodGramForm1.getText().toString()) / foodgram;
                    calorieForm1.setText(String.valueOf(msg1_0) + "cal");
                    protainForm1.setText(String.valueOf(msg1_1) + "g");
                    carbonForm1.setText(String.valueOf(msg1_2) + "g");
                    fatForm1.setText(String.valueOf(msg1_3) + "g");
                }

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

                if (foodlist2.length() == 0 || foodGramForm2.length() == 0) {
                    foodgram = 0;
                    calorie = 0;
                    protain = 0;
                    carbon = 0;
                    fat = 0;
                } else {
                    // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                    msg2_0 = calorie * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                    msg2_1 = protain * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                    msg2_2 = carbon * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                    msg2_3 = fat * Double.parseDouble(foodGramForm2.getText().toString()) / foodgram;
                    calorieForm2.setText(String.valueOf(msg2_0) + "cal");
                    protainForm2.setText(String.valueOf(msg2_1) + "g");
                    carbonForm2.setText(String.valueOf(msg2_2) + "g");
                    fatForm2.setText(String.valueOf(msg2_3) + "g");
                }

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
                        new String[]{
                                "id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"
                        },
                        "foodName = ?",
                        new String[]{name},
                        null, null, null
                );
                boolean exist = cr.moveToNext();
                if (exist) {
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

                if (foodlist3.length() == 0 || foodGramForm3.length() == 0) {
                    foodgram = 0;
                    calorie = 0;
                    protain = 0;
                    carbon = 0;
                    fat = 0;
                } else {
// * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                    msg3_0 = calorie * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                    msg3_1 = protain * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                    msg3_2 = carbon * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                    msg3_3 = fat * Double.parseDouble(foodGramForm3.getText().toString()) / foodgram;
                    calorieForm3.setText(String.valueOf(msg3_0) + "cal");
                    protainForm3.setText(String.valueOf(msg3_1) + "g");
                    carbonForm3.setText(String.valueOf(msg3_2) + "g");
                    fatForm3.setText(String.valueOf(msg3_3) + "g");
                }


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

                if (foodlist4.length() == 0 || foodGramForm4.length() == 0) {
                    foodgram = 0;
                    calorie = 0;
                    protain = 0;
                    carbon = 0;
                    fat = 0;
                } else {
                    // * ★double -> String の変換を記述。一番手短な方法のためにコードは正直よくないです。
                    msg4_0 = calorie * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                    msg4_1 = protain * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                    msg4_2 = carbon * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                    msg4_3 = fat * Double.parseDouble(foodGramForm4.getText().toString()) / foodgram;
                    calorieForm4.setText(String.valueOf(msg4_0) + "cal");
                    protainForm4.setText(String.valueOf(msg4_1) + "g");
                    carbonForm4.setText(String.valueOf(msg4_2) + "g");
                    fatForm4.setText(String.valueOf(msg4_3) + "g");
                }

                EiyouTotal();


            }
        });
    }


    public void EiyouTotal (){

        String msg1_0Total = String.valueOf(msg1_0 + msg2_0 + msg3_0 + msg4_0);
        calorieTotalForm.setText(msg1_0Total + "cal");
        String msg1_1Total = String.valueOf(msg1_1 + msg2_1 + msg3_1 + msg4_1);
        protainTotalForm.setText(msg1_1Total + "g");
        String msg1_2Total = String.valueOf(msg1_2 + msg2_2 + msg3_2 + msg4_2);
        carbonTotalForm.setText(msg1_2Total + "g");
        String msg1_3Total = String.valueOf(msg1_3 + msg2_3 + msg3_3 + msg4_3);
        fatTotalForm.setText(msg1_3Total + "g");
        Bundle result0 = new Bundle();
        result0.putString("totalCalorie1", msg1_0Total);
        result0.putString("totalProtein1", msg1_1Total);
        result0.putString("totalCarbon1", msg1_2Total);
        result0.putString("totalFat1", msg1_3Total);
        getParentFragmentManager().setFragmentResult("requestKey1_0", result0);

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
        if(cr.moveToFirst()) {
            mydata = new String[cr.getCount()];
            int i = 0;
            do {
                mydata[i] = cr.getString(1);
                i++;
            } while (cr.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.requireActivity(),
                android.R.layout.simple_dropdown_item_1line, mydata);
        foodlist1.setAdapter(adapter);
        foodlist2.setAdapter(adapter);
        foodlist3.setAdapter(adapter);
        foodlist4.setAdapter(adapter);
        }
    }


}
