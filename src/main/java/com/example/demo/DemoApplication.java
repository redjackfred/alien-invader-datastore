package com.example.demo;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@SpringBootApplication
public class DemoApplication {
  @Autowired
  GameRecordRepository gameRecordRepository;

  public static void main(String[] args) {
     SpringApplication.run(DemoApplication.class, args);
  }

  @ShellMethod("Saves a game record to Cloud Datastore: save-game-record <score> <playerId> <handle>")
  public String saveGameRecord(int score, String playerId, String handle) {
     GameRecord savedGameRecord = this.gameRecordRepository.save(new GameRecord(score, playerId, handle));
     return savedGameRecord.toString();
  }

  @ShellMethod("Loads all game records")
  public String findAllGameRecords() {
     Iterable<GameRecord> gameRecords = this.gameRecordRepository.findAll();
     return Lists.newArrayList(gameRecords).toString();
  }

  @ShellMethod("Loads game records by a specific score: find-by-score <score>")
  public String findByScore(int score) {
     List<GameRecord> gameRecords = this.gameRecordRepository.findByScore(score);
     return gameRecords.toString();
  }

  @ShellMethod("Loads game records which are greater than a given score: find-by-score-greater-than <score>")
  public String findByScoreGreaterThan(int score) {
     List<GameRecord> gameRecords = this.gameRecordRepository.findByScoreGreaterThan(score);
     return gameRecords.toString();
  }

  @ShellMethod("Loads game records by playerId: find-by-playerId <playerId>")
  public String findByPlayerId(String playerId) {
     List<GameRecord> gameRecords = this.gameRecordRepository.findByPlayerId(playerId);
     return gameRecords.toString();
  }

  @ShellMethod("Removes all game records")
  public void removeAllBooks() {
     this.gameRecordRepository.deleteAll();
  }
}
