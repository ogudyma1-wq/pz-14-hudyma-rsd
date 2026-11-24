package com.ua.moviestore.service;

import com.ua.moviestore.web.dto.MovieCardDto;
import com.ua.moviestore.web.dto.MovieDetailDto;
import com.ua.moviestore.web.dto.MovieRequest;

import java.util.List;

public interface MovieService {
    List<MovieCardDto> findAll(String genre);   // фільтр за жанром (null = усі)
    MovieDetailDto findById(Long id);
    MovieDetailDto create(MovieRequest request);
    MovieDetailDto update(Long id, MovieRequest request);
    void delete(Long id);
}
