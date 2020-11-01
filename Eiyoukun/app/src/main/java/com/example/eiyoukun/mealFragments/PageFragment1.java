package com.example.eiyoukun.mealFragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.view.MenuItem;
import android.view.Menu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.eiyoukun.MySQLiteOpenHelper;
import com.example.eiyoukun.R;

import static androidx.core.content.ContextCompat.getSystemService;

public class PageFragment1 extends Fragment {

    private AutoCompleteTextView foodlist1;
    private AutoCompleteTextView foodlist2;
    private AutoCompleteTextView foodlist3;
    private AutoCompleteTextView foodlist4;
    private MySQLiteOpenHelper mydb;
    private SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup cotainer,
                             @Nullable Bundle saveInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.page_food1,cotainer
                        , false);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodlist1 = (AutoCompleteTextView)view.findViewById(R.id.foodForm1_1);
        foodlist2 = (AutoCompleteTextView)view.findViewById(R.id.foodForm1_2);
        foodlist3= (AutoCompleteTextView)view.findViewById(R.id.foodForm1_3);
        foodlist4= (AutoCompleteTextView)view.findViewById(R.id.foodForm1_4);
        mydb = new MySQLiteOpenHelper(requireActivity());
        db = mydb.getReadableDatabase();
        final String [] mydata;
        ArrayList<String> array = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        Cursor cr = db.rawQuery(sql, null);
        cr.moveToFirst();
        mydata = new String[cr.getCount()];
        int i = 0;
        do {
            mydata[i] = cr.getString(1);
            i ++;
        }while (cr.moveToNext());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.requireActivity(),
                android.R.layout.simple_dropdown_item_1line, mydata);
        foodlist1.setAdapter(adapter);
        foodlist2.setAdapter(adapter);
        foodlist3.setAdapter(adapter);
        foodlist4.setAdapter(adapter);
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
     */

}
