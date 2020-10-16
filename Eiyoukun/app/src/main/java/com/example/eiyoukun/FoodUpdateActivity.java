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

public class FoodUpdateActivity extends AppCompatActivity {

    private MySQLiteOpenHelper helper; //MySQLiteOpenHelperクラス
    private Product product;
    private EditText updateFoodEdit;
    private EditText updatefoodGram;
    private EditText updateCalorie;
    private EditText updateProtain;
    private EditText updateCarbon;
    private EditText updateFat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_regist.xmlに作成した内容を画面に表示
        setContentView(R.layout.activity_foodupdate);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        product = (Product) bundle.getSerializable("PRODUCT");
        helper = new MySQLiteOpenHelper(this);
        updateFoodEdit = (EditText) findViewById(R.id.updateFoodEdit);
        updateFoodEdit.setText(product.getfoodName());
        updatefoodGram = (EditText) findViewById(R.id.foodGram);
        updatefoodGram.setText(Double.toString(product.getfoodgram()));
        updateCalorie = (EditText) findViewById(R.id.updateCalorie);
        updateCalorie.setText(Double.toString(product.getCalorie()));
        updateProtain = (EditText) findViewById(R.id.updateProtain);
        updateProtain.setText(Double.toString(product.getProtain()));
        updateCarbon = (EditText) findViewById(R.id.updateCarbon);
        updateCarbon.setText(Double.toString(product.getCarbon()));
        updateFat = (EditText) findViewById(R.id.updateFat);
        updateFat.setText(Double.toString(product.getFat()));

        // activity_main画面へ遷移するボタン
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent intent = new Intent();
                intent.putExtra("CANCELED_MESSAGE", "変更をキャンセルしました");
                setResult(RESULT_CANCELED, intent);

                FoodUpdateActivity.this.finish();
            }
        });

        // activity_main画面へ遷移するボタン
        Button foodUpdateButton = findViewById(R.id.foodUpdatebutton);
        foodUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent intent = new Intent();
                if (updateRecord()) {
                    intent.putExtra("SUCCESS_MESSAGE", "変更しました");
                    setResult(RESULT_OK, intent);
                } else {
                    intent.putExtra("CANCELED_MESSAGE", "変更に失敗しました");
                    setResult(RESULT_CANCELED, intent);
                }

                FoodUpdateActivity.this.finish();
            }
        });

    }

    private boolean updateRecord() {

        SQLiteDatabase db = helper.getWritableDatabase();
        int id = product.getId();

        String foodName = updateFoodEdit.getText().toString();
        double foodgram = Double.parseDouble(updatefoodGram.getText().toString());
        double calorie = Double.parseDouble(updateCalorie.getText().toString());
        double protain = Double.parseDouble(updateProtain.getText().toString());
        double carbon = Double.parseDouble(updateCarbon.getText().toString());
        double fat = Double.parseDouble(updateFat.getText().toString());

        ContentValues value = new ContentValues();
        value.put("foodName", foodName);
        value.put("foodgram", foodgram);
        value.put("calorie", calorie);
        value.put("protain", protain);
        value.put("carbon", carbon);
        value.put("fat", fat);

        String condition = "id=" + id;
        boolean judge = db.update("Products", value, condition, null)!= -1 ? true : false;
        db.close();
        return judge;
    }

}
