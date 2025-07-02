package org.khatep.services.impl;

import lombok.RequiredArgsConstructor;
import org.khatep.domain.Movie;
import org.khatep.repositories.MovieRepository;
import org.khatep.services.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;


    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    @Override
    public List<Movie> findByDirector(String director) {
        return movieRepository.findByDirector(director);
    }

    @Override
    public void createMovie(Movie movie) {
        if (movie.getYear() < 1900) {
            throw new IllegalArgumentException("Year must be 1900 or later");
        }
        if (!StringUtils.hasText(movie.getDirector())) {
            throw new IllegalArgumentException("Director name must not be empty");
        }
        if (!StringUtils.hasText(movie.getTitle())) {
            throw new IllegalArgumentException("Title must not be empty");
        }

        movieRepository.save(movie);
    }
}
