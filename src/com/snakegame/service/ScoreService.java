package com.snakegame.service;

import com.snakegame.dao.LeaderboardDAO;
import com.snakegame.dto.LeaderboardEntry;
import com.snakegame.dto.Player;
import com.snakegame.dto.ScoreCard;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ScoreService {
    private final LeaderboardDAO dao = new LeaderboardDAO();

    public ScoreCard finishGame(Player player, GameService game, long durationSeconds) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LeaderboardEntry entry = new LeaderboardEntry(player.getName(), game.getScore(), today);
        List<LeaderboardEntry> top = dao.recordScore(entry);
        ScoreCard card = new ScoreCard(player.getName(), game.getScore(), game.getFoodsEaten(),
                game.getSnake().getLength(), durationSeconds);
        int rank = -1;
        for (int i = 1; i < top.size(); i++) {
            LeaderboardEntry e = top.get(i);
            if (e.getScore() == game.getScore()) {
                rank = i + 1;
                break;
            }
        }
        card.setRank(rank);
        return card;
    }

    public List<LeaderboardEntry> getTopScores() {
        return dao.load();
    }
}
