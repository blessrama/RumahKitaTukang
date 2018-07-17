package blessrama.pkm.rumahkitatukang.fragment;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import blessrama.pkm.rumahkitatukang.RecyclerViewAdapterJobDone;
import blessrama.pkm.rumahkitatukang.RecyclerViewAdapterJobOnProgress;
import blessrama.pkm.rumahkitatukang.activity.LoginActivity;
import blessrama.pkm.rumahkitatukang.model.Job;

public class HistoryTab2 extends Fragment {

    private RecyclerView recyclerViewJobDone;
    private RecyclerViewAdapterJobDone recyclerViewAdapterJobDone;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private List<Job> jobList = new ArrayList<>();

    private ProgressDialog progressDialog;

    public HistoryTab2() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_tab2_done, container, false);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Tunggu dulu...");
        progressDialog.setIndeterminate(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerViewJobDone = view.findViewById(R.id.recyclerViewJobDone);
        databaseReference.child("users").child(firebaseUser.getUid()).child("jobList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    Job job = data.getValue(Job.class);
                    if(job.getStatus().equals("done")){
                        jobList.add(job);
                    }
                }
                recyclerViewJobDone.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewAdapterJobDone = new RecyclerViewAdapterJobDone(jobList, getContext());
                recyclerViewJobDone.setAdapter(recyclerViewAdapterJobDone);
                recyclerViewAdapterJobDone.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }
}
