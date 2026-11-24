package com.ua.moviestore.web.dto;

import java.math.BigDecimal;

/** DTO для переліку: мінімум полів, потрібних картці. */
public record MovieCardDto(Long id, String title, String director,
                           BigDecimal price, String posterUrl) {
}
