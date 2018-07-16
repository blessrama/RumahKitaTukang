package blessrama.pkm.rumahkitatukang.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blessrama.pkm.rumahkitatukang.R;

public class HistoryTab1 extends Fragment {
    public HistoryTab1() {

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_tab1_doing, container, false);
        return rootView;
    }
}
