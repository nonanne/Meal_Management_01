package com.example.eiyoukun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.eiyoukun.mealFragments.PageFragment1;
import com.example.eiyoukun.mealFragments.PageFragment2;
import com.example.eiyoukun.mealFragments.PageFragment3;
import com.example.eiyoukun.mealFragments.PageFragment4;

import java.util.ArrayList;
import java.util.List;

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
    private TextView calorieNow;
    private TextView proteinNow;
    private TextView carbonNow;
    private TextView fatNow;
    private TextView kojin_purpose;

    private double totalCalorie1;
    private double totalProtein1;
    private double totalCarbon1;
    private double totalFat1;
    private double totalCalorie2;
    private double totalProtein2;
    private double totalCarbon2;
    private double totalFat2;
    private double totalCalorie3;
    private double totalProtein3;
    private double totalCarbon3;
    private double totalFat3;
    private double totalCalorie4;
    private double totalProtein4;
    private double totalCarbon4;
    private double totalFat4;

    private String strCalorie2;
    private String strProtein2;
    private String strCarbon2;
    private String strFat2;

    private String strCalorie3;
    private String strProtein3;
    private String strCarbon3;
    private String strFat3;

    private SharedPreferences EiyouInf;
    private static final String SHARED_PREF_NAME1 = "EiyouInf";
    private static final String KEY_CALORIE = "calorie";
    private static final String KEY_PROTAIN = "protain";
    private static final String KEY_CARBON = "carbon";
    private static final String KEY_FAT = "fat";
    private static final String KEY_PURPOSE2 = "purpose2";
    private static final String KEY_WEIGHT2 = "weight2";

    private String strCalorie;
    private String strProtein;
    private String strCarbon;
    private String strFat;
    private String strPurpose2;
    private String strWeight2;

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    private ArrayList<Product> products;
    private ListView listView;
    private MySQLiteOpenHelper helper;
    private EntityUser entityUser;

    RoomDB database;
    List<EntityUser> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;


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

        getChildFragmentManager().setFragmentResultListener("requestKey1_0", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                if (!bundle.getString("totalCalorie1").isEmpty()) {
                    totalCalorie1 = Double.parseDouble(bundle.getString("totalCalorie1"));
                } else {
                    totalCalorie1 = 0;
                }
                if (!bundle.getString("totalProtein1").isEmpty()) {
                    totalProtein1 = Double.parseDouble(bundle.getString("totalProtein1"));
                } else {
                    totalProtein1 = 0;
                }
                if (!bundle.getString("totalCarbon1").isEmpty()) {
                    totalCarbon1 = Double.parseDouble(bundle.getString("totalCarbon1"))
                    ;
                } else {
                    totalCarbon1 = 0;
                }
                if (!bundle.getString("totalFat1").isEmpty()) {
                    totalFat1 = Double.parseDouble(bundle.getString("totalFat1"));
                } else {
                    totalFat1 = 0;
                }

                setPageToSecond();

            }
        });

        getChildFragmentManager().setFragmentResultListener("requestKey2_0", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                if (!bundle.getString("totalCalorie2").isEmpty()) {
                    totalCalorie2 = Double.parseDouble(bundle.getString("totalCalorie2"));
                } else {
                    totalCalorie2 = 0;
                }
                if (!bundle.getString("totalProtein2").isEmpty()) {
                    totalProtein2 = Double.parseDouble(bundle.getString("totalProtein2"));
                } else {
                    totalProtein2 = 0;
                }
                if (!bundle.getString("totalCarbon2").isEmpty()) {
                    totalCarbon2 = Double.parseDouble(bundle.getString("totalCarbon2"));
                } else {
                    totalCarbon2 = 0;
                }
                if (!bundle.getString("totalFat2").isEmpty()) {
                    totalFat2 = Double.parseDouble(bundle.getString("totalFat2"));
                } else {
                    totalFat2 = 0;
                }

                setPageToSecond();

            }
        });

        getChildFragmentManager().setFragmentResultListener("requestKey3_0", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                if (!bundle.getString("totalCalorie3").isEmpty()) {
                    totalCalorie3 = Double.parseDouble(bundle.getString("totalCalorie3"));
                } else {
                    totalCalorie3 = 0;
                }
                if (!bundle.getString("totalProtein3").isEmpty()) {
                    totalProtein3 = Double.parseDouble(bundle.getString("totalProtein3"));
                } else {
                    totalProtein3 = 0;
                }
                if (!bundle.getString("totalCarbon3").isEmpty()) {
                    totalCarbon3 = Double.parseDouble(bundle.getString("totalCarbon3"));
                } else {
                    totalCarbon3 = 0;
                }
                if (!bundle.getString("totalFat3").isEmpty()) {
                    totalFat3 = Double.parseDouble(bundle.getString("totalFat3"));
                } else {
                    totalFat3 = 0;
                }

                setPageToSecond();

            }
        });

        getChildFragmentManager().setFragmentResultListener("requestKey4_0", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                if (!bundle.getString("totalCalorie4").isEmpty()) {
                    totalCalorie4 = Double.parseDouble(bundle.getString("totalCalorie4"));
                } else {
                    totalCalorie4 = 0;
                }
                if (!bundle.getString("totalProtein4").isEmpty()) {
                    totalProtein4 = Double.parseDouble(bundle.getString("totalProtein4"));
                } else {
                    totalProtein4 = 0;
                }
                if (!bundle.getString("totalCarbon4").isEmpty()) {
                    totalCarbon4 = Double.parseDouble(bundle.getString("totalCarbon4"));
                } else {
                    totalCarbon4 = 0;
                }
                if (!bundle.getString("totalFat4").isEmpty()) {
                    totalFat4 = Double.parseDouble(bundle.getString("totalFat4"));
                } else {
                    totalFat4 = 0;
                }

                setPageToSecond();

            }
        });


        /*　今後上記のコードと比較したいからわざと残しています
        getChildFragmentManager().setFragmentResultListener("requestKey1_0", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String msg1_0Total = bundle.getString("bundleKey1_0");
                calorieNow.setText(msg1_0Total);
            }
        });

        getChildFragmentManager().setFragmentResultListener("requestKey1_1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String msg1_1Total = bundle.getString("bundleKey1_1");
                proteinNow.setText(msg1_1Total);
            }
        });
*/
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
        calorieNow = view.findViewById(R.id.calorieNow);
        proteinNow = view.findViewById(R.id.proteinNow);
        carbonNow = view.findViewById(R.id.carbonNow);
        fatNow = view.findViewById(R.id.fatNow);
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


        getChildFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String msg1_0Total = bundle.getString("bundleKey1_0");
                calorieNow.setText(msg1_0Total);
                String msg1_1Total = bundle.getString("bundleKey1_1");
                proteinNow.setText(msg1_1Total);
                String msg1_2Total = bundle.getString("bundleKey1_2");
                carbonNow.setText(msg1_2Total);
                String msg1_3Total = bundle.getString("bundleKey1_3");
                fatNow.setText(msg1_3Total);
            }
        });

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
        strCalorie = EiyouInf.getString(KEY_CALORIE, "");
        strProtein = EiyouInf.getString(KEY_PROTAIN, "");
        strCarbon = EiyouInf.getString(KEY_CARBON, "");
        strFat = EiyouInf.getString(KEY_FAT, "");
        strPurpose2 = EiyouInf.getString(KEY_PURPOSE2, "");
        strWeight2 = EiyouInf.getString(KEY_WEIGHT2, "");
    }


    public void setData() {
        calorieGoal.setText(strCalorie + "cal");
        proteinGoal.setText(strProtein + "g");
        carbonGoal.setText(strCarbon + "g");
        fatGoal.setText(strFat + "g");
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


    // PageFragment4つから受け取った栄養値の合計値をセット
    public void setPageToSecond() {
        strCalorie2 = String.valueOf(totalCalorie1 + totalCalorie2 + totalCalorie3 + totalCalorie4);
        strProtein2 = String.valueOf(totalProtein1 + totalProtein2 + totalProtein3 + totalProtein4);
        strCarbon2 = String.valueOf(totalCarbon1 + totalCarbon2 + totalCarbon3 + totalCarbon4);
        strFat2 = String.valueOf(totalFat1 + totalFat2 + totalFat3 + totalFat4);
        calorieNow.setText(strCalorie2 + "cal");
        proteinNow.setText(strProtein2 + "g");
        carbonNow.setText(strCarbon2 + "g");
        fatNow.setText(strFat2 + "g");

        EntityUser entityUser = new EntityUser();
        entityUser.DATE = "2020/12/14";
        entityUser.CALORIE_NOW = Double.parseDouble(strCalorie2);
        entityUser.PROTEIN_NOW = Double.parseDouble(strProtein2);
        entityUser.CARBON_NOW = Double.parseDouble(strCarbon2);
        entityUser.FAT_NOW = Double.parseDouble(strFat2);
        RoomDB.getInstance(requireContext()).daoUser().insert(entityUser);

        // TODO すでに今日の分が登録されていたらUpdateする

    }


    public void CompareEiyou() {
        if (Double.parseDouble(strCalorie) >= Double.parseDouble(strCalorie2) && strPurpose2.equals("ダイエット")
        || (Double.parseDouble(strCalorie) <= Double.parseDouble(strCalorie2) && strPurpose2.equals("デブエット"))) {
            strCalorie3.equals("〇");
        } else {
            strCalorie3.equals("✖");
        }
        if (Double.parseDouble(strProtein) >= Double.parseDouble(strProtein2) && strPurpose2.equals("ダイエット")
                || (Double.parseDouble(strProtein) <= Double.parseDouble(strProtein2) && strPurpose2.equals("デブエット"))) {
            strProtein3.equals("〇");
        } else {
            strProtein3.equals("✖");
        }
        if (Double.parseDouble(strCarbon) >= Double.parseDouble(strCarbon2) && strPurpose2.equals("ダイエット")
                || (Double.parseDouble(strProtein) <= Double.parseDouble(strProtein2) && strPurpose2.equals("デブエット"))) {
            strCarbon3.equals("〇");
        } else {
            strCarbon3.equals("✖");
        }
        if (Double.parseDouble(strFat) >= Double.parseDouble(strFat2) && strPurpose2.equals("ダイエット")
                || (Double.parseDouble(strFat) <= Double.parseDouble(strFat2) && strPurpose2.equals("デブエット"))) {
            strFat3.equals("〇");
        } else {
            strFat3.equals("✖");
        }

    }

}