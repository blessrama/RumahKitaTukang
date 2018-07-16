package blessrama.pkm.rumahkitatukang.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import blessrama.pkm.rumahkitatukang.R;
import blessrama.pkm.rumahkitatukang.RecyclerViewAdapterJob;
import blessrama.pkm.rumahkitatukang.RecyclerViewAdapterJobOnProgress;
import blessrama.pkm.rumahkitatukang.model.Job;

public class HistoryTab1 extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private RecyclerView recyclerViewJobOnProgress;
    private RecyclerViewAdapterJobOnProgress recyclerViewAdapterJobOnProgress;

    private List<Job> jobList = new ArrayList<>();

    public HistoryTab1() {

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_tab1_doing, container, false);
        recyclerViewJobOnProgress = view.findViewById(R.id.recyclerViewJobOnProgress);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").child(firebaseUser.getUid()).child("jobList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    Job job = data.getValue(Job.class);
                    if(job.getStatus().equals("on progress")){
                        jobList.add(job);
                    }
                }
                recyclerViewJobOnProgress.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewAdapterJobOnProgress = new RecyclerViewAdapterJobOnProgress(jobList, getContext());
                recyclerViewJobOnProgress.setAdapter(recyclerViewAdapterJobOnProgress);
                recyclerViewAdapterJobOnProgress.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

}
