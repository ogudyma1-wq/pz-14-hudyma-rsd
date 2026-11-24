package com.ua.moviestore.web;

import com.ua.moviestore.service.MovieService;
import com.ua.moviestore.web.dto.MovieCardDto;
import com.ua.moviestore.web.dto.MovieDetailDto;
import com.ua.moviestore.web.dto.MovieRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieCardDto> list(@RequestParam(required = false) String genre) {
        return movieService.findAll(genre);
    }

    @GetMapping("/{id}")
    public MovieDetailDto getOne(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)                 // 201
    public MovieDetailDto create(@RequestBody MovieRequest request) {
        return movieService.create(request);
    }

    @PutMapping("/{id}")
    public MovieDetailDto update(@PathVariable Long id, @RequestBody MovieRequest request) {
        return movieService.update(id, request);         // 200, або 404 якщо немає
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();       // 204
    }
}
