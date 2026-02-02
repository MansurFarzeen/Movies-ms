package org.com.ex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MovieInfoDto {
    private Long id;
    private String name;
    private double rating;
    private String genre;
}
