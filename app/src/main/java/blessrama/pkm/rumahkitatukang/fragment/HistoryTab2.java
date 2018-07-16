package blessrama.pkm.rumahkitatukang.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blessrama.pkm.rumahkitatukang.R;

public class HistoryTab2 extends Fragment {
    public HistoryTab2() {

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_tab2_done, container, false);
        return rootView;
    }
}
