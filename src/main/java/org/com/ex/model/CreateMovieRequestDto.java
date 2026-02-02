package org.com.ex.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class CreateMovieRequestDto {
    @NotBlank
    private String name;
    private LocalDate releaseDate;
    @NotNull
    private Double rating;
    @NotBlank
    private String genre;
    @NotBlank
    private String director;
    @NotNull
    @Min(1)
    private Integer durationMinutes;
}
