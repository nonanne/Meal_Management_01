package com.example.eiyoukun;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import java.lang.Math;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class MainActivity extends Activity {

    private TextView useridForm;
    private TextView ageForm;
    private TextView weightForm;
    private TextView heightForm;
    private TextView sexForm;
    private TextView activityLevelForm;
    private TextView purposeForm;
    private TextView calorieForm;
    private TextView proteinForm;
    private TextView carbonForm;
    private TextView fatForm;

    private static int REQUEST_CODE = 1000;
    private SharedPreferences accountInf;
    private static final String SHARED_PREF_NAME = "accountInf";
    public static final String KEY_USERID = "userid";
    public static final String KEY_AGE = "age";
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
        //activity_mainのレイアウトをContentViewに設定
        setContentView(R.layout.activity_main);

        useridForm = findViewById(R.id.useridForm);
        ageForm = findViewById(R.id.ageForm);
        weightForm = findViewById(R.id.weightForm);
        heightForm = findViewById(R.id.heightForm);
        sexForm = findViewById(R.id.sexForm);
        activityLevelForm = findViewById(R.id.activityLevelForm);
        purposeForm = findViewById(R.id.purposeForm);

        calorieForm = this.findViewById(R.id.calorieForm);
        proteinForm = this.findViewById(R.id.proteinForm);
        carbonForm = this.findViewById(R.id.carbonForm);
        fatForm = this.findViewById(R.id.fatForm);

        //activity_main内のtoSub1Buttonを取得
        Button gosubButton = findViewById(R.id.toSyokuziButton);
        //ボタンがクリックされた時の処理を追加
        gosubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                //Intentを利用して他のアクティビティに遷移する
                Intent i = new Intent(MainActivity.this, SubActivity1.class);
                startActivity(i);
            }
        });

        // activity_main内のregistButtonを取得
        Button goRegistButton = findViewById(R.id.regist);
        //ボタンがクリックされた時の処理を追加
        goRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                //Intentを利用して他のアクティビティに遷移する
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        loadData();
        setData();
        try {
            calNutrition();
        } catch (Exception e){

        }
    }



        public void loadData() {
            accountInf = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
            strUserid = accountInf.getString(KEY_USERID, "");
            strAge = accountInf.getString(KEY_AGE, "");
            strWeight = accountInf.getString(KEY_WEIGHT, "");
            strHeight = accountInf.getString(KEY_HEIGHT, "string");
            strSex = accountInf.getString(KEY_SEX, "string");
            strActivityLevel = accountInf.getString(KEY_ACTIVITYLEVEL, "string");
            strPurpose = accountInf.getString(KEY_PURPOSE, "string");
        }

        public void setData() {
            useridForm.setText(strUserid);
            ageForm.setText(strAge);
            weightForm.setText(strWeight);
            heightForm.setText(strHeight);
            sexForm.setText(strSex);
            activityLevelForm.setText(strActivityLevel);
            purposeForm.setText(strPurpose);
        }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        useridForm = findViewById(R.id.useridForm);
        ageForm = findViewById(R.id.ageForm);
        weightForm = findViewById(R.id.weightForm);
        heightForm = findViewById(R.id.heightForm);
        sexForm = findViewById(R.id.sexForm);
        activityLevelForm = findViewById(R.id.activityLevelForm);
        purposeForm = findViewById(R.id.purposeForm);

        calorieForm = this.findViewById(R.id.calorieForm);
        proteinForm = this.findViewById(R.id.proteinForm);
        carbonForm = this.findViewById(R.id.carbonForm);
        fatForm = this.findViewById(R.id.fatForm);

        loadData();
        setData();
        try {
            calNutrition();
        } catch (Exception e){
        }
    }


public void calNutrition() {

    double protein, carbon, fat, age, weight, height;
    age = Double.parseDouble(strAge);
    weight = Double.parseDouble(strWeight);
    height = Double.parseDouble(strHeight);


    // case文で処理したい
    double calorie = 0;
            if (strSex.equals("男性") && strActivityLevel.equals("ほぼ運動しない")) {
                calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.2; //  String msg0　時に四捨五入
            } else if (strSex.equals("男性") && strActivityLevel.equals("軽い運動をしている")) {
                calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.375;
            } else if (strSex.equals("男性") && strActivityLevel.equals("中程度の運動をしている")) {
                calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.55;
            } else if (strSex.equals("男性") && strActivityLevel.equals("激しい運動をしている")) {
                calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.725;
            } else if (strSex.equals("男性") && strActivityLevel.equals("非常に激しい運動をしている")) {
                calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.9;
            } else if (strSex.equals("女性") && strActivityLevel.equals("ほぼ運動しない")) {
                calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.2) * 10) / 10.0; // この時点で四捨五入
            } else if (strSex.equals("女性") && strActivityLevel.equals("軽い運動をしている")) {
                calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.375) * 10) / 10.0;
            } else if (strSex.equals("女性") && strActivityLevel.equals("中程度の運動をしている")) {
                calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.55) * 10) / 10.0;
            } else if (strSex.equals("女性") && strActivityLevel.equals("激しい運動をしている")) {
                calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.725) * 10) / 10.0;
            } else if (strSex.equals("女性") && strActivityLevel.equals("非常に激しい運動をしている")) {
                calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.9) * 10) / 10.0;
            }

    protein = weight * 2.3;
    carbon = weight * 2.65;
    fat = weight * 0.9;

    String msg0 = String.format("%.1fcal", calorie); // 四捨五入
    String msg1 = protein + "g";
    String msg2 = carbon + "g";
    String msg3 = fat + "g";


    calorieForm.setText(msg0);
    proteinForm.setText(msg1);
    carbonForm.setText(msg2);
    fatForm.setText(msg3);
 }

}








