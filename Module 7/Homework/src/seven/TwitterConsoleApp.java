package seven;
import java.util.*;

public class TwitterConsoleApp {
  private static final Scanner scanner = new Scanner(System.in);
  private static final TwitterService twitterService = new TwitterService();

  public static void main(String[] args) {
    new TwitterConsoleApp().run();
  }

  public void run() {
    System.out.print("Введите ваше имя: ");
    String userName = scanner.nextLine().trim();
    User currentUser = new User(userName);
    System.out.println("Добро пожаловать, " + currentUser.name() + "!");

    twitterService.initializePosts();

    while (true) {
      showMenu();
      int choice = getIntInput();
      switch (choice) {
        case 1 -> writePost(currentUser);
        case 2 -> likePost();
        case 3 -> repostPost(currentUser);
        case 4 -> showPosts(twitterService.getAllPosts());
        case 5 -> showPopularPosts();
        case 6 -> showPosts(twitterService.getUserPosts(currentUser));
        case 7 -> {
          System.out.println("Выход...");
          return;
        }
        default -> System.out.println("Некорректный ввод. Попробуйте снова.");
      }
    }
  }

  private void writePost(User user) {
    System.out.print("Введите текст поста (макс. 280 символов): ");
    String content = scanner.nextLine();
    if (content.length() > 280) {
      System.out.println("Пост слишком длинный.");
      return;
    }
    twitterService.createPost(user, content);
    System.out.println("Пост добавлен!");
  }

  private void likePost() {
    System.out.print("Введите ID поста для лайка: ");
    long id = getIntInput();
    if (!twitterService.likePost(id)) {
      System.out.println("Пост не найден.");
    } else {
      System.out.println("Пост лайкнут.");
    }
  }

  private void repostPost(User user) {
    System.out.print("Введите ID поста для репоста: ");
    long id = getIntInput();
    if (!twitterService.repostPost(user, id)) {
      System.out.println("Пост не найден.");
    } else {
      System.out.println("Пост репостнут.");
    }
  }

  private void showPosts(List<Post> posts) {
    if (posts.isEmpty()) {
      System.out.println("Нет постов для отображения.");
      return;
    }
    posts.forEach(System.out::println);
  }

  private void showPopularPosts() {
    System.out.print("Введите количество популярных постов: ");
    int count = getIntInput();
    showPosts(twitterService.getPopularPosts(count));
  }

  private int getIntInput() {
    try {
      return Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Ошибка ввода числа.");
      return -1;
    }
  }

  private static void showMenu() {
    System.out.println("\n=== Twitter Console ===");
    System.out.println("1. Написать пост");
    System.out.println("2. Лайкнуть пост");
    System.out.println("3. Сделать репост");
    System.out.println("4. Показать все посты");
    System.out.println("5. Показать популярные посты");
    System.out.println("6. Показать мои посты");
    System.out.println("7. Выход");
    System.out.print("Выберите действие: ");
  }
}
