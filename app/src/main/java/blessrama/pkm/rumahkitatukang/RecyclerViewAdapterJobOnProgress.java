package blessrama.pkm.rumahkitatukang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import blessrama.pkm.rumahkitatukang.model.Job;

public class RecyclerViewAdapterJobOnProgress extends RecyclerView.Adapter<RecyclerViewAdapterJobOnProgress.ViewHolder> {

    private List<Job> jobList;
    private Context context;

    public RecyclerViewAdapterJobOnProgress(List<Job> jobList, Context context) {
        this.jobList = jobList;
        this.context = context;
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

                }
            });
        }
    }

}
