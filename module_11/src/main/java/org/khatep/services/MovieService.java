package org.khatep.services;

import org.khatep.domain.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> findByDirector(String director);
    void createMovie(Movie movie);
}
