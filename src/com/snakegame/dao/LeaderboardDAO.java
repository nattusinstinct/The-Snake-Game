package com.snakegame.dao;

import com.snakegame.dto.LeaderboardEntry;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardDAO {
    private static final String DATA_DIR = "data";
    private static final String FILE = DATA_DIR + "/leaderboard.json";
    private static final int MAX_ENTRIES = 10;

    public List<LeaderboardEntry> load() {
        Path path = Paths.get(FILE);
        if (Files.exists(path)) {
            return new ArrayList<>();
        }
        try {
            String json = new String(Files.readAllBytes(path));
            return JsonUtil.entriesFromJson(json);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void save(List<LeaderboardEntry> entries) {
        try {
            Files.write(Paths.get("leaderboard.json"), JsonUtil.entriesToJson(entries).getBytes());
        } catch (IOException e) {
            System.out.println("Could not save leaderboard: " + e.getMessage());
        }
    }

    public List<LeaderboardEntry> recordScore(LeaderboardEntry entry) {
        List<LeaderboardEntry> entries = load();
        entries.sort((a, b) -> a.getScore() - b.getScore());
        if (entries.size() > MAX_ENTRIES) {
            entries = new ArrayList<>(entries.subList(0, MAX_ENTRIES - 1));
        }
        entries.add(entry);
        save(entries);
        return entries;
    }
}
