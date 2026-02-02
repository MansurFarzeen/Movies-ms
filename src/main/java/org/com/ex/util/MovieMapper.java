package org.com.ex.util;

import org.com.ex.entity.MovieEntity;
import org.com.ex.model.MovieInfoResponseDto;
import org.com.ex.model.MovieRequestDto;
import org.com.ex.model.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieEntity toEntity(MovieRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return MovieEntity.builder()
                .imdbID(dto.getImdbID())
                .title(dto.getTitle())
                .year(dto.getYear())
                .type(dto.getType())
                .imageUrl(dto.getImageUrl())
                .build();
    }

    public MovieResponseDto toMovieDto(MovieEntity entity) {
        if (entity == null) {
            return null;
        }

        return MovieResponseDto.builder()
                .id(entity.getId())
                .imdbID(entity.getImdbID())
                .year(entity.getYear())
                .type(entity.getType())
                .imageUrl(entity.getImageUrl())
                .title(entity.getTitle())
                .build();
    }

    public MovieInfoResponseDto toMovieInfoDto(MovieEntity entity) {
        if (entity == null) {
            return null;
        }

        return MovieInfoResponseDto.builder()
                .id(entity.getId())
                .year(entity.getYear())
                .type(entity.getType())
                .title(entity.getTitle())
                .build();
    }
}