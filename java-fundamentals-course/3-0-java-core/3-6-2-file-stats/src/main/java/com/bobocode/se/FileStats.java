package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {

    private char[] chars;

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        FileStats fileStats = new FileStats();
        try {
            Path filePath = Paths.get(FileStats.class.getClassLoader().getResource(fileName).toURI());
            fileStats.chars = fileStats.convertFileToCharArray(filePath.toFile());
        } catch (FileNotFoundException | URISyntaxException | NullPointerException e) {
            throw new FileStatsException("File not found");
        }
        return fileStats;
    }

    private char[] convertFileToCharArray(File fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(fileName);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(System.lineSeparator());
        }
        return (sb.length() > 0) ? sb.substring(0, sb.length() - 1).toCharArray() : new char[0];
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        int count = 0;
        for (char c : chars) {
            if (c == character) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        Map<Character, Integer> pairs = new HashMap<>();
        for (char c : chars) {
            if (Character.isWhitespace(c))
                continue;
            if (!pairs.containsKey(c)) {
                pairs.put(c, 1);
            } else {
                pairs.put(c, pairs.get(c) + 1);
            }
        }
        return pairs.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).get().getKey();
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        boolean isContains = false;
        for (char c : chars) {
            if (c == character && !Character.isWhitespace(c)) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }
}
