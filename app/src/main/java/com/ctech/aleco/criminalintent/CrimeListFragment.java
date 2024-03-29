package com.ctech.aleco.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {
private RecyclerView mCrimeRecyclerView;
private CrimeAdapter mAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View myView = inflater.inflate(R.layout.fragment_crime_list,container,false);

        mCrimeRecyclerView = myView.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return myView;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {


    private TextView mTitleTextView;
    private TextView mDateTextView;
    private Crime mCrime;
    private ImageView mSolvedImageView;
    private static final String EXTRA_CRIME_ID = "com.ctech.aleco.criminalintent.crime_id";


    public CrimeHolder(LayoutInflater inflater, ViewGroup parent){
        super(inflater.inflate(R.layout.list_item_crime, parent, false));
        mTitleTextView = itemView.findViewById(R.id.crime_title);
        mDateTextView = itemView.findViewById(R.id.crime_date);

        mSolvedImageView = itemView.findViewById(R.id.crime_solved);

        itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
            Intent myIntent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(myIntent);
        }
        public void bind(Crime crime) {
        mCrime = crime;
        mTitleTextView.setText(mCrime.getTitle());
        mDateTextView.setText(mCrime.getDate().toString());

        mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE: View.GONE);
        }


    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
    private List<Crime> mCrimes;
            public CrimeAdapter(List<Crime> crimes){
                mCrimes = crimes;
            }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                LayoutInflater myLayoutInflater = LayoutInflater.from(getActivity());
                return new CrimeHolder(myLayoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder crimeHolder, int position) {
         Crime myCrime = mCrimes.get(position);
         crimeHolder.bind(myCrime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }


    }
}