package com.example.demo;

import java.util.List;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;


public interface GameRecordRepository extends DatastoreRepository<GameRecord, Long> {

  List<GameRecord> findByScore(int score);

  List<GameRecord> findByPlayerId(String playerId);

  List<GameRecord> findByHandle(String handle);

  List<GameRecord> findByScoreGreaterThan(int score);

}