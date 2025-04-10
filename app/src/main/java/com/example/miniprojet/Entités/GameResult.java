package com.example.miniprojet.Entités;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import java.util.Date;

@Entity(tableName = "game_results")
public class GameResult {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_id")
    private String userId;

    @ColumnInfo(name = "game_name")
    private String gameName;

    private String difficulty;
    private int level;

    @ColumnInfo(name = "level_unlocked", defaultValue = "0")
    private boolean levelUnlocked;

    private int score;

    @ColumnInfo(name = "correct_answers")
    private int correctAnswers;

    @ColumnInfo(name = "total_questions")
    private int totalQuestions;

    @ColumnInfo(name = "completion_date")
    private long dateTimestamp;

    // Constructeur
    public GameResult(String userId, String gameName, String difficulty,
                      int level, int score, int correctAnswers, int totalQuestions) {
        this.userId = userId;
        this.gameName = gameName;
        this.difficulty = difficulty;
        this.level = level;
        this.score = score;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.dateTimestamp = System.currentTimeMillis();
        this.levelUnlocked = false;
    }

    // Getters
    public int getId() { return id; }
    public String getUserId() { return userId; }
    public String getGameName() { return gameName; }
    public String getDifficulty() { return difficulty; }
    public int getLevel() { return level; }
    public boolean isLevelUnlocked() { return levelUnlocked; }
    public int getScore() { return score; }
    public Date getDate() { return new Date(dateTimestamp); }
    public long getDateTimestamp() { return dateTimestamp; }
    public int getCorrectAnswers() { return correctAnswers; }
    public int getTotalQuestions() { return totalQuestions; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setLevelUnlocked(boolean levelUnlocked) { this.levelUnlocked = levelUnlocked; }
    public void setDateTimestamp(long timestamp) { this.dateTimestamp = timestamp; }
}
