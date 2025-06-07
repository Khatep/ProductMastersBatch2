package seven;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Post {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);
    private final long id;
    private final User author;
    private final String content;
    private int likes;
    private int reposts;
    private final LocalDateTime createdAt;

    public Post(User author, String content) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.author = author;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public int getReposts() {
        return reposts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void like() {
        likes++;
    }

    public void repost() {
        reposts++;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author.name() +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", reposts=" + reposts +
                '}';
    }
}
