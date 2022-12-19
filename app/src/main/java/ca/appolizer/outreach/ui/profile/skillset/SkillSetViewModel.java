package ca.appolizer.outreach.ui.profile.skillset;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ca.appolizer.outreach.controller.ApiClient;
import ca.appolizer.outreach.model.Skill;
import ca.appolizer.outreach.model.request.StudentSkillsetRequest;
import ca.appolizer.outreach.model.response.StudentSkillsetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
