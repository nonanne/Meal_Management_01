package com.example.eiyoukun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.widget.Button;
import android.view.View;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.SharedPreferences;
import android.text.format.Time;

import com.example.eiyoukun.mealFragments.PageFragment1;
import com.example.eiyoukun.mealFragments.PageFragment2;
import com.example.eiyoukun.mealFragments.PageFragment3;
import com.example.eiyoukun.mealFragments.PageFragment4;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link secondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class secondFragment extends Fragment {

    private TextView calorieGoal;
    private TextView proteinGoal;
    private TextView carbonGoal;
    private TextView fatGoal;
    private TextView kojin_purpose;


    private SharedPreferences EiyouInf;
    private static final String SHARED_PREF_NAME1 = "EiyouInf";
    private static final String KEY_CALORIE = "calorie";
    private static final String KEY_PROTAIN = "protain";
    private static final String KEY_CARBON = "carbon";
    private static final String KEY_FAT = "fat";
    private static final String KEY_PURPOSE2 = "purpose2";

    private String strcCalorie;
    private String strProtein;
    private String strCarbon;
    private String strFat;
    private String strPurpose2;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    private AutoCompleteTextView foodlist;
    private MySQLiteOpenHelper mydb;
    private SQLiteDatabase db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public secondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment secondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static secondFragment newInstance(String param1, String param2) {
        secondFragment fragment = new secondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView dateText = view.findViewById(R.id.today);
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        String date = (time.month + 1) + "月" + time.monthDay + "日";
        dateText.setText(date);

        calorieGoal = view.findViewById(R.id.calorieGoal);
        proteinGoal = view.findViewById(R.id.proteinGoal);
        carbonGoal = view.findViewById(R.id.carbonGoal);
        fatGoal = view.findViewById(R.id.fatGoal);
        kojin_purpose = view.findViewById(R.id.kojin_purpose);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment1());
        list.add(new PageFragment2());
        list.add(new PageFragment3());
        list.add(new PageFragment4());

        pager = view.findViewById(R.id.pager);
        pagerAdapter = new SidePagerAdapter(getChildFragmentManager(), list) {
        };
        pager.setAdapter(pagerAdapter);

        /*
       foodlist = (AutoCompleteTextView)view.findViewById(R.id.foodForm1_1);
       mydb = new MySQLiteOpenHelper(this);
       db = mydb.getDatabaseName();
       final String [

       ] mydata;
        ArrayList<String> array = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        Cursor cr = db.rawQuery
*/
    // activity_main内のregistButtonを取得
    Button gofoodActivityButton = view.findViewById(R.id.foodRegistButton);
    //ボタンがクリックされた時の処理を追加
        gofoodActivityButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //Intentを利用して他のアクティビティに遷移する
            Intent intent = new Intent(getActivity(), FoodActivity.class);
            startActivity(intent);
        }
    });


        loadData();
        setData();

    }

    @Override
    public void onResume() {
        super.onResume();

        loadData();
        setData();
    }

    public void loadData() {
        //fragmentはsharedPreferenceを直接呼び出せないので、親のactivityを呼んでそこから取得する
        EiyouInf = requireActivity().getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        strcCalorie = EiyouInf.getString(KEY_CALORIE, "");
        strProtein = EiyouInf.getString(KEY_PROTAIN, "");
        strCarbon = EiyouInf.getString(KEY_CARBON, "");
        strFat = EiyouInf.getString(KEY_FAT, "string");
        strPurpose2 = EiyouInf.getString(KEY_PURPOSE2, "string");

    }

    public void setData() {
        calorieGoal.setText(strcCalorie);
        proteinGoal.setText(strProtein);
        carbonGoal.setText(strCarbon);
        fatGoal.setText(strFat);
        kojin_purpose.setText(strPurpose2);

    }



    @Override
    public void onActivityResult
            (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        carbonGoal = getView().findViewById(R.id.calorieGoal);
        proteinGoal = getView().findViewById(R.id.proteinGoal);
        carbonGoal = getView().findViewById(R.id.carbonGoal);
        fatGoal = getView().findViewById(R.id.fatGoal);
        kojin_purpose = getView().findViewById(R.id.kojin_purpose);


        loadData();
        setData();

    }
}

/*
<ScrollView
        android:id="@+id/scrollView2"
                android:layout_width="match_parent"
                android:layout_height="320dp"
                android:layout_marginBottom="50dp"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintHorizontal_bias="0.0"
                app:layout_constraintStart_toStartOf="parent"
                tools:context=".MainActivity">

<androidx.gridlayout.widget.GridLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="0dp"
        android:background="@drawable/grid_border"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/foodRegistButton">


</androidx.gridlayout.widget.GridLayout>
</ScrollView>

 */