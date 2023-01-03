package ca.appolizer.outreach.ui.profile.skillset;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.appolizer.outreach.data.dto.SkillDto;

public class SkillSetViewModel extends ViewModel {

    public String token;
    private MutableLiveData<SkillDto> skillMutableLiveData;

    public SkillSetViewModel() {
        skillMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<SkillDto> getSkillMutableLiveData() {
        return skillMutableLiveData;
    }


}
