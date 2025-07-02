package org.khatep.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.khatep.domain.Movie;
import org.khatep.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/by-director")
    public ResponseEntity<List<Movie>> getByDirector(@RequestParam String name) {
        return ResponseEntity.ok(movieService.findByDirector(name));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        try {
            movieService.createMovie(movie);
            return ResponseEntity.ok("Successfully added!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
