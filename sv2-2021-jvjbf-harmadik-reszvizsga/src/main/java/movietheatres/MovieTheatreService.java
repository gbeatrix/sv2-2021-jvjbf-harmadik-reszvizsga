package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class MovieTheatreService {
    private Map<String, List<Movie>> shows = new LinkedHashMap<>();

    public void readFromFile(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("[-;]");
                String theatre = parts[0];
                String movieTitle = parts[1];
                LocalTime startTime = LocalTime.parse(parts[2]);
                shows.putIfAbsent(theatre, new LinkedList<>());
                shows.get(theatre).add(new Movie(movieTitle, startTime));
            }
            for (List<Movie> movies : shows.values()) {
                movies.sort(Comparator.comparing(Movie::getStartTime));
            }
        } catch (IOException e) {
            throw new IllegalStateException("File not found!", e);
        }
    }

    public Map<String, List<Movie>> getShows() {
        return new LinkedHashMap<>(shows);
    }

    public List<String> findMovie(String title) {
        return shows.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(movie -> movie.getTitle().equals(title)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public LocalTime findLatestShow(String title) {
        return shows.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .filter(movie -> movie.getTitle().equals(title))
                .map(Movie::getStartTime)
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find movie: " + title));
    }
}
