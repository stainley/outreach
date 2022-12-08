package ca.appolizer.outreach.ui.job;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class JobViewModel implements ViewModelProvider.Factory {
    private String token;
    private Context context;

    private JobAdapter adapter;

    public JobViewModel(Context context, String token, JobAdapter adapter) {
        this.context = context;
        this.token = token;
        this.adapter = adapter;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(context, token, adapter);
    }
}
