package com.example.eiyoukun;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import java.lang.Math;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class FoodAddActivity extends AppCompatActivity {
    private MySQLiteOpenHelper helper; //MySQLiteOpenHelperクラス
    private EditText addFoodEdit;
    private EditText foodGram;
    private EditText addCalorie;
    private EditText addProtain;
    private EditText addCarbon;
    private EditText addFat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_foodadd.xmlに作成した内容を画面に表示
        setContentView(R.layout.activity_foodadd);

        helper = new MySQLiteOpenHelper(this);
        addFoodEdit = (EditText) findViewById(R.id.addFoodEdit);
        foodGram = (EditText) findViewById(R.id.foodGram);
        addCalorie = (EditText) findViewById(R.id.addCalorie);
        addProtain = (EditText) findViewById(R.id.addProtain);
        addCarbon = (EditText) findViewById(R.id.addCarbon);
        addFat = (EditText) findViewById(R.id.addFat);

        // activity_food画面へ遷移するボタン
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                Intent intent = new Intent();

                intent.putExtra("CANCELED_MESSAGE", "登録をキャンセルしました");
                setResult(RESULT_CANCELED, intent);

                FoodAddActivity.this.finish();
            }
        });

        // activity_food画面へ遷移するボタン
        Button foodAddButton = findViewById(R.id.foodAddButton);
        foodAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                Intent intent = new Intent();

                if (registRecord()) { //登録成功
                    intent.putExtra("SUCCESS_MESSAGE", "登録完了しました");
                    setResult(RESULT_OK, intent);
                }else {
                    intent.putExtra("CANCELED_MESSAGE", "登録に失敗しました");
                    setResult(RESULT_CANCELED, intent);
                }

                intent.putExtra("CANCELED_MESSAGE", "登録をキャンセルしました");
                setResult(RESULT_CANCELED, intent);

                FoodAddActivity.this.finish();
            }
        });
    }


private boolean registRecord() {

        SQLiteDatabase db = helper.getWritableDatabase();
        String foodName = addFoodEdit.getText().toString();
        int foodgram = Integer.parseInt(foodGram.getText().toString());
        double calorie = Double.parseDouble(addCalorie.getText().toString());
        double protain = Double.parseDouble(addProtain.getText().toString());
        double carbon = Double.parseDouble(addCarbon.getText().toString());
        double fat = Double.parseDouble(addFat.getText().toString());

        ContentValues value = new ContentValues();
        value.put("foodName", foodName);
        value.put("foodGram", foodgram);
        value.put("calorie", calorie);
        value.put("protain", protain);
        value.put("carbon", carbon);
        value.put("fat", fat);

        boolean judge = db.insert("Products", null, value) != -1 ? true : false;
        db.close();
        return judge;
}

}