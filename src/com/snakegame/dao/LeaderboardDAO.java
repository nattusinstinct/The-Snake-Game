package com.snakegame.dao;

import com.snakegame.dto.LeaderBoardEntry;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardDAO {
    private static final String FILE = "data/leaderboard.json";
    private static final int MAX_ENTRIES = 10;

    public List<LeaderBoardEntry> load() {
        Path path = Paths.get(FILE);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        try {
            String json = new String(Files.readAllBytes(path));
            return JsonUtil.entriesFromJson(json);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void save(List<LeaderBoardEntry> entries) {
        try {
            
        Path path = Paths.get(FILE);
        Files.write(path,JsonUtil.entriesToJson(entries).getBytes());

        } catch (IOException e) {
            System.out.println("Could not save leaderboard: " + e.getMessage());
        }
    }

    public List<LeaderBoardEntry> recordScore(LeaderBoardEntry entry) {
        List<LeaderBoardEntry> entries = load();
        entries.add(entry);
        entries.sort((a, b) -> b.getScore() - a.getScore());
        if (entries.size() > MAX_ENTRIES) {
                entries = new ArrayList<>(entries.subList(0, MAX_ENTRIES));
        }
        save(entries);
        return entries;
    }
}
