package com.example.demo.features.film;

import java.nio.channels.Channel;
import java.util.List;
import java.util.UUID;
import java.util.Locale.Category;

import com.example.demo.features.genre.Genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 50)
    private String title;

    private int year;

    private String posterUrl;

    private String description;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "film")
    private List<Genre> genres;

    @OneToMany(mappedBy = "film")
    private List<Channel> channels;

    public FilmDTO convertToDTO() {
        return new FilmDTO(
            id, 
            title, 
            year,
            posterUrl, 
            description, 
            category, 
            genres, 
            channels
        );
    }
}
