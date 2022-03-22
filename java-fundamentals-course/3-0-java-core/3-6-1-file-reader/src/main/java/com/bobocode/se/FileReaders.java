package com.bobocode.se;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Path filePath = Paths.get(Objects.requireNonNull(FileReaders.class.getClassLoader().getResource(fileName)).toURI());
            Scanner scanner = new Scanner(new File(String.valueOf(filePath)));
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return (sb.length() > 0) ? sb.substring(0, sb.length() - 1) : sb.toString();
    }

}
