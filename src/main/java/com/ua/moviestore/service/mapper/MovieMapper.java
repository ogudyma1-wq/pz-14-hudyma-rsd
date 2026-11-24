package com.ua.moviestore.service.mapper;

import com.ua.moviestore.domain.Movie;
import com.ua.moviestore.web.dto.MovieCardDto;
import com.ua.moviestore.web.dto.MovieDetailDto;
import com.ua.moviestore.web.dto.MovieRequest;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieCardDto toCard(Movie m) {
        return new MovieCardDto(m.getId(), m.getTitle(), m.getDirector(),
                m.getPrice(), m.getPosterUrl());
    }

    public MovieDetailDto toDetail(Movie m) {
        return new MovieDetailDto(m.getId(), m.getTitle(), m.getDirector(), m.getPrice(),
                m.getYear(), m.getDurationMinutes(), m.getGenre(),
                m.getPosterUrl(), m.getDescription());
    }

    public Movie toDomain(MovieRequest r) {
        Movie m = new Movie();
        m.setTitle(r.title());
        m.setDirector(r.director());
        m.setPrice(r.price());
        m.setYear(r.year());
        m.setDurationMinutes(r.durationMinutes());
        m.setGenre(r.genre());
        m.setPosterUrl(r.posterUrl());
        m.setDescription(r.description());
        return m;   // id не встановлюємо тут
    }
}
