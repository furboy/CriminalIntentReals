package com.ctech.aleco.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;


public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "com.ctech.aleco.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId){
     Intent myIntent = new Intent(packageContext, CrimeActivity.class);
     myIntent.putExtra(EXTRA_CRIME_ID, crimeId);
        return myIntent;
    }
    @Override
    protected Fragment createFragment(){
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
