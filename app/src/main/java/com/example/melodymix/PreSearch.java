package com.example.melodymix;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreSearch extends Fragment {

    private OnSearchButtonClickListener mListener;

    public interface OnSearchButtonClickListener {
        void onSearchButtonClicked();
    }

    public PreSearch() {
        // Required empty public constructor
    }

    public static PreSearch newInstance() {
        return new PreSearch();
    }

    public void setOnSearchButtonClickListener(OnSearchButtonClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchButtonClickListener) {
            mListener = (OnSearchButtonClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSearchButtonClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pre_search, container, false);

        Button searchButton = view.findViewById(R.id.searchBtn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onSearchButtonClicked();
                }
            }
        });

        return view;
    }
}
