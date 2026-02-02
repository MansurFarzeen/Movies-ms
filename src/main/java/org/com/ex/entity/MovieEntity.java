package org.com.ex.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.com.ex.model.MovieType;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "imdbID")
    private String imdbID;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private String year;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private MovieType type;

    @Column(name = "imageUrl")
    private String imageUrl;
}
