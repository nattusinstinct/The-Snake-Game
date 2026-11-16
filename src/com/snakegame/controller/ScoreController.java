package com.snakegame.controller;

import com.snakegame.dto.LeaderBoardEntry;
import com.snakegame.dto.Player;
import com.snakegame.dto.ScoreCard;
import com.snakegame.service.GameService;
import com.snakegame.service.ScoreService;
import com.snakegame.view.GameView;
import java.util.List;

public class ScoreController {
    private final ScoreService scoreService = new ScoreService();
    private final GameView view = new GameView();

    public void finish(Player player, GameService game, long durationSeconds) {
       
        ScoreCard card = scoreService.finishGame(player, game, durationSeconds);
        List<LeaderBoardEntry> top = scoreService.getTopScores();

        view.printScoreCard(card);
        view.printLeaderboard(top);
    }
}
