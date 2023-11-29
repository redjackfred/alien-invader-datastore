package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "gameRecords")
public class GameRecord {
  @Id
  Long id;

  int score;

  String playerId;

  String handle;


  public GameRecord(int score, String playerId, String handle) {
    this.score = score;
    this.playerId = playerId;
    this.handle = handle;
  }

  public long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
  	this.id=id;
  }
  
  public int getScore() {
  	return this.score;
  }
  
  public void setScore(int score) {
  	this.score=score;
  }
   public String getPlayerId() {
  	return this.playerId;
  }
  
  public void setPlayerId(String playerId) {
  	this.playerId=playerId;
  }

  public String getHandle() {
    return this.handle;
  }

  public void setHandle(String handle) {
    this.handle=handle;
  }

  @Override
  public String toString() {
    return "{" +
        "id:" + this.id +
        ", score:'" + this.score + '\'' +
        ", playerId:'" + this.playerId + '\'' +
        ", handle:'" +
        '}';
  }
}