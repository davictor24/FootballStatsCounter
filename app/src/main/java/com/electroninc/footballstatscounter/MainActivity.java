package com.electroninc.footballstatscounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<String> STATS = Arrays.asList(
            "goals",
            "shots",
            "shotsOnTarget",
            "fouls",
            "yellowCards",
            "redCards",
            "offsides",
            "corners"
    );

    private final List<String> TEAMS = Arrays.asList("TeamA", "TeamB");

    private StatsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(StatsViewModel.class);

        for (String stat : STATS) {
            for (String team : TEAMS) {
                int buttonRes = getResourceFromId(getButtonId(stat, team));
                final String textViewId = getTextViewId(stat, team);
                int textViewRes = getResourceFromId(textViewId);

                Button button = findViewById(buttonRes);
                final TextView textView = findViewById(textViewRes);
                textView.setText(String.valueOf(getStat(textViewId)));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int i = getStat(textViewId);
                        textView.setText(String.valueOf(i + 1));
                        viewModel.stats.put(textViewId, i + 1);
                    }
                });
            }
        }

        Button resetBtn = findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (String stat : STATS) {
                    for (String team : TEAMS) {
                        final String textViewId = getTextViewId(stat, team);
                        int textViewRes = getResourceFromId(textViewId);
                        TextView textView = findViewById(textViewRes);
                        viewModel.stats.put(textViewId, 0);
                        textView.setText("0");
                    }
                }
            }
        });
    }

    private String getButtonId(String stat, String team) {
        return stat + "Btn" + team;
    }

    private String getTextViewId(String stat, String team) {
        return stat + team;
    }

    private int getResourceFromId(String id) {
        return getResources().getIdentifier(id, "id", getPackageName());
    }

    private int getStat(String key) {
        Integer integer = viewModel.stats.get(key);
        return (integer == null) ? 0 : integer;
    }
}
