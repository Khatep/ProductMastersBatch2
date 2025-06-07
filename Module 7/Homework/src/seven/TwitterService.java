package seven;

import java.util.*;

public class TwitterService {
    private final List<Post> posts = new ArrayList<>();

    public void initializePosts() {
        posts.add(new Post(new User("Alice"), "Привет, мир!"));
        posts.add(new Post(new User("Bob"), "Сегодня отличный день!"));
        posts.add(new Post(new User("Charlie"), "Люблю программировать на Java."));
        System.out.println("Добавлены стартовые посты.");
    }

    public void createPost(User user, String content) {
        posts.add(new Post(user, content));
    }

    public boolean likePost(long id) {
        return posts.stream()
                .filter(p -> p.getId() == id).findFirst().map(p -> {
                    p.like();
                    return true;
                }).orElse(false);
    }

    public boolean repostPost(User user, long id) {
        return posts.stream()
                .filter(p -> p.getId() == id).findFirst().map(original -> {
                    original.repost();
                    posts.add(new Post(user, "(Репост) " + original.getContent()));
                    return true;
                }).orElse(false);
    }

    public List<Post> getAllPosts() {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .toList();
    }

    public List<Post> getPopularPosts(int count) {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getLikes).reversed())
                .limit(count)
                .toList();
    }

    public List<Post> getUserPosts(User user) {
        return posts.stream()
                .filter(p -> p.getAuthor().name().equals(user.name()))
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .toList();
    }
}
