package org.khatep.repositories;

import org.khatep.domain.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getAllMovies();
    List<Movie> findByDirector(String director);

    void save(Movie movie);
}
