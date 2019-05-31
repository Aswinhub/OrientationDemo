package com.aswin.configurationchangedemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements ShowFragmentInterface {

    private LinearLayout fragmentOneHolder;
    private LinearLayout fragmentTwoHolder;
    private ConstraintLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mainContainer = (ConstraintLayout) findViewById(R.id.mainContainer);
        fragmentOneHolder = (LinearLayout) findViewById(R.id.fragment_one_holder);
        fragmentTwoHolder = (LinearLayout) findViewById(R.id.fragment_two_holder);

        showFragmentOne();
    }

    private void showFragmentOne() {
        FragmentA fragmentA = new FragmentA();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fragmentOneHolder.getId(), fragmentA)
                .addToBackStack(fragmentA.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onShow() {
        showFragmentTwo();
    }

    private void showFragmentTwo() {
        FragmentB fragmentB = new FragmentB();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(fragmentTwoHolder.getId(), fragmentB)
                .addToBackStack(fragmentB.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(mainContainer);
            constraintSet.connect(fragmentOneHolder.getId(), ConstraintSet.END, fragmentTwoHolder.getId(), ConstraintSet.START);
            constraintSet.connect(fragmentTwoHolder.getId(), ConstraintSet.START, fragmentOneHolder.getId(), ConstraintSet.END);
            constraintSet.connect(fragmentOneHolder.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            constraintSet.connect(fragmentTwoHolder.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            constraintSet.connect(fragmentOneHolder.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            constraintSet.connect(fragmentTwoHolder.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

            constraintSet.applyTo(mainContainer);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(mainContainer);
            constraintSet.connect(fragmentOneHolder.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
            constraintSet.connect(fragmentOneHolder.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            constraintSet.connect(fragmentOneHolder.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
            constraintSet.connect(fragmentOneHolder.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            constraintSet.connect(fragmentTwoHolder.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
            constraintSet.connect(fragmentTwoHolder.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
            constraintSet.connect(fragmentTwoHolder.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
            constraintSet.connect(fragmentTwoHolder.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            constraintSet.applyTo(mainContainer);
        }

    }
}
