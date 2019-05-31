package com.aswin.configurationchangedemo;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by Aswin on 31,May,2019
 */
public class ConfigurationUnhandledActivity extends AppCompatActivity implements ShowFragmentInterface {

    private LinearLayout fragmentOneHolder;
    private LinearLayout fragmentTwoHolder;
    private ConstraintLayout mainContainer;
    private final String TAG = "UnHandledActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_configuration);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
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
}
