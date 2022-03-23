package com.bobocode.se;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {

    private List<Character> chars;

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        FileStats fileStats = new FileStats();
        try {
            fileStats.chars = fileStats.convertFileToCharArray(new File(fileName));
        } catch (IOException | URISyntaxException | NullPointerException e) {
            throw new FileStatsException("File not found");
        }
        return fileStats;
    }

    private List<Character> convertFileToCharArray(File file) throws IOException, URISyntaxException, NullPointerException {
        List<Character> characters = new ArrayList<>();
        URL res = getClass().getClassLoader().getResource(file.getName());
        if (res == null)
            throw new NullPointerException();

        BufferedReader br = new BufferedReader(new FileReader(Paths.get(res.toURI()).toFile()));
        int c;
        while ((c = br.read()) != -1) {
            characters.add((char) c);
        }
        return characters;
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return (int) chars.stream().filter(c -> c == character).count();
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
        return chars.stream().filter(c -> !Character.isWhitespace(c)).anyMatch(c -> c == character);
    }

}
