package blessrama.pkm.rumahkitatukang.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Switch;

import blessrama.pkm.rumahkitatukang.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment2 extends Fragment {
    private Switch sw;

    public HomeFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @OnClick(R.id.availability_worker)
    public void onClick(){
        if (sw.isChecked()){
            // Gabut
        }
        else{
            Intent intent = new Intent(HomeFragment2.this.getActivity(), HomeFragment.class);
            startActivity(intent);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

}
