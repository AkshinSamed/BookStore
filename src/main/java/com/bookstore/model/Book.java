package com.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
public class Book extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private float cost;
    @Column(name = "year_published")
    private int yearPublished;
    @Column(name = "brief_content")
    private String briefContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Author author;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Book(String title, String genre, float cost,
                int yearPublished, String briefContent, Author author) {
        super();
        this.title = title;
        this.genre = genre;
        this.cost = cost;
        this.yearPublished = yearPublished;
        this.briefContent = briefContent;
        this.author = author;
    }

}
