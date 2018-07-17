package blessrama.pkm.rumahkitatukang;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import blessrama.pkm.rumahkitatukang.model.Job;
import blessrama.pkm.rumahkitatukang.model.User;

public class RecyclerViewAdapterJobOnProgress extends RecyclerView.Adapter<RecyclerViewAdapterJobOnProgress.ViewHolder> {

    private List<Job> jobList;
    private Context context;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private int contractCompleted;

    public RecyclerViewAdapterJobOnProgress(List<Job> jobList, Context context) {
        this.jobList = jobList;
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_adapter_job_on_progress, parent, false);
        return new RecyclerViewAdapterJobOnProgress.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.txtJobId.setText(job.getId());
        holder.txtConsumerName.setText(job.getConsumerId());
        holder.txtJobCategory.setText(job.getJobCategory());
        holder.txtWorkLocation.setText(job.getLocation());
        holder.txtWorkStatus.setText(job.getStatus());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJobId;
        private TextView txtConsumerName;
        private TextView txtJobCategory;
        private TextView txtWorkLocation;
        private TextView txtWorkStatus;
        public ViewHolder(View itemView) {
            super(itemView);
            txtJobId = itemView.findViewById(R.id.txtJobId);
            txtConsumerName = itemView.findViewById(R.id.txtConsumerName);
            txtJobCategory = itemView.findViewById(R.id.txtJobCategory);
            txtWorkLocation = itemView.findViewById(R.id.txtWorkLocation);
            txtWorkStatus = itemView.findViewById(R.id.txtWorkStatus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage("Is the job done?");
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            databaseReference.child("users").child(firebaseUser.getUid()).child("jobList").child(txtJobId.getText().toString()).child("status").setValue("done");
                            databaseReference.child("users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    contractCompleted = user.getContractCompleted();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            contractCompleted +=1;
                            databaseReference.child("users").child(firebaseUser.getUid()).child("contractCompleted").setValue(contractCompleted);
                        }
                    });

                    alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        }
    }
}
