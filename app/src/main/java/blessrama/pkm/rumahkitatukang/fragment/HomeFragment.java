package blessrama.pkm.rumahkitatukang.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

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
import blessrama.pkm.rumahkitatukang.model.Job;
import blessrama.pkm.rumahkitatukang.model.JobCategory;
import blessrama.pkm.rumahkitatukang.model.User;

public class HomeFragment extends Fragment {

    private Switch switchWorkingStatus;
    private TextView txtWorkingStatus;

    private LinearLayout linearLayoutJob;
    private List<String> jobCategoryList = new ArrayList<>();
    private Spinner spinnerJob;
    private List<Job> jobList = new ArrayList<>();
    private RecyclerView recyclerViewJob;
    private RecyclerViewAdapterJob recyclerViewAdapterJob;

    private LinearLayout linearLayoutInfo;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        switchWorkingStatus = view.findViewById(R.id.switchWorkingStatus);
        databaseReference.child("users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                switchWorkingStatus.setChecked(user.getWorkingStatus());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        txtWorkingStatus = view.findViewById(R.id.txtWorkingStatus);

        linearLayoutJob = view.findViewById(R.id.linearLayoutJob);

        spinnerJob = view.findViewById(R.id.spinnerJob);
        databaseReference.child("jobCategories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobCategoryList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    JobCategory jobCategory = data.getValue(JobCategory.class);
                    jobCategoryList.add(jobCategory.getName());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, jobCategoryList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerJob.setAdapter(dataAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerViewJob = view.findViewById(R.id.recyclerViewJob);
        databaseReference.child("jobs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()
                        ) {
                    Job job = data.getValue(Job.class);
                    if(job.getWorkerId().equals("none")) {
                        jobList.add(job);
                    }
                }
                recyclerViewJob.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewAdapterJob = new RecyclerViewAdapterJob(jobList, getContext());
                recyclerViewJob.setAdapter(recyclerViewAdapterJob);
                recyclerViewAdapterJob.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        linearLayoutInfo = view.findViewById(R.id.linearLayoutInfo);

        switchWorkingStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                databaseReference.child("users").child(firebaseUser.getUid()).child("workingStatus").setValue(switchWorkingStatus.isChecked());
                if (switchWorkingStatus.isChecked()){
                    txtWorkingStatus.setText("Bekerja");
                    linearLayoutJob.setVisibility(View.VISIBLE);
                    linearLayoutInfo.setVisibility(View.GONE);
                } else{
                    txtWorkingStatus.setText("Libur");
                    linearLayoutJob.setVisibility(View.GONE);
                    linearLayoutInfo.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }

}
