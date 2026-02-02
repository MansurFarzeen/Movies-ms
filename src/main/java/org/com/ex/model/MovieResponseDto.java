package org.com.ex.model;


import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@Setter
@NoArgsConstructor
public class MovieResponseDto {
    private Long id;
    private String imdbID;
    private String title;
    private String year;
    private MovieType type;
    private String imageUrl;
}
