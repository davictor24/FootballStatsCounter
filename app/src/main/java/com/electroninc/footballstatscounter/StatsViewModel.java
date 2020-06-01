package com.electroninc.footballstatscounter;

import java.util.HashMap;

import androidx.lifecycle.ViewModel;

public class StatsViewModel extends ViewModel {
    public HashMap<String, Integer> stats = new HashMap<>();
}
