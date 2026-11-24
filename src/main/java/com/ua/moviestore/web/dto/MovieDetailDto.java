package com.ua.moviestore.web.dto;

import java.math.BigDecimal;

/** DTO для сторінки фільму: повний набір полів. */
public record MovieDetailDto(Long id, String title, String director, BigDecimal price,
                             Integer year, Integer durationMinutes, String genre,
                             String posterUrl, String description) {
}
