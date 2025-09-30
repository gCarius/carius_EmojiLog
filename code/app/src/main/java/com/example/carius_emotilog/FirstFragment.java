package com.example.carius_emotilog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {
    private MoodViewModel moodViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel
        moodViewModel = new ViewModelProvider(requireActivity()).get(MoodViewModel.class);

        // Button IDs
        int[] buttonIds = {
                R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6,
                R.id.button7, R.id.button8, R.id.button9
        };

        // Corresponding emoji moods
        String[] moods = {"🤩","😍","😀","🙂","😐","😞","🤪","😭","🤯"};

        // Loop through buttons
        for (int i = 0; i < buttonIds.length; i++) {
            int index = i;
            Button btn = view.findViewById(buttonIds[i]);
            if (btn != null) {
                // When a mood button is clicked:
                // Add the corresponding mood to the ViewModel
                btn.setOnClickListener(v -> {
                    moodViewModel.addMood(new Mood(moods[index]));
                    Log.d("MoodTracker", "Moods so far: " + moodViewModel.getMoods());
                });
            }
        }

        // Next button navigation -> SecondFragment
        Button nextBtn = view.findViewById(R.id.button_first);
        if (nextBtn != null) {
            nextBtn.setOnClickListener(v ->
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment)
            );
        }
    }
}