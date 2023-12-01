package gameserver;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class RecordController {

    private final RecordRepository recordRepository;

    public RecordController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    /**
     * save a new record into database
     * @param record the record react posted
     * @return string "success" if correctly saved
     */
    @PostMapping("/saveRecord")
    @CrossOrigin("*")
    public String saveRecord(@RequestBody Record record){
        System.out.println(record.getUserId() + record.getUserName() + record.getScore() + record.getDate());
        if (record == null) {
            return "fail";
        }
        this.recordRepository.save(record);
        return "success";
    }

    /**
     * find all the record within specific page
     * localhost:8080/findAllRecord?pageNum=1&pageSize=5&sortDirection=desc
     * @param pageNum
     * @param pageSize
     * @param sortDirection
     * @return records within given size and page
     */
    @GetMapping("/findAllRecord")
    @CrossOrigin("*")
    public Map<String, Object> findAllRecord(@RequestParam int pageNum,
                                             @RequestParam int pageSize,
                                             @RequestParam String sortDirection){
        Pageable pageSortByScore = PageRequest.of(
                pageNum - 1,
                pageSize,
                Objects.equals(sortDirection, "desc")
                        ? Sort.by("score").descending()
                        : Sort.by("score").ascending());

        Page<Record> page = this.recordRepository.findAll(pageSortByScore);

        int totalPages = page.getTotalPages();

        Map<String, Object> results = new HashMap<>();
        results.put("recordList", page.getContent());
        results.put("totalPages", totalPages);

        return results;
    }

    /**
     * Find all records related to given userId
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return all records related to given userId
     */
    @GetMapping("/myRecord")
    @CrossOrigin("*")
    public Map<String, Object> findMyRecord(@RequestParam String userId,
                                            @RequestParam int pageNum,
                                            @RequestParam int pageSize) {
        Pageable pageSortByScore = PageRequest.of(pageNum - 1, pageSize);
        Page<Record> page = this.recordRepository.findAllByUserId(userId, pageSortByScore);

        int totalPages = page.getTotalPages();

        Map<String, Object> results = new HashMap<>();
        results.put("recordList", page.getContent());
        results.put("totalPages", totalPages);

        return results;
    }

    /**
     * Delete record related to primary key id
     * @param json {"id" : "auto gen long type id"}
     * @return success either deleted or non exists.
     */
    //delete record
    @PostMapping("/deleteRecord")
    @CrossOrigin("*")
    public String deleteRecord(@RequestBody Map<String, String> json){
        if (json == null) {
            return "fail";
        }

        // delete by id
        this.recordRepository.deleteById(Long.parseLong(json.get("id")));
        return "success";
    }

    @Override
    public String toString() {
        return "RecordController{" +
                "recordRepository=" + recordRepository +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordController that = (RecordController) o;
        return Objects.equals(recordRepository, that.recordRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordRepository);
    }
}
