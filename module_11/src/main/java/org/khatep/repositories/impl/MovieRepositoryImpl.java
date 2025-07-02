package org.khatep.repositories.impl;

import org.khatep.domain.Movie;
import org.khatep.repositories.MovieRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private static final List<Movie> MOVIES = new ArrayList<>();

    static {
        MOVIES.add(new Movie("The Shawshank Redemption", "Frank Darabont", 1994));
        MOVIES.add(new Movie("The Godfather", "Francis Ford Coppola", 1972));
        MOVIES.add(new Movie("The Dark Knight", "Christopher Nolan", 2008));
        MOVIES.add(new Movie("Pulp Fiction", "Quentin Tarantino", 1994));
        MOVIES.add(new Movie("Inception", "Christopher Nolan", 2010));
    }

    @Override
    public List<Movie> getAllMovies() {
        return MOVIES;
    }

    @Override
    public List<Movie> findByDirector(String director) {
        return MOVIES.stream()
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director))
                .toList();
    }

    @Override
    public void save(Movie movie) {
        MOVIES.add(movie);
    }
}
