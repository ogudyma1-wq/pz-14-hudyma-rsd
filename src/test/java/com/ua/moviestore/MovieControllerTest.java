package com.ua.moviestore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MovieControllerTest {

    private static final String BODY = """
            {"title":"Нова стрічка","director":"Режисер","price":199.00,"year":2024,
             "durationMinutes":100,"genre":"Комедія","posterUrl":"/posters/x.jpg","description":"Опис"}""";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listReturnsFiveSeededMovies() throws Exception {
        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5))
                .andExpect(jsonPath("$[0].title").exists());
    }

    @Test
    void getOneReturnsDetails() throws Exception {
        mockMvc.perform(get("/api/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.durationMinutes").exists());
    }

    @Test
    void unknownIdReturns404() throws Exception {
        mockMvc.perform(get("/api/movies/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void filterByGenreReturnsOnlyThatGenre() throws Exception {
        mockMvc.perform(get("/api/movies").param("genre", "Драма"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void createReturns201AndAssignsId() throws Exception {
        mockMvc.perform(post("/api/movies").contentType(MediaType.APPLICATION_JSON).content(BODY))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.title").value("Нова стрічка"));
    }

    @Test
    void updateExistingReturns200() throws Exception {
        mockMvc.perform(put("/api/movies/1").contentType(MediaType.APPLICATION_JSON).content(BODY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Нова стрічка"));
    }

    @Test
    void updateUnknownReturns404() throws Exception {
        mockMvc.perform(put("/api/movies/999").contentType(MediaType.APPLICATION_JSON).content(BODY))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteExistingReturns204() throws Exception {
        mockMvc.perform(delete("/api/movies/2")).andExpect(status().isNoContent());
        mockMvc.perform(get("/api/movies/2")).andExpect(status().isNotFound());
    }

    @Test
    void deleteUnknownReturns404() throws Exception {
        mockMvc.perform(delete("/api/movies/999")).andExpect(status().isNotFound());
    }
}
