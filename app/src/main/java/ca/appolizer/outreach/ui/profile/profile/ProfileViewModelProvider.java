package ca.appolizer.outreach.ui.profile.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProfileViewModelProvider implements ViewModelProvider.Factory {

    private String token;
    private long id;

    public ProfileViewModelProvider(String token, long id) {
        this.token = token;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileViewModel(token, id);
    }
}
