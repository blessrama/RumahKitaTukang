package blessrama.pkm.rumahkitatukang;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blessrama.pkm.rumahkitatukang.R;

public class SelesaiFragment extends Fragment {
    public SelesaiFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_selesai, container, false);
        return rootView;
    }
}
