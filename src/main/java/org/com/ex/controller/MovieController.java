package org.com.ex.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.com.ex.entity.MovieEntity;
import org.com.ex.exception.ApiErrorResponse;
import org.com.ex.model.MovieInfoResponseDto;
import org.com.ex.model.MovieRequestDto;
import org.com.ex.model.MovieResponseDto;
import org.com.ex.service.MovieServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1.0.0/movies")
@RequiredArgsConstructor
@Tag(name = "Movie Controller", description = "Management APIs for Movies")
public class MovieController {

    private final MovieServiceImpl movieService;

    @Operation(summary = "Add a new movie", description = "Persists a new movie record in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<MovieResponseDto> addMovie(@Valid @RequestBody MovieRequestDto movieDetails) {
        return new ResponseEntity<>(movieService.addMovie(movieDetails), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all movies", description = "Retrieves a paginated list of all movies.")
    @GetMapping
    public ResponseEntity<Page<MovieResponseDto>> getAllMovies(
            @Parameter(description = "Page number to retrieve (starts from 0)")
            @RequestParam(name = "page", defaultValue = "0") int page) {
        return new ResponseEntity<>(movieService.getMoviesList(page), HttpStatus.OK);
    }

    @Operation(summary = "Get movie by ID", description = "Returns basic details for a specific movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the movie"),
            @ApiResponse(responseCode = "404", description = "Movie not found",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable("id") Long movieId) {
        return new ResponseEntity<>(movieService.getMovieById(movieId), HttpStatus.OK);
    }

    @Operation(summary = "Search movies by title", description = "Finds movies whose titles contain the provided string.")
    @GetMapping("/search")
    public ResponseEntity<Page<MovieResponseDto>> getMoviesByTitle(
            @Parameter(description = "The title (or part of the title) to search for", required = true)
            @RequestParam(name = "title") String movieTitle) {
        return new ResponseEntity<>(movieService.getMoviesByTitle(movieTitle), HttpStatus.OK);
    }

    @Operation(summary = "Get detailed movie info", description = "Returns extended information for a specific movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved details"),
            @ApiResponse(responseCode = "404", description = "Movie ID not found")
    })
    @GetMapping("/{id}/info")
    public ResponseEntity<MovieInfoResponseDto> getMovieInfo(
            @Parameter(description = "ID of the movie to retrieve info for")
            @PathVariable("id") Long movieId) {
        return new ResponseEntity<>(movieService.getMovieInfo(movieId), HttpStatus.OK);
    }
}