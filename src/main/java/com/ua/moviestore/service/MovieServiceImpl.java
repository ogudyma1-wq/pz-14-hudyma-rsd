package com.ua.moviestore.service;

import com.ua.moviestore.domain.Movie;
import com.ua.moviestore.exception.MovieNotFoundException;
import com.ua.moviestore.repository.MovieRepository;
import com.ua.moviestore.service.mapper.MovieMapper;
import com.ua.moviestore.web.dto.MovieCardDto;
import com.ua.moviestore.web.dto.MovieDetailDto;
import com.ua.moviestore.web.dto.MovieRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;
    private final MovieMapper mapper;

    public MovieServiceImpl(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<MovieCardDto> findAll(String genre) {
        List<Movie> movies = (genre == null || genre.isBlank())
                ? repository.findAll()
                : repository.findByGenre(genre);
        return movies.stream().map(mapper::toCard).toList();
    }

    @Override
    public MovieDetailDto findById(Long id) {
        Movie movie = repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        return mapper.toDetail(movie);
    }

    @Override
    public MovieDetailDto create(MovieRequest request) {
        Movie movie = mapper.toDomain(request);
        movie.setId(null);
        return mapper.toDetail(repository.save(movie));
    }

    @Override
    public MovieDetailDto update(Long id, MovieRequest request) {
        repository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        Movie movie = mapper.toDomain(request);
        movie.setId(id);
        return mapper.toDetail(repository.save(movie));
    }

    @Override
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new MovieNotFoundException(id);
        }
    }
}
