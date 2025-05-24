package medium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Hw6Medium {
    public static void main(String[] args) {
        Set<String> uniqueWords = new LinkedHashSet<>();
        Map<String, Integer> uniqueWordsWithCounts = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader("module_6/resources/words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[.,]", "").toLowerCase();

                String[] words = line.split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        uniqueWords.add(word);
                        uniqueWordsWithCounts.put(word, uniqueWordsWithCounts.getOrDefault(word, 0) + 1);
                    }
                }
            }

            System.out.println("All unique words: " + uniqueWords);

            LinkedHashMap<String, Integer> sortedWordsWithCounts = uniqueWordsWithCounts.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new
                    ));

            System.out.println("Sorted unique words by counts: " + sortedWordsWithCounts);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
