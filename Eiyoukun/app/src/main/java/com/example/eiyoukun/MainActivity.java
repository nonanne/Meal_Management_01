package com.example.eiyoukun;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //activity_mainのレイアウトをContentViewに設定
        setContentView(R.layout.activity_main);
        //activity_main内のtoSub1Buttonを取得
        Button gosubButton = findViewById(R.id.toSyokuziButton);
        //ボタンがクリックされた時の処理を追加
        gosubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                //Intentを利用して他のアクティビティに遷移する
                Intent intent = new Intent(MainActivity.this, SubActivity1.class);
                startActivity(intent);
            }
        });
    }


    //　決定ボタン
    public void onClickButton(android.view.View view) {
        TextView calorieForm = this.findViewById(R.id.calorieForm);
        TextView proteinForm = this.findViewById(R.id.proteinForm);
        TextView carbonForm = this.findViewById(R.id.carbonForm);
        TextView fatForm = this.findViewById(R.id.fatForm);
        EditText ageForm = this.findViewById(R.id.ageForm);
        EditText weightForm = this.findViewById(R.id.weightForm);
        EditText heightForm = this.findViewById(R.id.heightForm);

        // Spinnerオブジェクトを取得
        Spinner spinner = findViewById(R.id.spinnerSex);
        Spinner spinner1 = findViewById(R.id.活動レベルForm);
        Spinner spinner2 = findViewById(R.id.spinnerPurpose);
        // 選択されているアイテムを取得
        String item = (String)spinner.getSelectedItem();
        String item1 = (String)spinner1.getSelectedItem();
        String item2 = (String)spinner2.getSelectedItem();


        String strAge;
        strAge = ageForm.getText().toString();
        String strWeight;
        strWeight = weightForm.getText().toString();
        String strHeight;
        strHeight = heightForm.getText().toString();

        double protein, carbon, fat, age, weight, height;
        age = Double.parseDouble(strAge);
        weight = Double.parseDouble(strWeight);
        height = Double.parseDouble(strHeight);

        // case文で処理したい
        double calorie = 0;
        if (item.equals("男性") && item1.equals("ほぼ運動しない")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.2; //  String msg0　時に四捨五入
        } else if (item.equals("男性") && item1.equals("軽い運動をしている")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.375;
        } else if (item.equals("男性") && item1.equals("中程度の運動をしている")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.55;
        } else if (item.equals("男性") && item1.equals("激しい運動をしている")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.725;
        }  else if (item.equals("男性") && item1.equals("非常に激しい運動をしている")) {
            calorie = (13.397 * weight + 4.799 * height - 5.677 * age + 88.362) * 1.9;
        }  else if (item.equals("女性") && item1.equals("ほぼ運動しない")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.2)*10)/10.0; // この時点で四捨五入
        }  else if (item.equals("女性") && item1.equals("軽い運動をしている")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.375)*10)/10.0;
        } else if (item.equals("女性") && item1.equals("中程度の運動をしている")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.55)*10)/10.0;
        } else if (item.equals("女性") && item1.equals("激しい運動をしている")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.725)*10)/10.0;
        }  else if (item.equals("女性") && item1.equals("非常に激しい運動をしている")) {
            calorie = (Math.round((9.247 * weight + 3.098 * height - 4.33 * age + 447.593) * 1.9)*10)/10.0;
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