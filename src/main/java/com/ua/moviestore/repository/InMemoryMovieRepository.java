package com.ua.moviestore.repository;

import com.ua.moviestore.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryMovieRepository implements MovieRepository {

    private final Map<Long, Movie> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public List<Movie> findAll() {
        return storage.values().stream()
                .sorted(Comparator.comparing(Movie::getId))
                .toList();
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        return storage.values().stream()
                .filter(m -> genre.equalsIgnoreCase(m.getGenre()))
                .sorted(Comparator.comparing(Movie::getId))
                .toList();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            movie.setId(sequence.incrementAndGet());
        }
        storage.put(movie.getId(), movie);
        return movie;
    }

    @Override
    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }
}
