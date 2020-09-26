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

public class UpdateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // activity_main画面へ遷移するボタン
        Button returnButton = findViewById(R.id.updateButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                UpdateActivity.this.finish();
            }
        });

        // activity_main画面へ遷移するボタン
        Button return1Button = findViewById(R.id.updateStopButton);

        return1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                UpdateActivity.this.finish();
            }
        });



    }

}

