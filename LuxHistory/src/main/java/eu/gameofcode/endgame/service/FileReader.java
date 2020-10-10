package eu.gameofcode.endgame.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileReader {

    public List<String> getFileLines(String fileName) throws URISyntaxException, IOException {
        try {
            return Files.lines(Paths.get(getClass().getClassLoader().getResource(fileName).toURI())).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("There isn't such file in called root");
        }
    }

}
