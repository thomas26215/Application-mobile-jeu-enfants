package com.example.miniprojet.Entités.User;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.miniprojet.Entités.GameResult;

import java.util.List;

@Dao
public interface GameResultDAO {

    @Insert
    void insert(GameResult gameResult);

    @Update
    void update(GameResult gameResult);

    @Query("DELETE FROM game_results WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM game_results WHERE user_id = :userId ORDER BY completion_date DESC")
    List<GameResult> getUserResults(String userId);

    @Query("SELECT * FROM game_results WHERE game_name = :gameName AND difficulty = :difficulty ORDER BY score DESC LIMIT 10")
    List<GameResult> getGameLeaderboard(String gameName, String difficulty);

    @Query("SELECT * FROM game_results WHERE user_id = :userId AND game_name = :gameName AND level = :level")
    List<GameResult> getLevelResults(String userId, String gameName, int level);

    @Query("UPDATE game_results SET level_unlocked = 1 WHERE id = :resultId")
    void unlockLevel(int resultId);

    @Query("SELECT AVG(score) FROM game_results WHERE user_id = :userId AND difficulty = :difficulty")
    double getAverageScore(String userId, String difficulty);

    @Query("SELECT MAX(score) FROM game_results WHERE user_id = :userId AND game_name = :gameName")
    int getHighScore(String userId, String gameName);

    @Query("SELECT COUNT(*) FROM game_results WHERE user_id = :userId AND correct_answers = total_questions")
    int getPerfectGamesCount(String userId);
}
