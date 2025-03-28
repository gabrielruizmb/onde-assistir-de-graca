package com.example.demo.features.film;

import java.util.List;
import java.util.UUID;

import com.example.demo.features.category.Category;
import com.example.demo.features.channel.Channel;
import com.example.demo.features.genre.Genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(optional = true)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "film_genre",
        joinColumns = 
            @JoinColumn(name = "film_id", referencedColumnName = "id"),
        inverseJoinColumns = 
            @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private List<Genre> genres;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "film_channel",
        joinColumns = 
            @JoinColumn(name = "film_id", referencedColumnName = "id"),
        inverseJoinColumns = 
            @JoinColumn(name = "channel_id", referencedColumnName = "id")
    )
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
