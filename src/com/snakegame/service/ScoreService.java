package com.snakegame.service;

import com.snakegame.dao.LeaderboardDAO;
import com.snakegame.dto.LeaderBoardEntry;
import com.snakegame.dto.Player;
import com.snakegame.dto.ScoreCard;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ScoreService {
    private final LeaderboardDAO dao = new LeaderboardDAO();

    public ScoreCard finishGame(Player player, GameService game, long durationSeconds) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LeaderBoardEntry entry = new LeaderBoardEntry(player.getName(), game.getScore(), today);
        List<LeaderBoardEntry> top = dao.recordScore(entry);
        ScoreCard card = new ScoreCard(player.getName(), game.getScore(), game.getFoodsEaten(),
                game.getSnake().getLength(), durationSeconds);
        int rank = -1;
        for (int i = 0; i < top.size(); i++) {
            LeaderBoardEntry e = top.get(i);
            if (e.getScore() == game.getScore()) {
                rank = i + 1;
                break;
            }
        }
        card.setRank(rank);
        return card;
    }

    public List<LeaderBoardEntry> getTopScores() {
        return dao.load();
    }
}
