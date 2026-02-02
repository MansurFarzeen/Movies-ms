package org.com.ex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ex.entity.MovieEntity;
import org.com.ex.exception.ApiException;
import org.com.ex.model.MovieRequestDto;
import org.com.ex.model.MovieResponseDto;
import org.com.ex.model.MovieInfoResponseDto;
import org.com.ex.repository.MovieRepository;
import org.com.ex.util.MovieMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl {
    private final static int REQUIRED_RESULTS_AT_PAGE = 2;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieInfoResponseDto getMovieInfo(Long movieId) {
        return movieRepository.findById(movieId)
                .map(movieEntity -> {
                    log.info("Found movie with id: {} and title: {}", movieEntity.getId(), movieEntity.getTitle());
                    return movieMapper.toMovieInfoDto(movieEntity);
                })
                .orElseThrow(() -> {
                    log.error("Movie info lookup failed - id: {} not found", movieId);
                    return new ApiException(
                            String.format("Movie with id: %s doesn't exist!", movieId),
                            HttpStatus.NOT_FOUND,
                            "MOVIE_NOT_FOUND"
                    );
                });
    }

    public Page<MovieResponseDto> getMoviesByTitle(String movieTitle) {
        PageRequest pageRequest = PageRequest.of(0, REQUIRED_RESULTS_AT_PAGE, Sort.by("id").ascending());
        Page<MovieEntity> moviePage = movieRepository.findByTitleContainingIgnoreCase(pageRequest, movieTitle);

        log.info("Found {} movies in database matching title: {}", moviePage.getTotalElements(), movieTitle);
        return moviePage.map(movieMapper::toMovieDto);
    }

    public MovieResponseDto getMovieById(Long movieId) {
        return movieRepository.findById(movieId)
                .map(movieEntity -> {
                    log.info("Found movie with id: {} and title: {}", movieEntity.getId(), movieEntity.getTitle());
                    return movieMapper.toMovieDto(movieEntity);
                })
                .orElseThrow(() -> {
                    log.error("Movie fetch failed - id: {} not found", movieId);
                    return new ApiException(
                            String.format("Movie with id: %s doesn't exist!", movieId),
                            HttpStatus.NOT_FOUND,
                            "MOVIE_NOT_FOUND"
                    );
                });
    }

    public Page<MovieResponseDto> getMoviesList(int page) {
        if (page < 0) {
            log.warn("Invalid page request: {}", page);
            throw new ApiException(
                    "Page index must not be less than zero",
                    HttpStatus.BAD_REQUEST,
                    "INVALID_PAGE_INDEX"
            );
        }

        PageRequest pageRequest = PageRequest.of(page, REQUIRED_RESULTS_AT_PAGE, Sort.by("id").ascending());
        Page<MovieEntity> moviePage = movieRepository.findAll(pageRequest);

        log.info("Retrieved page {} with {} movies", page, moviePage.getTotalElements());
        return moviePage.map(movieMapper::toMovieDto);
    }

    public MovieResponseDto addMovie(MovieRequestDto movieDetails) {
        MovieEntity entity = movieMapper.toEntity(movieDetails);
        MovieEntity savedEntity = movieRepository.save(entity);
        log.info("Successfully saved movieEntity in database with id: {}", savedEntity.getId());
        return movieMapper.toMovieDto(savedEntity);
    }
}