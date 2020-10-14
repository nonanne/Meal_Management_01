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

public class RegistActivity extends Activity {

    private EditText useridForm;
    private EditText ageForm;
    private EditText weightForm;
    private EditText heightForm;
    private Spinner spinnerSex;
    private Spinner activityLevelForm;
    private Spinner spinnerPurpose;

    private static int REQUEST_CODE = 1000;
    private SharedPreferences accountInf;
    private static final String SHARED_PREF_NAME = "accountInf";
    private static final String KEY_USERID = "userid";
    private static final String KEY_AGE = "age";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_SEX = "sex";
    private static final String KEY_ACTIVITYLEVEL = "activityLevel";
    private static final String KEY_PURPOSE = "purpose";

    private String strUserid;
    private String strAge;
    private String strWeight;
    private String strHeight;
    private String strSex;
    private String strActivityLevel;
    private String strPurpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //activity_regist.xmlに作成した内容を画面に表示
        setContentView(R.layout.activity_regist);

         useridForm = findViewById(R.id.useridForm);
         ageForm = findViewById(R.id.ageForm);
         weightForm = findViewById(R.id.weightForm);
        heightForm = findViewById(R.id.heightForm);
        spinnerSex = findViewById(R.id.spinnerSex);
        activityLevelForm = findViewById(R.id.activityLevelForm);
        spinnerPurpose = findViewById(R.id.spinnerPurpose);


        // fragment_first画面へ遷移するボタン
        Button returnButton = findViewById(R.id.registButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

//Intentクラスのオブジェクトを生成
                int id = view2.getId();
                Intent intent = new Intent();
                saveData();
                setResult(RESULT_OK, intent);

                finish();
            }
        });


        // fragment_first画面へ遷移するボタン
         Button return1Button = findViewById(R.id.registStopButton);
        return1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view2){

                //Intentクラスのオブジェクトを生成
                Intent intent = new Intent();
                int id = view2.getId();

                RegistActivity.this.finish();
            }
         });
    }


    public void saveData() {
        accountInf = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = accountInf.edit();
        editor.putString(KEY_USERID,useridForm.getText().toString());
        editor.putString(KEY_AGE,ageForm.getText().toString());
        editor.putString(KEY_WEIGHT,weightForm.getText().toString());
        editor.putString(KEY_HEIGHT,heightForm.getText().toString());
        editor.putString(KEY_SEX,spinnerSex.getSelectedItem().toString());
        editor.putString(KEY_ACTIVITYLEVEL,activityLevelForm.getSelectedItem().toString());
        editor.putString(KEY_PURPOSE,spinnerPurpose.getSelectedItem().toString());
        editor.apply();
    }



}

