package gameserver;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Entity(name = "Record")
public class Record {
    @Id
    Long id;

    private String userId;
    private String userName;
    private int score;
    private String date;

    public Record(String userId, String userName, int score, String date) {
        this.userId = userId;
        this.userName = userName;
        this.score = score;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return score == record.score && Objects.equals(id, record.id) && Objects.equals(userId, record.userId) && Objects.equals(userName, record.userName) && Objects.equals(date, record.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userName, score, date);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", score=" + score +
                ", date='" + date + '\'' +
                '}';
    }
}
