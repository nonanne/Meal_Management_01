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

public class FoodDeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_regist.xmlに作成した内容を画面に表示
        setContentView(R.layout.activity_fooddelete);

        // activity_main画面へ遷移するボタン
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                FoodDeleteActivity.this.finish();
            }
        });

    }

}
