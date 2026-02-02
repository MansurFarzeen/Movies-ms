package org.com.ex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class MovieRequestDto {
    @NotNull
    private String imdbID;

    @NotNull
    private String title;

    @NotNull
    private String year;

    @NotNull
    private MovieType type;

    @NotNull
    private String imageUrl;
}

