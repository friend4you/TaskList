package com.example.vladarsenyuk.tasklist;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by VladArsnyuk on 10.11.2016.
 */

public class Start extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);

        ListFragment listFragment = new ListFragment();
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, ListFragment.newInstance());
        ft.commit();
    }
}
