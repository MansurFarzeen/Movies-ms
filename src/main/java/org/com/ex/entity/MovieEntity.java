package org.com.ex.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(nullable = false, name = "rating")
    private double rating;

    @Column(nullable = false, name = "genre")
    private String genre;

    @Column(nullable = false, name = "director")
    private String director;

    @Column(nullable = false, name = "duration_minutes")
    private int durationMinutes;
}
