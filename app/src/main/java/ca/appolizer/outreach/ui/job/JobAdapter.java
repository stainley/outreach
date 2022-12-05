package ca.appolizer.outreach.ui.job;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.databinding.FragmentJobDescriptionBinding;
import ca.appolizer.outreach.model.Job;
import ca.appolizer.outreach.ui.job.description.JobDescriptionActivity;
import ca.appolizer.outreach.ui.job.description.JobDescriptionFragment;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobHolder> {
    public List<Job> jobs;
    private static List<Job> arrayListJobs = new ArrayList<>();
    private final Fragment context;
    private long studentId;

    public JobAdapter(Fragment context, long studentId) {
        this.context = context;
        this.studentId = studentId;
        if (jobs != null) {
            arrayListJobs = jobs;
        }
    }

    public void add(List<Job> jobsAdded) {
        if (jobsAdded != null && jobsAdded.size() > 0) {
            //jobs.addAll(jobsAdded);
            this.arrayListJobs = jobsAdded;
            notifyDataSetChanged();
        }
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

        holder.titleTextView.setText(jobs.get(position).getName());

        holder.cardJob.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), JobDescriptionActivity.class);
            intent.putExtra("id", jobs.get(position).getId());
            intent.putExtra("name", jobs.get(position).getName());
            intent.putExtra("description", jobs.get(position).getDescription());
            intent.putExtra("student_id", studentId);
            context.startActivity(intent);
        });
    }

    public void filter(String text) {
        text = text.toLowerCase();
        jobs.clear();

        if (text.length() == 0) {
            jobs.addAll(arrayListJobs);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(text);
            jobs = arrayListJobs.stream().filter(job -> job.getName().toLowerCase().contains(sb))
                    .collect(Collectors.toList());

        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return jobs != null ? jobs.size() : 0;
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
