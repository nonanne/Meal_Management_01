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
    private MySQLiteOpenHelper helper; //MySQLiteOpenHelperクラス
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_regist.xmlに作成した内容を画面に表示
        setContentView(R.layout.activity_fooddelete);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        product = (Product)bundle.getSerializable("PRODUCT");

        helper = new MySQLiteOpenHelper(this);

        TextView deleteFood = (TextView)findViewById(R.id.deleteFood);
        deleteFood.setText(product.getFoodName());
        TextView foodGram = (TextView)findViewById(R.id.foodGram);
        foodGram.setText(Double.toString(product.getFoodgram()));
        TextView deleteCalorie = (TextView)findViewById(R.id.deleteCalorie);
        deleteCalorie.setText(Double.toString(product.getCalorie()));
        TextView deleteProtain = (TextView)findViewById(R.id.deleteProtain);
        deleteProtain.setText(Double.toString(product.getProtain()));
        TextView deleteCarbon = (TextView)findViewById(R.id.deleteCarbon);
        deleteCarbon.setText(Double.toString(product.getCarbon()));
        TextView deleteFat = (TextView)findViewById(R.id.deleteFat);
        deleteFat.setText(Double.toString(product.getFat()));

        // activity_main画面へ遷移するボタン
        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent intent = new Intent();
                intent.putExtra("CANSELED_MESSAGE", "削除をキャンセルしました");
                setResult(RESULT_CANCELED, intent);

                FoodDeleteActivity.this.finish();
            }
        });

        // activity_main画面へ遷移するボタン
        Button foodDeleteButton = findViewById(R.id.foodDeletebutton);
        foodDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Intent intent = new Intent();
                    if(deleteRecord()) {
                        intent.putExtra("SUCCESS_MESSAGE", "削除しました");
                        setResult(RESULT_OK, intent);
                    }else {
                        intent.putExtra("CANSELED_MESSAGE", "削除に失敗しました");
                        setResult(RESULT_CANCELED, intent);
                    }
                FoodDeleteActivity.this.finish();
            }
        });
    }

    private boolean deleteRecord(){
        SQLiteDatabase db = helper.getWritableDatabase();
        int id = product.getId();
        String condition = "id=" + id;
        boolean judge = db.delete("Products", condition, null) != -1 ? true : false;
        db.close();
        return judge;
    }

}
