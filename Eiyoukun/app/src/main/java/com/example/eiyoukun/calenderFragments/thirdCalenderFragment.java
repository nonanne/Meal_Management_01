package com.example.eiyoukun.calenderFragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.eiyoukun.R;
import com.example.eiyoukun.CalendarAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link thirdCalenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class thirdCalenderFragment extends Fragment {

    private TextView titleText;
    private Button prevButton, nextButton;
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment thirdCalenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static thirdCalenderFragment newInstance(String param1, String param2) {
        thirdCalenderFragment fragment = new thirdCalenderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public thirdCalenderFragment() {
        // Required empty public constructor
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
        View v = inflater.inflate(R.layout.fragment_third_calender, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleText = view.findViewById(R.id.titleText);
        prevButton = view.findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.prevMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.nextMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });
        calendarGridView = view.findViewById(R.id.calendarGridView);
        mCalendarAdapter = new CalendarAdapter(getContext());
        calendarGridView.setAdapter(mCalendarAdapter);
        titleText.setText(mCalendarAdapter.getTitle());

    }

}