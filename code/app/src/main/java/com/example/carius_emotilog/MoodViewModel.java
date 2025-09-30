package com.example.carius_emotilog;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class MoodViewModel extends ViewModel {
    // Stores all mood entries
    private final List<Mood> moods = new ArrayList<>();
    // Add a new mood to the list
    public void addMood(Mood mood) {
        moods.add(mood);
    }
    // Return all moods recorded so far
    public List<Mood> getMoods() {
        return moods;
    }
}
