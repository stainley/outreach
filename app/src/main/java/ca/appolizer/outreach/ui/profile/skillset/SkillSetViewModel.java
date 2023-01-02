package ca.appolizer.outreach.ui.profile.skillset;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.appolizer.outreach.data.model.Skill;

public class SkillSetViewModel extends ViewModel {

    public String token;
    private MutableLiveData<Skill> skillMutableLiveData;

    public SkillSetViewModel() {
        skillMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Skill> getSkillMutableLiveData() {
        return skillMutableLiveData;
    }


}
