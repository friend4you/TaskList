package com.example.vladarsenyuk.tasklist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by VladArsnyuk on 10.11.2016.
 */

public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("LOG:", "fragment create");
        return inflater.inflate(R.layout.activity_main, container, false);
    }
    public static ListFragment newInstance(){
        ListFragment lf = new ListFragment();
        return lf;
    }
}
