package com.ua.moviestore.web.dto;

import java.math.BigDecimal;

/** Вхідний DTO для створення/оновлення: без id (його призначає сервер). */
public record MovieRequest(String title, String director, BigDecimal price,
                           Integer year, Integer durationMinutes, String genre,
                           String posterUrl, String description) {
}
