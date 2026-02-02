package org.com.ex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ex.entity.MovieEntity;
import org.com.ex.exception.CustomException;
import org.com.ex.model.CreateMovieRequestDto;
import org.com.ex.model.MovieDto;
import org.com.ex.model.MovieInfoDto;
import org.com.ex.repository.DemoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {
    private final DemoRepository demoRepository;

    public MovieDto createMovie(CreateMovieRequestDto movieRequest) {
        log.info("Start creating movie into database.");
        MovieEntity entity = MovieEntity.builder()
                .name(movieRequest.getName())
                .releaseDate(movieRequest.getReleaseDate())
                .rating(movieRequest.getRating())
                .genre(movieRequest.getGenre())
                .director(movieRequest.getDirector())
                .durationMinutes(movieRequest.getDurationMinutes())
                .build();

        MovieEntity savedEntity = demoRepository.save(entity);
        log.info("Successfully save movie into database.");

        return toMovieDto(savedEntity);
    }

    public Page<MovieDto> getAllMovies(int page) {
        if (page < 0) {
            throw new CustomException("Page index must not be less than zero", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        PageRequest pageRequest = PageRequest.of(page, 2, Sort.by("id").ascending());

        Page<MovieEntity> moviePage = demoRepository.findAll(pageRequest);
        log.info("find {} movies in database", moviePage.getTotalElements());
        return moviePage.map(this::toMovieDto);
    }

    public List<MovieDto> searchMovieByName(String movieName) {
        if (movieName == null || movieName.isBlank()) {
            throw new CustomException("Movie name must not be empty", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<MovieEntity> movieEntities = demoRepository.findByNameContainingIgnoreCase(movieName);
        log.info("Found {} movies in database", movieEntities.size());
        return movieEntities.stream().map(this::toMovieDto).toList();
    }

    public MovieDto getMovieById(Long id) {
        MovieEntity entity = demoRepository.findById(id)
                .orElseThrow(() -> new CustomException(String.format("Movie not found with id: {}", id), HttpStatus.INTERNAL_SERVER_ERROR));

        return toMovieDto(entity);
    }

    public MovieInfoDto getMovieInfo(Long id) {
        MovieEntity entity = demoRepository.findById(id)
                .orElseThrow(() -> new CustomException(String.format("Movie not found with id: {}", id), HttpStatus.INTERNAL_SERVER_ERROR));

        return MovieInfoDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .rating(entity.getRating())
                .genre(entity.getGenre())
                .build();
    }

    private MovieDto toMovieDto(MovieEntity entity) {
        return MovieDto.builder()
                .name(entity.getName())
                .releaseDate(entity.getReleaseDate())
                .rating(entity.getRating())
                .genre(entity.getGenre())
                .director(entity.getDirector())
                .durationMinutes(entity.getDurationMinutes())
                .build();
    }
}
