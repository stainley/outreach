package ca.appolizer.outreach.ui.myjob;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.model.response.JobApplied;

public class MyJobAdapter extends RecyclerView.Adapter<MyJobAdapter.MyJobViewHolder> {

    private List<JobApplied> myJobsApplied;
    private Context context;

    public MyJobAdapter(List<JobApplied> myJobsApplied, Context context) {
        this.myJobsApplied = myJobsApplied;
        this.context = context;
    }

    public void addJobsApplied(List<JobApplied> jobApplieds) {
        this.myJobsApplied = jobApplieds;
    }

    @NonNull
    @Override
    public MyJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_card_myjob, parent, false);
        RecyclerView.ViewHolder myJobViewHolder = new MyJobViewHolder(view);

        return (MyJobViewHolder) myJobViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyJobViewHolder holder, int position) {
        holder.userId.setText(String.valueOf(myJobsApplied.get(position).getUserId()));
        holder.jobId.setText(String.valueOf(myJobsApplied.get(position).getJobId()));
    }

    @Override
    public int getItemCount() {
        return myJobsApplied.size();
    }

    class MyJobViewHolder extends RecyclerView.ViewHolder {

        private TextView userId;
        private TextView jobId;

        public MyJobViewHolder(@NonNull View itemView) {
            super(itemView);

            userId = itemView.findViewById(R.id.jobTitle);
            jobId = itemView.findViewById(R.id.jobDescription);
        }
    }
}
