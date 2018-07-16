package blessrama.pkm.rumahkitatukang.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import blessrama.pkm.rumahkitatukang.R;
import blessrama.pkm.rumahkitatukang.model.Job;
import blessrama.pkm.rumahkitatukang.model.User;

public class DetailServiceActivity extends AppCompatActivity {

    private Button btnTakeJob;
    private TextView txtJobId, txtConsumerName, txtWorkLocation;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private String jobId;
    private int contractInProgress;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_service);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        txtJobId = findViewById(R.id.txtJobId);

        Intent intent = getIntent();
        jobId = intent.getStringExtra("jobId");
        txtJobId.setText(jobId);

        txtConsumerName = findViewById(R.id.txtConsumerName);
        txtWorkLocation = findViewById(R.id.txtWorkLocation);

        databaseReference.child("jobs").child(jobId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Job job = dataSnapshot.getValue(Job.class);
                txtWorkLocation.setText("Lokasi pengerjaan : " + job.getLocation());
                databaseReference.child("users").child(job.getConsumerId()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        txtConsumerName.setText("Konsumen : " + user.getName());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnTakeJob = findViewById(R.id.btnTakeJob);
        btnTakeJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("jobs").child(jobId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        job = dataSnapshot.getValue(Job.class);
                        databaseReference.child("jobs").child(jobId).child("workerId").setValue(firebaseUser.getUid());
                        job.setStatus("on progress");
                        databaseReference.child("users").child(firebaseUser.getUid()).child("jobList").child(jobId).setValue(job);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                databaseReference.child("users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        contractInProgress = user.getContractInProgress();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                contractInProgress+=1;
                databaseReference.child("users").child(firebaseUser.getUid()).child("contractInProgress").setValue(contractInProgress);
                finish();
            }
        });
    }
}
