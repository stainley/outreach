package ca.appolizer.outreach.ui.profile.skillset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.model.Skillset;


public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {

    private final List<Skillset> skillsetList;
    private Context context;

    public SkillAdapter(List<Skillset> skillsetList) {
        this.skillsetList = skillsetList;
    }


    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_skillset, parent, false);
        context = parent.getContext();
        return new SkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        holder.skillNameTxt.setText(skillsetList.get(position).getSkill().getName());
        String yearsOfExp = skillsetList.get(position).getTotalYearsExperience() + " " + context.getResources().getString(R.string.years);
        holder.skillYearTxt.setText(yearsOfExp);

        holder.removeSkillBtn.setOnClickListener(view -> {
            skillsetList.remove(position);

            notifyItemRemoved(position);
            notifyItemRangeRemoved(position, skillsetList.size());
        });
    }

    @Override
    public int getItemCount() {
        return skillsetList.size();
    }

    static class SkillViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatImageButton removeSkillBtn;
        private final AppCompatTextView skillNameTxt;
        private final AppCompatTextView skillYearTxt;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            skillNameTxt = itemView.findViewById(R.id.skill_name);
            skillYearTxt = itemView.findViewById(R.id.skill_year);
            removeSkillBtn = itemView.findViewById(R.id.deleteSkillBtn);

        }
    }
}
