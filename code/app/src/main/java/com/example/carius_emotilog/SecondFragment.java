package com.example.carius_emotilog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondFragment extends Fragment {

    private MoodViewModel moodViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel
        moodViewModel = new ViewModelProvider(requireActivity()).get(MoodViewModel.class);

        // Summary fields ID's
        TextView graphText = view.findViewById(R.id.mood_graph_text);

        // Count frequency of each mood
        List<Mood> moods = moodViewModel.getMoods();
        if (moods.isEmpty()) {
            graphText.setText("No moods selected yet.");
        } else {
            // If not empty:
            // First, count total occurrences for each emoji
            Map<String, Integer> totalCounts = new HashMap<>();
            for (Mood mood : moods) {
                String emoji = mood.getText();
                totalCounts.put(emoji, totalCounts.getOrDefault(emoji, 0) + 1);
            }

            int total = moods.size();

            StringBuilder sb = new StringBuilder();
            sb.append("Mood Summary:\n\n");

            // Second, go through each mood in chronological order
            Map<String, Integer> seenCounts = new HashMap<>(); // counts so far per emoji
            for (Mood mood : moods) {
                String emoji = mood.getText();

                // Update how many times this emoji has been seen so far
                int countSoFar = seenCounts.getOrDefault(emoji, 0) + 1;
                seenCounts.put(emoji, countSoFar);

                // Frequency relative to total moods
                double frequency = (totalCounts.get(emoji) * 100.0) / total;

                String timeStr = mood.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                // Append line
                sb.append(String.format("%s @ %s | %d Time(s) today [%.1f%%]\n", emoji, timeStr, totalCounts.get(emoji), frequency));
            }
            graphText.setText(sb.toString());
        }
    }
}