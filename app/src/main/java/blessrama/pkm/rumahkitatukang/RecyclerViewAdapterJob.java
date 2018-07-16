package blessrama.pkm.rumahkitatukang;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import blessrama.pkm.rumahkitatukang.activity.DetailServiceActivity;
import blessrama.pkm.rumahkitatukang.model.Job;

public class RecyclerViewAdapterJob extends RecyclerView.Adapter<RecyclerViewAdapterJob.ViewHolder> {

    private List<Job> jobList;
    private Context context;

    public RecyclerViewAdapterJob(List<Job> jobList, Context context) {
        this.jobList = jobList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_adapter_job, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.txtJobId.setText(job.getId());
        holder.txtJobCategory.setText(job.getJobCategory());
        holder.txtWorkLocation.setText(job.getLocation());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtJobId;
        private TextView txtJobCategory;
        private TextView txtWorkLocation;
        public ViewHolder(View itemView) {
            super(itemView);
            txtJobId = itemView.findViewById(R.id.txtJobId);
            txtJobCategory = itemView.findViewById(R.id.txtJobCategory);
            txtWorkLocation = itemView.findViewById(R.id.txtWorkLocation);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailServiceActivity.class);
                    intent.putExtra("jobId", txtJobId.getText().toString());
                    context.startActivity(intent);
                }
            });
        }
    }
}
