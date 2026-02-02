package org.com.ex.controller;

import lombok.RequiredArgsConstructor;
import org.com.ex.model.CreateMovieRequestDto;
import org.com.ex.model.MovieDto;
import org.com.ex.model.MovieInfoDto;
import org.com.ex.service.DemoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class DemoController {
    private final DemoService demoService;

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody CreateMovieRequestDto movieRequest) {
        return ResponseEntity.ok(demoService.createMovie(movieRequest));
    }

    @GetMapping
    public ResponseEntity<Page<MovieDto>> getAllMovies(@RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(demoService.getAllMovies(page));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> searchByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(demoService.searchMovieByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        MovieDto movie = demoService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<MovieInfoDto> getMovieInfo(@PathVariable Long id) {
        MovieInfoDto info = demoService.getMovieInfo(id);
        return ResponseEntity.ok(info);
    }

}
