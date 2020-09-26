package com.example.eiyoukun;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.text.format.Time;
import java.util.Calendar;

public class SubActivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);


        TextView dateText = findViewById(R.id.today);
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = (time.month+1) + "月" + time.monthDay + "日";
        dateText.setText(date);


        // activity_main画面へ遷移するボタン
        Button returnButton = findViewById(R.id.toKojinButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                SubActivity1.this.finish();
            }
        });

        // activity_main内のregistButtonを取得
        Button foodRegistButton = findViewById(R.id.foodRegistButton);
        //ボタンがクリックされた時の処理を追加
        foodRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                //Intentを利用して他のアクティビティに遷移する
                Intent intent = new Intent(SubActivity1.this, FoodRegistActivity.class);
                startActivity(intent);
            }
        });

    }
}