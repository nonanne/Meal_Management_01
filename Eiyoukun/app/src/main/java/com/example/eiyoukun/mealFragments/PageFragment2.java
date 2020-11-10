package com.example.eiyoukun.mealFragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eiyoukun.MySQLiteOpenHelper;
import com.example.eiyoukun.Product;
import com.example.eiyoukun.R;

import java.util.ArrayList;

public class PageFragment2 extends Fragment {

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
                .inflate(R.layout.page_food2,cotainer
                        , false);


        return rootView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodlist1 = (AutoCompleteTextView)view.findViewById(R.id.foodForm2_1);
        foodlist2 = (AutoCompleteTextView)view.findViewById(R.id.foodForm2_2);
        foodlist3= (AutoCompleteTextView)view.findViewById(R.id.foodForm2_3);
        foodlist4= (AutoCompleteTextView)view.findViewById(R.id.foodForm2_4);
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

