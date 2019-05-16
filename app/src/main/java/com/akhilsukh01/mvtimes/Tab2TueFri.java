package com.akhilsukh01.mvtimes;

/**
 * Created by akhil on 7/24/2017.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akhilsukh01.mvtimes.R;

public class Tab2TueFri extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2tue, container, false);
        return view;
    }
}
