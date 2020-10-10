package com.example.eiyoukun;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.text.format.Time;
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

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //activity_regist.xmlに作成した内容を画面に表示
        setContentView(R.layout.activity_food);

        // activity_main画面へ遷移するボタン
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                finish();
            }
        });

        // activity_main内のregistButtonを取得
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

        // activity_main内のregistButtonを取得
        Button gofoodUpdateButton = findViewById(R.id.foodUpdateButton);
        //ボタンがクリックされた時の処理を追加
        gofoodAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intentを利用して他のアクティビティに遷移する
                Intent intent = new Intent(FoodActivity.this, FoodUpdateActivity.class);
                startActivity(intent);
            }
        });

        // activity_main内のregistButtonを取得
        Button gofoodDeleteButton = findViewById(R.id.foodDeleteButton);
        //ボタンがクリックされた時の処理を追加
        gofoodAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intentを利用して他のアクティビティに遷移する
                Intent intent = new Intent(FoodActivity.this, FoodDeleteActivity.class);
                startActivity(intent);
            }
        });

    }

}
