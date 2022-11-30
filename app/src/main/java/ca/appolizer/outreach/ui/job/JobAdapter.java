package ca.appolizer.outreach.ui.job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.model.Job;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobHolder> {
    private final List<Job> jobs;
    private final Context context;

    public JobAdapter(List<Job> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View jobView = inflater.inflate(R.layout.fragment_card_job, parent, false);
        RecyclerView.ViewHolder viewHolder = new JobHolder(jobView);
        return (JobHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobHolder holder, int position) {

        holder.titleTextView.setText(jobs.get(position).getTitle());
        holder.subtitleTextView.setText(jobs.get(position).getSubtitle());

        holder.cardJob.setOnClickListener(view -> {
            Toast.makeText(context, "CLICKED", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    class JobHolder extends RecyclerView.ViewHolder {
        private CardView cardJob;
        private TextView titleTextView;
        private TextView subtitleTextView;

        public JobHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.jobTitle);
            subtitleTextView = itemView.findViewById(R.id.jobDescription);
            cardJob = itemView.findViewById(R.id.cardJob);
        }
    }
}
