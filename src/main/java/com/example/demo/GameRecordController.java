package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameRecordController {
    private final GameRecordRepository gameRecordRepository;

    public GameRecordController(GameRecordRepository gameRecordRepository) {
        this.gameRecordRepository = gameRecordRepository;
    }

    @PutMapping("/updateHandle")
    @CrossOrigin(origins = "*")
    public String updateUser(@RequestBody String playerId, @RequestParam String handle) {
        Iterable<GameRecord> gameRecords = this.gameRecordRepository.findByPlayerId(playerId);
        for (GameRecord gameRecord : gameRecords) {
            gameRecord.setHandle(handle);
            gameRecordRepository.save(gameRecord);
        }
        return "Updated";
    }


    @PostMapping("/saveGameRecord")
    @CrossOrigin(origins = "*")
    public String saveGameRecord(@RequestBody GameRecord gameRecord) {
        if (gameRecord == null) {
            return "The record is invalid";
        }
        this.gameRecordRepository.save(gameRecord);
        return "success";
    }

    @GetMapping("/findAllGameRecords")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<GameRecord> findAllGameRecords() {
        Iterable<GameRecord> gameRecords = this.gameRecordRepository.findAll();
        List<GameRecord> gameRecordList = new ArrayList<>();
        gameRecords.forEach(gameRecordList::add);
        return gameRecordList;
    }

    @DeleteMapping("/deleteAll")
    @CrossOrigin(origins = "*")
    public String deleteAll() {
        this.gameRecordRepository.deleteAll();
        return "success";
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public String deleteById(@PathVariable("id") Long id) {
        if (id == null) {
            return "The GameRecord is invalid";
        }
        this.gameRecordRepository.deleteById(id);
        return "success";
    }
}