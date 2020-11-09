package com.example.eiyoukun;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import java.lang.Math;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.widget.ListView;

public class FoodActivity extends AppCompatActivity {
    private static final int FOODADD_REQUEST_CODE = 1;
    private MySQLiteOpenHelper helper;
    private ListView listView;
    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new MySQLiteOpenHelper(this);

        //activity_food.xmlに作成した内容を画面に表示
        setContentView(R.layout.activity_food);

        // fragment_second画面へ遷移するボタン
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                FoodActivity.this.finish();
            }
        });

        // activity_food内のfoodAddButtonを取得
        Button gofoodAddButton = findViewById(R.id.foodAddButton);
        //ボタンがクリックされた時の処理を追加
        gofoodAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intentを利用して他のアクティビティに遷移する
                Intent intent = new Intent(FoodActivity.this, FoodAddActivity.class);
                startActivity(intent);

            }
        });

        // activity_food内のfoodUpdateButtonを取得
        Button gofoodUpdateButton = findViewById(R.id.foodUpdateButton);

        //ボタンがクリックされた時の処理を追加
        gofoodUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedPosition = listView.getCheckedItemPosition();

                // 未選択 または 不正な選択状態になった場合
                if (checkedPosition < 0 || checkedPosition >= products.size()) {
                    return;
                }
                Product selected = products.get(checkedPosition);

                //Intentを利用して他のアクティビティに遷移する
                Intent intent = new Intent(FoodActivity.this, FoodUpdateActivity.class);
                intent.putExtra("PRODUCT", selected);
                startActivity(intent);
            }
        });

        // activity_food内のfoodDeleteButtonを取得
        Button gofoodDeleteButton = findViewById(R.id.foodDeleteButton);
        //ボタンがクリックされた時の処理を追加
        gofoodDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedPosition = listView.getCheckedItemPosition();

                // 未選択 または 不正な選択状態になった場合
                if (checkedPosition < 0 || checkedPosition >= products.size()) {
                    return;
                }
                Product selected = products.get(checkedPosition);
                //Intentを利用して他のアクティビティに遷移する
                Intent intent = new Intent(FoodActivity.this, FoodDeleteActivity.class);
                intent.putExtra("PRODUCT", selected);
                startActivity(intent);
            }
        });

        listView = (ListView)findViewById(R.id.listView1);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        products = new ArrayList<Product>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columns = {"id", "foodName", "foodgram", "calorie", "protain", "carbon", "fat"};

        Cursor cursor = db.query("Products", columns, null, null, null, null, null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0) ;
            String foodName = cursor.getString(1);
            double foodgram = cursor.getDouble(2);
            double calorie = cursor.getDouble(3);
            double protain = cursor.getDouble(4);
            double carbon = cursor.getDouble(5);
            double fat = cursor.getDouble(6);

            products.add(
                    new Product(id, foodName, foodgram, calorie, protain, carbon, fat)
            );
        }

        String[] items = new String[products.size()];

        for(int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            items[i] = product.getfoodName() + "：" + product.getfoodgram() + "\n" + "C:" + product.getCalorie() + "   p:" + product.getProtain()  +"   c:" +product.getCarbon() + "   f:" + product.getFat();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_single_choice, items);

        listView.setAdapter(adapter);

        listView.setItemChecked(0, true);

        db.close();
    }



}
