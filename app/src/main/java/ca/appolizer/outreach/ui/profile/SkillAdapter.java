package ca.appolizer.outreach.ui.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.model.Skill;


public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {
    private List<Skill> skills;

    public SkillAdapter(List<Skill> skills) {
        this.skills = skills;
    }


    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_skillset, parent, false);
        RecyclerView.ViewHolder viewHolder = new SkillViewHolder(view);
        return (SkillViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        holder.skillNameTxt.setText(skills.get(position).getName());
        holder.skillYearTxt.setText(position + " years");

        holder.removeSkillBtn.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), "Skill has been removed " + skills.get(position), Toast.LENGTH_SHORT).show();
            skills.remove(position);
            notifyDataSetChanged();
        });
    }


    @Override
    public int getItemCount() {
        return skills.size();
    }

    class SkillViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageButton removeSkillBtn;
        private AppCompatTextView skillNameTxt;
        private AppCompatTextView skillYearTxt;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            skillNameTxt = itemView.findViewById(R.id.skill_name);
            skillYearTxt = itemView.findViewById(R.id.skill_year);
            removeSkillBtn = itemView.findViewById(R.id.deleteSkillBtn);

        }
    }
}
