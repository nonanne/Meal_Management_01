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

public class FoodRegistActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_regist.xmlに作成した内容を画面に表示
        setContentView(R.layout.activity_foodregist);



        // タブナビゲーションモードに設定
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // タブを作成して追加。タブの選択・解除・再選択をハンドリングするコールバックの TabListener をセットしないと実行時例外でクラッシュする
        getActionBar().addTab(getSupportActionBar().newTab().setText("Tab1").setTabListener(this));
        getActionBar().addTab(getSupportActionBar().newTab().setText("Tab2").setTabListener(this));
        getActionBar().addTab(getSupportActionBar().newTab().setText("Tab3").setTabListener(this));

        // activity_main画面へ遷移するボタン
        Button returnButton = findViewById(R.id.goSub1Button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                FoodRegistActivity.this.finish();
            }
        });


    }

}
