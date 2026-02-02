package org.com.ex.model;


import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
@Setter
@NoArgsConstructor
public class MovieDto {
    private String name;
    private LocalDate releaseDate;
    private double rating;
    private String genre;
    private String director;
    private int durationMinutes;
}
