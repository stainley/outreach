package ca.appolizer.outreach.ui.job.description;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import ca.appolizer.outreach.R;
import ca.appolizer.outreach.databinding.ActivityJobDescriptionBinding;

public class JobDescriptionActivity extends AppCompatActivity {

    private ActivityJobDescriptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityJobDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String title = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        TextView textViewTitle = findViewById(R.id.titleDescription);
        //TextView textJobDescription = findViewById(R.id.textJobDescription);
        toolBarLayout.setTitle(title);
        //textJobDescription.setText(description);
        WebView webViewDescription = findViewById(R.id.textJobDescription);
        webViewDescription.loadData(description, "text/html", "utf-8");



        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view -> Snackbar.make(view, "Apply to job", Snackbar.LENGTH_LONG)
                .setAction("Confirm", listener -> {
                    sendMessage();
                }).show());
    }

    private void sendMessage() {
        Toast.makeText(this, "Send info", Toast.LENGTH_SHORT).show();
    }
}