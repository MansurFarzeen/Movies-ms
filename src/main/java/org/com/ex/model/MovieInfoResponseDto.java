package org.com.ex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MovieInfoResponseDto {
    private Long id;
    private String title;
    private String year;
    private MovieType type;
}
