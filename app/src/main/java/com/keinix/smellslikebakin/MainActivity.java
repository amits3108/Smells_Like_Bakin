package com.keinix.smellslikebakin;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.keinix.smellslikebakin.GridFragment.OnRecipeSelectedInterface;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnRecipeSelectedInterface, OnRecipeSelectedInterface {
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String GRID_FRAGMENT = "grid_fragment";
    public static final String VIEW_PAGER_FRAGMENT = "view_pager_fragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        Toast.makeText(this, isTablet + "", Toast.LENGTH_SHORT).show();

        if (!isTablet) {
            ListFragment savedFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
            if (savedFragment == null) {
                ListFragment fragment = new ListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
                fragmentTransaction.commit();
            }
                } else {
                    GridFragment savedFragment = (GridFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
                    if (savedFragment == null) {
                        GridFragment fragment = new GridFragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
                        fragmentTransaction.commit();
                    }
        }
    }

    @Override
    public void onListRecipeSelected(int index) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEW_PAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onGridRecipeSelected(int index) {

    }
}
