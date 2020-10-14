package com.example.eiyoukun;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Sqlite";   // DB名
    private static int DB_VERSION = 1;           // DBのVersion
    // コンストラクタ
    // CREATE用のSQLを取得する
    public MySQLiteOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    // DBが存在しない状態でOpenすると、onCreateがコールされる
    // 新規作成されたDBのインスタンスが付与されるので、テーブルを作成する。
    @Override
    public void onCreate(SQLiteDatabase db) {
        //テーブルの作成用SQL文の設定
        String sql = "CREATE TABLE Products (";
        sql += "id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sql += "foodName TEXT NOT NULL,";
        sql += "calorie INTEGER NOT NULL,";
        sql += "protain INTEGER NOT NULL,";
        sql += "carbon INTEGER NOT NULL,";
        sql += "fat INTEGER NOT NULL);";

        //テーブル作成用SQL文を実行
        db.execSQL(sql) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


}
