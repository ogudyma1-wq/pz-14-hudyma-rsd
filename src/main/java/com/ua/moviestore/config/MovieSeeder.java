package com.ua.moviestore.config;

import com.ua.moviestore.domain.Movie;
import com.ua.moviestore.repository.MovieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/** Початкове наповнення сховища (жанр кожного фільму використовується у фільтрі). */
@Component
public class MovieSeeder implements ApplicationRunner {

    private final MovieRepository repository;

    public MovieSeeder(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) {
        repository.save(new Movie(null, "Тіні забутих предків", "Сергій Параджанов",
                new BigDecimal("180.00"), 1965, 97, "Драма",
                "/posters/shadows.jpg",
                "Поетична драма за повістю Михайла Коцюбинського про кохання "
                        + "гуцула Івана і Марічки в Карпатах."));

        repository.save(new Movie(null, "Земля", "Олександр Довженко",
                new BigDecimal("150.00"), 1930, 75, "Драма",
                "/posters/zemlia.jpg",
                "Німий класичний фільм про колективізацію на селі, "
                        + "один із найвідоміших творів українського кіно."));

        repository.save(new Movie(null, "Інтерстеллар", "Крістофер Нолан",
                new BigDecimal("320.00"), 2014, 169, "Науково-фантастичний",
                "/posters/interstellar.jpg",
                "Група дослідників вирушає крізь червоточину в пошуках "
                        + "нового дому для людства."));

        repository.save(new Movie(null, "Паразити", "Пон Чжун Хо",
                new BigDecimal("280.00"), 2019, 132, "Трилер",
                "/posters/parasite.jpg",
                "Бідна родина поступово проникає в життя заможної сім'ї, "
                        + "і ця історія обертається несподіваними наслідками."));

        repository.save(new Movie(null, "Леон", "Люк Бессон",
                new BigDecimal("210.00"), 1994, 110, "Кримінальний бойовик",
                "/posters/leon.jpg",
                "Найманий убивця опікується дванадцятирічною дівчинкою, "
                        + "чию родину вбили корумповані поліцейські."));
    }
}
